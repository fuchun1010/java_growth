package com.tank.server;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author tank198435163.com
 */
public final class TimeServer {

  public static void main(String[] args) {
    logger.log(Level.INFO, "TimeServer entry");
  }

  private static final Logger logger = Logger.getLogger(TimeServer.class.getName());
}
