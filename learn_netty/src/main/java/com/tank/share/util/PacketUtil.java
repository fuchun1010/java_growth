package com.tank.share.util;

import cn.hutool.core.util.ReflectUtil;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import static com.tank.share.constants.SerialCommand.defaultSerialCommandValue;

/**
 * @author tank198435163.com
 */
public class PacketUtil {

  @SneakyThrows
  public <T> Packet toSimplePacket(@NonNull final T data) {
    val packet = new Packet();
    val method = ReflectUtil.getMethodByName(data.getClass(), "commandType");
    final Byte command = ((Byte) method.invoke(data));
    packet.setCommandType(command);
    byte[] body = Codec.instance().serial(defaultSerialCommandValue(), data);
    packet.setData(body);
    packet.setDataLength(body.length);
    return packet;
  }

  public static PacketUtil instance() {
    return packetUtil;
  }

  private PacketUtil() {

  }

  private static final PacketUtil packetUtil = new PacketUtil();
}
