package com.tank.share.util;

import com.tank.share.protocol.LoginReq;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class PacketUtilTest {

  @Test
  public void toSimplePacket() {
    val loginReq = new LoginReq();
    loginReq.setPassword("123456");
    loginReq.setUsername("jack");
    val packet = packetUtil.toSimplePacket(loginReq);
    Assert.assertNotNull(packet);
  }

  private final PacketUtil packetUtil = PacketUtil.instance();
}