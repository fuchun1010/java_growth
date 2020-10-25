package com.tank.share.util;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;

/**
 * @author tank198435163.com
 */
public class PacketDecoder extends LengthFieldBasedFrameDecoder {

  public PacketDecoder() {
    super(Integer.MAX_VALUE, 7, 4);
  }

  @Override
  protected Object decode(ChannelHandlerContext ctx, ByteBuf in) throws Exception {
    //TODO check magic number
    return super.decode(ctx, in);
  }

}
