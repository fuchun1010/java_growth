package com.tank.tcp.codec;

import com.tank.tcp.protocol.Packet;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import lombok.val;

import java.util.List;

import static com.tank.tcp.util.Command.MAGIC;

/**
 * @author tank198435163.com
 */
public class ObjectDecoder extends MessageToMessageDecoder<ByteBuf> {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    val packet = Packet.instance();

    if (in.readableBytes() < BASIC_LENGTH) {
      return;
    }
    int beginIndex = -1;
    while (true) {
      if (in.readInt() == MAGIC) {
        in.markReaderIndex();
        break;
      }
      in.readInt();
      if (in.readableBytes() < BASIC_LENGTH) {
        return;
      }
    }

    //skip version
    if (in.readableBytes() >= packet.minRemainingBytes()) {
      in.skipBytes(4);
    } else {

      return;
    }

    val command = in.readInt();
    packet.setCommand(command);

    val dataLength = in.readInt();
    final byte[] data = new byte[dataLength];
    in.readBytes(data);

    packet.setData(data);

    val resultOpt = packet.decode();
    resultOpt.ifPresent(out::add);

    in.resetReaderIndex();

  }

  private final int BASIC_LENGTH = 4;
}
