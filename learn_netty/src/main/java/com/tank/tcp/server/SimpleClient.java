package com.tank.tcp.server;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
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
              protected void initChannel(Channel ch) throws Exception {
                ch.pipeline().addLast(new StringEncoder());
              }
            });
    val channel = bootstrap.connect("localhost", 9000).channel();
    for (; ; ) {
      channel.writeAndFlush(StrUtil.format("date:[{}]", DateUtil.now()));
    }
  }
}
