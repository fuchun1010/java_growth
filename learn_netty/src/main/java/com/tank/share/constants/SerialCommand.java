package com.tank.share.constants;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public enum SerialCommand {

  JOSN(Byte.valueOf("1"), "json序列化"),
  PROTOSTUFF(Byte.valueOf("2"), "protostuff序列化");

  public static SerialCommand defaultSerialCommandValue() {
    return JOSN;
  }

  public static SerialCommand fetchSerialCommand(@NonNull Byte commandValue) {
    for (SerialCommand serialCommand : SerialCommand.values()) {
      if (serialCommand.getCommand().compareTo(commandValue) == 0) {
        return serialCommand;
      }
    }
    return null;
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
