package com.tank.share.constants;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public enum SerialCommand {

  JOSN(Byte.valueOf("1"), "json序列化");

  public static Byte defaultSerialCommandValue() {
    return JOSN.getCommand();
  }

  SerialCommand(@NonNull final Byte command, @NonNull final String desc) {
    this.command = command;
    this.desc = desc;
  }

  @Getter
  private Byte command;

  @Getter
  private String desc;
}
