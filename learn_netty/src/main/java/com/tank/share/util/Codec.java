package com.tank.share.util;

import cn.hutool.json.JSONUtil;
import com.tank.share.constants.SerialCommand;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.nio.charset.StandardCharsets;

import static com.tank.share.constants.SerialCommand.JOSN;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class Codec {

  @SneakyThrows
  public <T> byte[] serial(SerialCommand serialCommand,
                           @NonNull final T data) {
//    val serialFun = this.serialCommands.get(serialCommand);
//    if (Objects.isNull(serialFun)) {
//      throw new IllegalAccessException(StrUtil.format(" [{}] serial not supported", serialCommand.getDesc()));
//    }
//    val result = serialFun.apply(data);
//    this.serialCommands.clear(); //save some memory
//    return result;
    return null;
  }

  public <R> R deserialize(SerialCommand serialCommand, @NonNull final byte[] payload, @NonNull Class<R> clazz) {

    if (serialCommand == JOSN) {
      val jsonStr = new String(payload, StandardCharsets.UTF_8);
      return JSONUtil.toBean(jsonStr, clazz);
    }

    return null;

  }


}
