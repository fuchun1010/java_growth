package com.tank.share.constants;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public enum SerialCommand {

  JOSN(1, "json序列化");

  SerialCommand(@NonNull final Integer command, @NonNull final String desc) {
    this.command = command;
    this.desc = desc;
  }

  private Integer command;

  private String desc;
}
