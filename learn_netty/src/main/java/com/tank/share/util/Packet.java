package com.tank.share.util;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import static com.tank.share.constants.TimeServerConstants.MAGIC_NUMBER;
import static io.netty.handler.codec.memcache.binary.BinaryMemcacheOpcodes.VERSION;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
public final class Packet {
  //magic number(int -> 4) + version(byte -> 1)  + commandType number(byte -> 1) + data length(int -> 4) + data (bytes)

  @Setter(AccessLevel.PRIVATE)
  private int magic = MAGIC_NUMBER;

  @Setter(AccessLevel.PRIVATE)
  private byte version = VERSION;

  private byte commandType;

  private int dataLength;

  private byte[] data;

}
