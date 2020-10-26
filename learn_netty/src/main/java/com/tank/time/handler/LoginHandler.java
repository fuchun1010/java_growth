package com.tank.time.handler;

import com.tank.share.protocol.LoginReq;
import com.tank.share.protocol.LoginRes;
import com.tank.share.util.PacketUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class LoginHandler extends SimpleChannelInboundHandler<LoginReq> {

  @Override
  public void channelRead0(ChannelHandlerContext ctx, LoginReq msg) throws Exception {
    val username = msg.getUsername();
    val password = msg.getPassword();
    val isOk = "jack".equalsIgnoreCase(username) && "123456".equalsIgnoreCase(password);
    if (isOk) {
      val loginRes = new LoginRes();
      loginRes.setWords("hello,client");
      val packet = PacketUtil.instance().toSimplePacket(loginRes);
      ctx.writeAndFlush(packet);
    } else {
      ctx.close();
    }
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    cause.printStackTrace();
    ctx.close();
  }
}
