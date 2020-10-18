package com.tank.tcp.util;

/**
 * @author tank198435163.com
 */
public interface Command {

  int MAGIC = 10;

  int LOGIN = 1;

  String END = "\r\n";

  int FIX_PACK_LENGTH = 8192;

}
