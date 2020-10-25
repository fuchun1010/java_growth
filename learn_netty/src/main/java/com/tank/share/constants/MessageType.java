package com.tank.share.constants;

import com.tank.share.protocol.ApiResult;
import com.tank.share.protocol.LoginReq;
import lombok.Getter;
import lombok.NonNull;

import java.util.Optional;

/**
 * @author tank198435163.com
 */
public enum MessageType {

  LOGIN_REQ(Byte.valueOf("1"), LoginReq.class), HELLO_RES(Byte.valueOf("2"), ApiResult.class);

  /**
   * fetched mapped clazz of target
   *
   * @param messageType
   * @return
   */
  public static Optional<MessageType> fetchMessageType(@NonNull final Byte messageType) {
    for (MessageType tmpMessageType : messageTypes) {
      if (tmpMessageType.type.compareTo(messageType) == 0) {
        return Optional.ofNullable(tmpMessageType);
      }
    }
    return Optional.empty();
  }

  /**
   * create MessageType enum
   *
   * @param type
   * @param clazz
   */
  MessageType(@NonNull final Byte type,
              @NonNull final Class clazz) {
    this.type = type;
    this.clazz = clazz;
  }

  private static MessageType[] messageTypes = MessageType.values();

  @Getter
  private Byte type;

  @Getter
  private Class clazz;

}
