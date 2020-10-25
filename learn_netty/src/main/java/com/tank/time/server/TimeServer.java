package com.tank.time.server;

/**
 * @author tank198435163.com
 */
public final class TimeServer {

  public static void main(String[] args) {
    ServerCreator.creatorInstance().start(9600);
  }

}
