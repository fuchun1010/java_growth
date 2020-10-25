package com.tank.share.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.tank.share.constants.SerialCommand;
import io.vavr.Function1;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.nio.charset.StandardCharsets;
import java.util.Map;
import java.util.Objects;

import static com.tank.share.constants.SerialCommand.JOSN;

/**
 * @author tank198435163.com
 */
public class Codec {

  @SneakyThrows
  public byte[] serial(@NonNull SerialCommand serialCommand,
                       @NonNull final Packet packet) {

    val function = serialFunc.get(serialCommand);
    if (Objects.isNull(function)) {
      throw new IllegalAccessException(StrUtil.format("serial type:[] not supported", serialCommand.getCommand()));
    }
    return function.apply(packet);
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
    serialFunc.put(JOSN, packet -> JSONUtil.toJsonStr(packet).getBytes());
  }


  private static final Codec INSTANCE = new Codec();

  private static final Map<SerialCommand, Function1<Packet, byte[]>> serialFunc = Maps.newConcurrentMap();


}
