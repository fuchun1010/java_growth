package com.tank.net;

import com.tank.net.client.NioClient;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class NioClientApp {
  public static void main(final String[] args) {
    val client = new NioClient().setIp("localhost").setPort(7000);
    client.echo();
  }
}
