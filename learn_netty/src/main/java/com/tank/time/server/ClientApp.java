package com.tank.time.server;

import lombok.val;

/**
 * @author tank198435163.com
 */
public class ClientApp {

  public static void main(String[] args) {
    val clientCreator = new ClientCreator();
    clientCreator.run("localhost", TimeServer.PORT);
  }
}
