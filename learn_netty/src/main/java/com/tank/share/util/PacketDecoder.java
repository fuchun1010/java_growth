package com.tank.share.util;

import cn.hutool.json.JSONUtil;
import com.tank.share.constants.MessageType;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.val;

import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @author tank198435163.com
 */
public class PacketDecoder extends ByteToMessageDecoder {

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    //skip magic and version field
    in.skipBytes(5);

    val command = in.readByte();
    val messageType = in.readByte();
    val dataLength = in.readInt();
    val data = new byte[dataLength];

    val opt = MessageType.fetchMessageType(command);
    if (opt.isPresent()) {
      in.writeBytes(data);
      val message = opt.get();
      val jsonStr = new String(data, StandardCharsets.UTF_8);
      val bean = JSONUtil.toBean(jsonStr, message.getClass());
      out.add(bean);
    }

  }
}
