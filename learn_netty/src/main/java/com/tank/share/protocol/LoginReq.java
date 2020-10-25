package com.tank.share.protocol;

import com.tank.share.constants.MessageType;
import lombok.Getter;
import lombok.Setter;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
public class LoginReq implements ReqSerial {

  @Override
  public Short messageType() {
    return MessageType.LOGIN_REQ.getType();
  }

  private String username;

  private String password;


}
