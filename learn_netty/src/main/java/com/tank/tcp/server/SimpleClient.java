package com.tank.tcp.server;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.tank.tcp.handler.client.ConnectedHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class SimpleClient {

  public static void main(String[] args) {
    val bootstrap = new Bootstrap();
    val group = new NioEventLoopGroup();
    bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .handler(new ChannelInitializer<Channel>() {
              @Override
              protected void initChannel(Channel channel) throws Exception {
                channel.pipeline().addLast(new StringEncoder());
                channel.pipeline().addLast(new ConnectedHandler());
              }
            });
    bootstrap
            .connect("localhost", 9000)
            .addListener(future -> {
              if (future.isSuccess()) {
                System.out.println("connect server success");
              } else {
                System.out.println("connect server failure");
              }
            })
            .channel()
            .writeAndFlush(StrUtil.format("date is :", DateUtil.now()))
            .addListener(future -> {
              if (future.isSuccess()) {
                System.out.println("write to server success");
              }
            });
  }
}
