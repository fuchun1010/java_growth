package com.tank.tcp.server;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.NonNull;
import lombok.val;

import java.util.concurrent.CountDownLatch;

/**
 * @author tank198435163.com
 */

public class SimpleServer {

  public SimpleServer() {
    this(9000);
  }

  public SimpleServer(@NonNull final Integer port) {
    Preconditions.checkArgument(port != null, "");
    this.serverBootstrap = this.initServerBootStrap();
    this.port = port;
  }

  public void start() {
    try {
      this.serverBootstrap
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(final NioSocketChannel nioSocketChannel) throws Exception {
                  nioSocketChannel.pipeline().addLast(new StringDecoder());
                  nioSocketChannel.pipeline().addLast(new SimpleChannelInboundHandler<String>() {
                    @Override
                    protected void channelRead0(final ChannelHandlerContext channelHandlerContext,
                                                final String str) throws Exception {
                      System.out.println(StrUtil.format("server received: [{}]", str));
                    }
                  });
                }
              })
              .bind(this.port)
              .sync()
              .addListener(result -> {
                if (result.isSuccess()) {
                  System.out.println(StrUtil.format("server Thread:[{}] success listen port:[{}]",
                          Thread.currentThread().getName(),
                          this.port));
                }

              });
    } catch (Exception e) {
      e.printStackTrace();
    }

  }

  private ServerBootstrap initServerBootStrap() {
    val serverBootstrap = new ServerBootstrap();
    val boss = new NioEventLoopGroup();
    val worker = new NioEventLoopGroup();
    return serverBootstrap.group(boss, worker);
  }

  private final ServerBootstrap serverBootstrap;

  private final Integer port;

  private final CountDownLatch running = new CountDownLatch(1);
}
