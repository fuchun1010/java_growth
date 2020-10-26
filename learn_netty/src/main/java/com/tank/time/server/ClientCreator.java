package com.tank.time.server;

import cn.hutool.core.util.StrUtil;
import com.tank.share.util.MessageDecoder;
import com.tank.share.util.MessageEncoder;
import com.tank.share.util.PacketDecoder;
import com.tank.time.handler.client.LoginRespHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.NonNull;
import lombok.val;

import java.util.logging.Logger;

/**
 * @author tank198435163.com
 */
public class ClientCreator {

  public void run(@NonNull final String ip, @NonNull final Integer port) {
    val workerGroup = new NioEventLoopGroup();
    val boot = new Bootstrap();
    try {
      val f = boot.group(workerGroup)
              .channel(NioSocketChannel.class)
              .option(ChannelOption.TCP_NODELAY, true)
              .handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel ch) throws Exception {
                  val p1 = ch.pipeline();
                  p1.addLast(new MessageDecoder());
                  p1.addLast(new MessageEncoder());
                  p1.addLast(new PacketDecoder());
                  p1.addLast(new LoginRespHandler());

                }
              });

      f.connect(ip, port)
              .sync()
              .channel()
              .closeFuture()
              .sync()
              .addListener(future -> {
                if (future.isSuccess()) {
                  logger.info(StrUtil.format("client connect server:[{}], port:[{}] success", ip, port));
                } else {
                  logger.info("client connect server failure");
                }
              });

    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      workerGroup.shutdownGracefully();
    }
  }

  private static final Logger logger = Logger.getLogger(ClientCreator.class.getSimpleName());

}
