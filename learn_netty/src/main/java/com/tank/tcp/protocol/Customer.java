package com.tank.tcp.protocol;

import cn.hutool.crypto.SecureUtil;
import com.google.common.base.MoreObjects;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @author tank198435163.com
 */

@Accessors(chain = true)
public class Customer implements Serializable {

  @Override
  public String toString() {

    return MoreObjects.toStringHelper(Customer.class)
            .add("username", this.username)
            .add("password", SecureUtil.md5(this.password))
            .toString();
  }

  @Getter
  @Setter
  private String username;

  @Getter
  @Setter
  private String password;


}
