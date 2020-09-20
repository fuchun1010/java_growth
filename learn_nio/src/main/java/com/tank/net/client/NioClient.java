package com.tank.net.client;

import cn.hutool.core.util.StrUtil;
import lombok.*;
import lombok.experimental.Accessors;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @author tank198435163.com
 */
@Accessors(chain = true)
public class NioClient {

  @SneakyThrows
  public void echo() {
    val socketChannel = SocketChannel.open();
    val selector = Selector.open();
    socketChannel.configureBlocking(false);

    if (!socketChannel.connect(new InetSocketAddress(this.ip, this.port))) {
      while (!socketChannel.finishConnect()) {
        System.out.println("try to connect");
      }
    }

    socketChannel.register(selector, SelectionKey.OP_READ);
    socketChannel.write(ByteBuffer.wrap("I am client".getBytes()));
    while (runnnig.get()) {
      if (selector.select() == 0) {
        continue;
      }
      val keys = selector.selectedKeys();
      val iterators = keys.iterator();
      val defaultBuffer = ByteBuffer.allocate(128);
      val scanner = new Scanner(System.in);
      while (iterators.hasNext()) {
        val key = iterators.next();
        if (key.isReadable()) {
          SocketChannel channel = ((SocketChannel) key.channel());
          val length = channel.read(defaultBuffer);
          val buffer = new byte[length];
          System.arraycopy(defaultBuffer.array(), 0, buffer, 0, length);
          val result = ByteBuffer.wrap(buffer);
          result.flip();
          System.out.println(StrUtil.format("client received:[{}]", new String(result.array())));
          channel.register(selector, SelectionKey.OP_WRITE);
        } else if (key.isWritable()) {
          SocketChannel channel = ((SocketChannel) key.channel());
          defaultBuffer.clear();
          System.out.println("input:");
          String str = scanner.nextLine();
          channel.write(ByteBuffer.wrap(str.getBytes()));
          channel.register(selector, SelectionKey.OP_READ);

        }
        iterators.remove();
      }
    }


  }


  @Setter(value = AccessLevel.PRIVATE)
  private AtomicBoolean runnnig = new AtomicBoolean(true);

  @Getter
  @Setter
  private String ip;

  @Setter
  private Integer port;
}
