package com.tank.tcp.handler.client;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

import java.nio.charset.StandardCharsets;

/**
 * @author tank198435163.com
 */
public class ConnectedHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    if (msg instanceof ByteBuf) {
      final ByteBuf message = ((ByteBuf) msg);
      final byte[] data = new byte[message.readableBytes()];
      message.readBytes(data);
      System.out.println(StrUtil.format("received from server:[{}]", new String(data, StandardCharsets.UTF_8)));
      message.release();
    }

  }
}
