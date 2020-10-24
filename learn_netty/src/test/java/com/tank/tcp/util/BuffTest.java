package com.tank.tcp.util;

import io.netty.buffer.ByteBufAllocator;
import io.netty.buffer.Unpooled;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.val;
import org.junit.Test;

public class BuffTest {

  @Test
  public void testIndex() {
    val buff = ByteBufAllocator.DEFAULT.buffer(8);
    buff.writeByte(1);
    buff.writeShort(2);
    buff.writeInt(3);
    buff.writeByte(1);

    val xx = new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 7, 4);

    val f1 = buff.markReaderIndex();
    System.out.println(f1.readerIndex());
    val r1 = buff.readByte();
    System.out.println(buff.readerIndex());
    val r2 = buff.readShort();
    System.out.println(buff.readerIndex());
    val r3 = buff.readInt();
    System.out.println(buff.readerIndex());
    val r4 = buff.readByte();
    System.out.println(buff.readerIndex());
    buff.resetReaderIndex();
    System.out.println(buff.readerIndex());

  }

  @Test
  public void testBuff2() {
    val buff = Unpooled.wrappedBuffer("hello".getBytes());
    val buff2 = ByteBufAllocator.DEFAULT.buffer();
    buff2.writeBytes("hello".getBytes());
    buff.release();
    buff2.release();
    ByteBufAllocator.DEFAULT.ioBuffer();
  }


}
