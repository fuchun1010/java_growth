package com.tank.tcp.util;

import com.tank.tcp.protocol.Customer;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.time.Duration;
import java.time.Instant;

public class CodecTest {

  @Test
  public void toPacket() {
    val start = Instant.now();
    val result = Codec.toObjectBytes(this.customer);
    val packet = Codec.toPacket(Command.LOGIN, result);
    Assert.assertNotNull(packet);
    Assert.assertNotNull(packet.getData());
    Assert.assertTrue(packet.getData().length > 0);
    System.out.println(packet.getData().length);
    val end = Instant.now();
    System.out.println(Duration.between(start, end).toMillis());
  }

  @Test
  public void toObjectBytes() {
    val result = Codec.toObjectBytes(this.customer);
    Assert.assertNotNull(result);
    Assert.assertTrue(result.length > 0);
    System.out.println(result.length);
  }

  @Before
  public void init() {
    this.customer = new Customer();
    this.customer.setUsername("jack").setPassword("123456");
  }

  private Customer customer;
}