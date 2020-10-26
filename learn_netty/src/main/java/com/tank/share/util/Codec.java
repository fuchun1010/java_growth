package com.tank.share.util;

import cn.hutool.json.JSONUtil;
import com.tank.share.constants.SerialCommand;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.nio.charset.StandardCharsets;

import static com.tank.share.constants.SerialCommand.JOSN;

/**
 * @author tank198435163.com
 */
public class Codec {

  @SneakyThrows
  public <T> byte[] serial(@NonNull SerialCommand serialCommand,
                           @NonNull final T data) {

    if (serialCommand == JOSN) {
      return JSONUtil.toJsonStr(data).getBytes();
    }
    return null;
  }

  public <T> T deSerial(@NonNull final byte[] payload,
                        @NonNull final Class<T> clazz,
                        @NonNull SerialCommand serialCommand) {
    if (serialCommand == JOSN) {
      return JSONUtil.toBean(new String(payload, StandardCharsets.UTF_8), clazz);
    }
    return null;
  }

  public static Codec instance() {
    return INSTANCE;
  }

  private Codec() {
  }

  private static final Codec INSTANCE = new Codec();

}
