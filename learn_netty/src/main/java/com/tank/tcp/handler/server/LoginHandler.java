package com.tank.tcp.handler.server;

import com.tank.tcp.protocol.Customer;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * @author tank198435163.com
 */
public class LoginHandler extends SimpleChannelInboundHandler<Customer> {

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, Customer msg) throws Exception {
    System.out.println(msg.toString());
  }
}
