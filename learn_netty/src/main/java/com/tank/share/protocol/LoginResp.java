package com.tank.share.protocol;

import com.tank.share.constants.SerialCommand;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
public class LoginResp implements Serial {

  @Override
  public Byte commandType() {
    return SerialCommand.defaultSerialCommandValue().getCommand();
  }

  private String words;
}
