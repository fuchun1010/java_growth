package com.tank.tcp.util;

import com.tank.tcp.protocol.Packet;
import lombok.Cleanup;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * @author tank198435163.com
 */
public class Codec {

  public static Packet toPacket(@NonNull final int command, @NonNull final byte[] data) {
    val packet = Packet.instance();
    packet.setCommand(command);
    packet.setLength(data.length);
    packet.setData(data);
    return packet;
  }

  @SneakyThrows({IOException.class})
  public static <T extends Serializable> byte[] toObjectBytes(@NonNull final T data) {
    @Cleanup val bytesOut = new ByteArrayOutputStream();
    @Cleanup val out = new ObjectOutputStream(bytesOut);
    out.writeObject(data);
    return bytesOut.toByteArray();
  }

}
