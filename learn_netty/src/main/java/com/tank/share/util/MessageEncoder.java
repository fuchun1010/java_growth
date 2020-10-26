package com.tank.share.util;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import lombok.val;

import static com.tank.share.constants.TimeServerConstants.MAGIC_NUMBER;
import static io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes.VERSION;

/**
 * @author tank198435163.com
 */
public class MessageEncoder extends MessageToByteEncoder<Packet> {
  @Override
  protected void encode(ChannelHandlerContext ctx, Packet msg, ByteBuf out) throws Exception {
    val fixedSize = 10 + msg.getData().length;
    val buff = Unpooled.buffer(fixedSize);
    buff.writeInt(MAGIC_NUMBER);
    buff.writeByte(VERSION);
    buff.writeByte(msg.getCommandType());
    buff.writeInt(msg.getDataLength());
    buff.writeBytes(msg.getData());
    byte[] data = buff.array();
    out.writeBytes(data);
    buff.release();

  }
}
