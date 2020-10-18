package com.tank.tcp.server;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.tank.tcp.codec.ObjectDecoder;
import com.tank.tcp.handler.server.LoginHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import lombok.NonNull;
import lombok.val;

import java.util.concurrent.CountDownLatch;

import static com.tank.tcp.util.Command.END;
import static com.tank.tcp.util.Command.FIX_PACK_LENGTH;

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
      val channelFuture = this.serverBootstrap
              .channel(NioServerSocketChannel.class)
              .childHandler(new ChannelInitializer<NioSocketChannel>() {
                @Override
                protected void initChannel(final NioSocketChannel channel) throws Exception {
                  val delimiter = Unpooled.copiedBuffer(END.getBytes());
                  channel.pipeline().addFirst(new DelimiterBasedFrameDecoder(FIX_PACK_LENGTH, delimiter));
                  channel.pipeline().addLast(new ObjectDecoder());
                  channel.pipeline().addLast(new LoginHandler());
                }
              })
              .bind(this.port)
              .sync();

      channelFuture.addListener(result -> {
        if (result.isSuccess()) {
          val tips = StrUtil.format("server Thread:[{}] success listen port:[{}]",
                  Thread.currentThread().getName(),
                  this.port);
          System.out.println(tips);

        }
      });

      channelFuture.channel().closeFuture().sync();

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      this.boss.shutdownGracefully();
      this.worker.shutdownGracefully();
    }

  }

  private ServerBootstrap initServerBootStrap() {
    val serverBootstrap = new ServerBootstrap();
    this.boss = new NioEventLoopGroup();
    this.worker = new NioEventLoopGroup();
    return serverBootstrap.group(boss, worker);
  }

  private final ServerBootstrap serverBootstrap;

  private final Integer port;

  private NioEventLoopGroup boss;

  private NioEventLoopGroup worker;

  private final CountDownLatch running = new CountDownLatch(1);
}
