package com.tank.time.handler;

import com.tank.share.constants.MessageType;
import com.tank.share.constants.SerialCommand;
import com.tank.share.protocol.LoginReq;
import com.tank.share.util.Packet;
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
      val packet = new Packet();
      packet.setMessageType(MessageType.HELLO_RES.getType());
      packet.setData("hello,client".getBytes());
      packet.setCommandType(SerialCommand.defaultSerialCommandValue());
      packet.setDataLength(packet.getData().length);
      ctx.writeAndFlush(packet);
    } else {
      ctx.close();
    }
  }
}
