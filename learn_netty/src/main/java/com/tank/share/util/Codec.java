package com.tank.share.util;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.google.common.collect.Maps;
import com.tank.share.constants.SerialCommand;
import io.vavr.Function1;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.util.Map;
import java.util.Objects;

import static com.tank.share.constants.SerialCommand.JOSN;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class Codec<T> {

  @SneakyThrows
  public byte[] serial(SerialCommand serialCommand,
                       @NonNull final T data) {
    val serialFun = this.command.get(serialCommand);
    if (Objects.isNull(serialFun)) {
      throw new IllegalAccessException(StrUtil.format(" [{}] serial not supported", serialCommand.getDesc()));
    }
    val result = serialFun.apply(data);
    this.command.clear(); //save some memory
    return result;
  }

  public Codec() {
    command.put(JOSN, data -> JSONUtil.toJsonStr(data).getBytes());
  }

  private final Map<SerialCommand, Function1<T, byte[]>> command = Maps.newConcurrentMap();

}
