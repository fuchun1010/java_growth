package com.tank.net;

import com.tank.net.server.NioServer;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class NioServerApp {
  public static void main(String[] args) {
    val server = new NioServer(7000);
    server.start();
  }
}
