package com.tank.share;

import lombok.Getter;
import lombok.Setter;

import static com.tank.share.constants.TimeServerConstants.MAGIC_NUMBER;
import static io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes.VERSION;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
public abstract class Packet {
  //magic number(int -> 4) + version(byte -> 1) + command number(short -> 2) + data length(int -> 4) + data (bytes)

  private int magic = MAGIC_NUMBER;

  private byte version = VERSION;

  private short command;

  private int dataLength;

  private byte[] data;

}
