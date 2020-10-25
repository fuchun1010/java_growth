package com.tank.share.util;

import com.tank.share.constants.MessageType;
import com.tank.share.constants.SerialCommand;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.val;

import java.util.List;

/**
 * @author tank198435163.com
 */
public class PacketDecoder extends ByteToMessageDecoder {

  @Override
  @SuppressWarnings("unchecked")
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    //skip magic and version field
    in.skipBytes(5);

    val command = in.readByte();
    //skip messageType
    in.skipBytes(1);
    val dataLength = in.readInt();
    val data = new byte[dataLength];

    val opt = MessageType.fetchMessageType(command);
    if (opt.isPresent()) {
      in.writeBytes(data);
      val message = opt.get();
      val bean = codec.deSerial(data, message.getClazz(), SerialCommand.fetchSerialCommand(command));
      out.add(bean);
    }

  }

  private final Codec codec = Codec.instance();
}
