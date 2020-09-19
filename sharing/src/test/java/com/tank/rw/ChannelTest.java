package com.tank.rw;

import lombok.*;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.function.BiConsumer;

/**
 * @author tank198435163.com
 */
public class ChannelTest {

  @Test
  public void testRReadWithChannel() {
    this.doWithFileChanel((file, channel) -> {
      val buffer = ByteBuffer.allocate(new Long(file.length()).intValue());
      try {
        channel.read(buffer);
      } catch (final Exception ex) {
        ex.printStackTrace();
      }
      val result = buffer.array();
      System.out.println(new String(result));
    });
  }

  @Test
  public void testCopyFileWithChannel() {
    this.doWithFileChanel((file, channel) -> {
      val buffer = ByteBuffer.allocate(new Long(file.length()).intValue());
      try {
        channel.read(buffer);
      } catch (final Exception ex) {
        ex.printStackTrace();
      }
      val result = buffer.array();
      System.out.println(System.getProperty("user.dir"));
      val targetFile = new File(this.createResource().getParent() + "/target.txt");
      try {
        @Cleanup val out = new FileOutputStream(targetFile);
        channel.transferTo(0, result.length, out.getChannel());
        System.out.println("....copy success....");
      } catch (final Exception e) {
        e.printStackTrace();
      }

    });
  }


  @Test
  public void testReadByte() {
    this.doWithFileChanel(((file, channel) -> {
      val length = new Long(file.length()).intValue();
      val buffer = ByteBuffer.allocateDirect(length);
      try {
        channel.read(buffer);
      } catch (final IOException e) {
        e.printStackTrace();
      }
      buffer.flip();
      val result = new byte[length];
      var i = 0;
      while (buffer.hasRemaining()) {
        result[i] = buffer.get();
        i++;
      }
      System.out.println(new String(result));
    }));
  }

  @SneakyThrows
  private void doWithFileChanel(@NonNull final BiConsumer<File, FileChannel> consumer) {
    val file = this.createResource();
    @Cleanup val in = new FileInputStream(file);
    @Cleanup val readChannel = in.getChannel();
    consumer.accept(file, readChannel);
  }

  private File createResource() {
    return this.createResource("test.txt");
  }

  private File createResource(@NonNull final String resourceName) {
    val url = Thread.currentThread().getContextClassLoader().getResource(resourceName);
    Assert.assertNotNull(url);
    return new File(url.getFile());
  }

}
