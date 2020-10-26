package com.tank.share.protocol;

import com.tank.share.constants.MessageType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class LoginReq implements Serial {

  @Override
  public Byte commandType() {
    return MessageType.LOGIN_REQ.getType();
  }

  private String username;

  private String password;


}
