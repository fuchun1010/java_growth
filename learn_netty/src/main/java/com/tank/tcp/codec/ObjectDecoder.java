package com.tank.tcp.codec;

import cn.hutool.core.util.StrUtil;
import com.tank.tcp.protocol.Packet;
import com.tank.tcp.util.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.val;

import java.util.List;

/**
 * @author tank198435163.com
 */
public class ObjectDecoder extends MessageToMessageDecoder<ByteBuf> {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf msg, List<Object> out) throws Exception {
    val magic = msg.readInt();
    if (magic != Command.MAGIC) {
      System.out.println("非系统数据不接收");
      ctx.channel().close();
    }
    // confirm enough bytes to read
    //next int version  int dataLength => 2 * 4
    for (; ; ) {
      if (msg.readableBytes() > Packet.instance().minRemainingBytes()) {
        break;
      }
    }
    //skip version field
    msg.skipBytes(4);

    Packet packet = Packet.instance();
    val dataLength = msg.readInt();
    //continue read util data is full
    for (; ; ) {
      if (msg.readableBytes() >= dataLength) {
        break;
      } else {
        System.out.println("not enough bytes");
      }
    }
    final byte[] data = new byte[dataLength];
    msg.readBytes(data);
    val resultOpt = packet.decode(ByteBufAllocator.DEFAULT.buffer().writeBytes(data));
    if (resultOpt.isPresent()) {
      out.add(resultOpt.get());
    } else {
      throw new IllegalArgumentException(StrUtil.format("command [{}] not supported", packet.getCommand()));
    }
  }
}
