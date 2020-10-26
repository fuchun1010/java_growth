package com.tank.time.handler.client;

import com.tank.share.protocol.LoginReq;
import com.tank.share.util.PacketUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.val;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
public class LoginRespHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    val times = 1;
    val loginReq = new LoginReq();
    loginReq.setUsername("jack");
    loginReq.setPassword("123456");
    IntStream.range(0, times)
            .boxed()
            .map(index -> packetUtil.toSimplePacket(loginReq))
            .forEach(ctx::writeAndFlush);
  }

  @Override
  public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
    System.out.println("read from server");
  }

  private final PacketUtil packetUtil = PacketUtil.instance();
}
