package com.tank.time.server;

import cn.hutool.core.util.StrUtil;
import com.tank.share.util.Codec;
import com.tank.share.util.MessageDecoder;
import com.tank.share.util.MessageEncoder;
import com.tank.share.util.PacketDecoder;
import com.tank.time.handler.server.LoginReqHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.util.AttributeKey;
import lombok.NonNull;
import lombok.val;

import java.util.logging.Logger;

/**
 * @author tank198435163.com
 */
public class ServerCreator {

  public static ServerCreator creatorInstance() {
    return SERVER_CREATOR;
  }

  public void start(@NonNull final Integer port) {

    if (port.compareTo(0) <= 0) {
      throw new IllegalArgumentException("port not allowed less than zero");
    }

    val bossGroup = new NioEventLoopGroup();
    val workerGroup = new NioEventLoopGroup();
    val serverBootStrap = new ServerBootstrap();
    val channelFuture = serverBootStrap
            .group(bossGroup, workerGroup)
            .channel(NioServerSocketChannel.class)
            .option(ChannelOption.SO_BACKLOG, 1024)
            .attr(AttributeKey.valueOf("codeC"), Codec.instance())
            .childHandler(new ChannelInitializer<SocketChannel>() {
              @Override
              protected void initChannel(@NonNull final SocketChannel ch) throws Exception {
                val pipeline = ch.pipeline();
                pipeline.addLast(new MessageDecoder());
                pipeline.addLast(new PacketDecoder());
                pipeline.addLast(new MessageEncoder());
                pipeline.addLast(new LoginReqHandler());

              }
            });

    try {
      val listenerFuture = channelFuture
              .bind(port)
              .sync()
              .addListener(future -> {
                val message = future.isSuccess() ? StrUtil.format("listening port:[{}] success", port) : StrUtil.format("listening port:[{}] failure", port);
                logger.info(message);
              });

      listenerFuture
              .channel()
              .closeFuture()
              .sync();

    } catch (Exception e) {
      logger.warning(StrUtil.format("server listening port: [{}] failure, error message is [{}]", port, e.getMessage()));
    } finally {
      bossGroup.shutdownGracefully();
      workerGroup.shutdownGracefully();
    }
  }

  private final Logger logger = Logger.getLogger(ServerCreator.class.getSimpleName());

  private static final ServerCreator SERVER_CREATOR = new ServerCreator();
}
