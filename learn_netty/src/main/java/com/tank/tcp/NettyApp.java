package com.tank.tcp;

import com.tank.tcp.server.SimpleServer;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class NettyApp {
  public static void main(String[] args) {
    val simpleServer = new SimpleServer();
    simpleServer.start();
  }
}
