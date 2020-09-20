package com.tank.net.server;

import cn.hutool.core.util.StrUtil;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */

public class NioServer {

  public NioServer(@NonNull final Integer port) {
    super();
    this.port = port;
  }

  @SneakyThrows
  public void start() {
    val serverSocketChannel = this.createServerSocketChannel();
    val selector = Selector.open();
    serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
    while (running.get()) {
      int result = selector.select(100);
      if (result == 0) {
        continue;
      }
      val keys = selector.selectedKeys();
      val iterator = keys.iterator();
      while (iterator.hasNext()) {
        val key = iterator.next();
        if (key.isAcceptable()) {
          System.out.println(StrUtil.format("on line users:[{}]", onLineUsers.incrementAndGet()));
          final SocketChannel channel = serverSocketChannel.accept();
          channel.configureBlocking(false);
          channel.register(selector, SelectionKey.OP_READ);
        } else if (key.isReadable()) {
          final SocketChannel channel = ((SocketChannel) key.channel());
          val defaultBuffer = ByteBuffer.allocate(128);
          val length = channel.read(defaultBuffer);
          final byte[] buffer = new byte[length];
          System.arraycopy(defaultBuffer.array(), 0, buffer, 0, length);
          channel.configureBlocking(false);
          String value = new String(buffer);
          System.out.println(StrUtil.format("server received :[{}]", value));
          if ("quit".equalsIgnoreCase(value)) {
            channel.shutdownInput();
            channel.shutdownOutput();
            channel.close();
          } else {
            channel.register(selector, SelectionKey.OP_WRITE, ByteBuffer.wrap(buffer));
          }

        } else if (key.isWritable()) {
          final SocketChannel channel = ((SocketChannel) key.channel());
          channel.configureBlocking(false);
          channel.write(ByteBuffer.wrap("I am server".getBytes()));
          channel.register(selector, SelectionKey.OP_READ);
        }
        iterator.remove();
      }
    }
  }

  public void stop() {
    this.running.set(false);
  }

  @SneakyThrows
  private ServerSocketChannel createServerSocketChannel() {
    val serverSocketChannel = ServerSocketChannel.open();
    serverSocketChannel.socket().bind(new InetSocketAddress(this.port));
    System.out.println(StrUtil.format("listening port:[{}] success", this.port));
    serverSocketChannel.configureBlocking(false);
    return serverSocketChannel;
  }

  private final Integer port;

  private final AtomicBoolean running = new AtomicBoolean(true);

  private final AtomicInteger onLineUsers = new AtomicInteger(0);
}
