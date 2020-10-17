package com.tank.tcp.handler.server;

import cn.hutool.core.util.StrUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import lombok.NonNull;
import lombok.val;

import java.nio.charset.StandardCharsets;

/**
 * @author tank198435163.com
 */
public class WelComeHandler extends ChannelInboundHandlerAdapter {

  @Override
  public void channelActive(final ChannelHandlerContext ctx) throws Exception {
    val buffer = ctx.alloc().buffer();
    buffer.writeBytes("welcome connect server".getBytes());
    ctx.channel().writeAndFlush(buffer);
    System.out.println("channelActive");
  }


  @Override
  public void channelRegistered(final ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelRegistered");
  }

  @Override
  public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelUnregistered");
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelInactive");

  }

  @Override
  public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {

    System.out.println("channelReadComplete");
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    System.out.println("userEventTriggered");
  }

  @Override
  public void channelWritabilityChanged(ChannelHandlerContext ctx) throws Exception {
    System.out.println("channelWritabilityChanged");
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    System.out.println("exceptionCaught:" + cause.getMessage());
  }

  @Override
  public void channelRead(@NonNull final ChannelHandlerContext ctx, final Object msg) throws Exception {
    if (msg instanceof ByteBuf) {
      final ByteBuf byteBuf = ((ByteBuf) msg);
      byte[] data = this.convertFrom(byteBuf);
      System.out.println(StrUtil.format("from client data: {}", new String(data, StandardCharsets.UTF_8)));
    }
  }

  private byte[] convertFrom(@NonNull final ByteBuf byteBuffer) {
    final byte[] data = new byte[byteBuffer.readableBytes()];
    byteBuffer.readBytes(data);
    byteBuffer.release();
    return data;
  }

}
