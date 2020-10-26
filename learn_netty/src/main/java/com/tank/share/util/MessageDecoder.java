package com.tank.share.util;

import com.tank.share.constants.TimeServerConstants;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.val;

import java.util.logging.Logger;

/**
 * @author tank198435163.com
 */
public class MessageDecoder extends LengthFieldBasedFrameDecoder {

  public MessageDecoder() {
    super(Integer.MAX_VALUE, 6, 4);
  }

  @Override
  public Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    val isNotSystemMessage = in.getInt(in.readerIndex()) != TimeServerConstants.MAGIC_NUMBER;
    if (isNotSystemMessage) {
      logger.info("reject illegal message");
      ctx.close();
      return null;
    }
    return super.decode(ctx, in);
  }

  private final Logger logger = Logger.getLogger(MessageDecoder.class.getSimpleName());

}
