package com.tank.share;

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
  //magic number(int -> 4) + version(byte -> 1) + messageType number(short -> 2) + data length(int -> 4) + data (bytes)

  @Setter(AccessLevel.PRIVATE)
  private int magic = MAGIC_NUMBER;

  @Setter(AccessLevel.PRIVATE)
  private byte version = VERSION;

  private short messageType;

  private int dataLength;

  private byte[] data;

}
