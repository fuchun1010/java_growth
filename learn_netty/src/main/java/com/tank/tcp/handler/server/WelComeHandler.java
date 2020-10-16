package com.tank.tcp.handler.server;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class WelComeHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    val buffer = ctx.alloc().buffer();
    buffer.writeBytes("welcome connect server".getBytes());
    ctx.channel().writeAndFlush(buffer);
    System.out.println("channelActive");
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("channelRead");
  }

}
