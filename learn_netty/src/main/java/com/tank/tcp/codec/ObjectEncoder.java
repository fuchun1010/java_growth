package com.tank.tcp.codec;

import com.tank.tcp.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class ObjectEncoder extends MessageToByteEncoder<Packet> {

  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
    val targetBuff = msg.encode();
    val data = new byte[targetBuff.readableBytes()];
    targetBuff.readBytes(data);
    out.writeBytes(data);
  }
}
