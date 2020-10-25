package com.tank.share.protocol;

import lombok.Getter;
import lombok.Setter;

/**
 * @param <T>
 * @author tank198435163.com
 */
@Getter
@Setter
public class ApiResult<T> {

  private int statusCode;

  private short messageType;

  private T payload;
}
