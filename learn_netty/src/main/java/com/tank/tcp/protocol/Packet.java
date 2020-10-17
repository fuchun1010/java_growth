package com.tank.tcp.protocol;

import com.google.common.collect.Maps;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.ByteBufAllocator;
import io.vavr.Function2;
import lombok.*;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

/**
 * @author tank198435163.com
 */
public final class Packet implements Serializable, Cloneable {

  public static Packet instance() {
    return INSTANCE;
  }

  @Transient
  public ByteBuf encode() {
    val byteBuff = ByteBufAllocator.DEFAULT.buffer();
    byteBuff.writeInt(this.magic);
    byteBuff.writeInt(this.version);
    byteBuff.writeInt(this.command);
    byteBuff.writeInt(this.length);
    byteBuff.writeBytes(this.data);
    return byteBuff;
  }

  @Transient
  @SuppressWarnings("unchecked")
  public <T extends Serializable> Optional<T> decode(@NonNull final ByteBuf byteBuf) throws Exception {
    val packet = this.parse2Packet(byteBuf);
    val clazz = this.commandMapping.get(packet.command);
    return this.deSerial(packet.getData(), clazz);
  }

  @Transient

  @SuppressWarnings("unchecked")
  public <T extends Serializable> Optional<T> decode(@NonNull final ByteBuf byteBuf, Function2<byte[], Class<T>, Optional<T>> deSerialFun) throws Exception {
    val packetOpt = this.parse2Packet(byteBuf);
    val clazz = this.commandMapping.get(packetOpt.command);
    return deSerialFun.apply(packetOpt.getData(), clazz);
  }

  private Packet() {
    this.register();
  }

  @Transient
  private Packet parse2Packet(@NonNull final ByteBuf byteBuf) throws Exception {
    val packetArrayLength = byteBuf.readerIndex();
    byte[] packetData = new byte[packetArrayLength];
    byteBuf.readBytes(packetData);
    val packetOpt = this.deSerial(packetData, Packet.class);
    if (!packetOpt.isPresent()) {
      throw new Exception("xxxx");
    }
    return packetOpt.get();
  }


  @Transient
  @SuppressWarnings("unchecked")
  @SneakyThrows({IOException.class, ClassNotFoundException.class})
  private <T extends Serializable> Optional<T> deSerial(@NonNull final byte[] data, @NonNull Class<T> clazz) {
    @Cleanup val byteArray = new ByteArrayInputStream(data);
    @Cleanup val in = new ObjectInputStream(byteArray);
    Object obj = in.readObject();
    if (obj.getClass() == clazz) {
      return Optional.of(((T) obj));
    }

    return Optional.empty();
  }

  @Transient
  public void register() {
    this.commandMapping.put(1, Customer.class);

  }

  @Transient
  public boolean isEmptyCommand() {
    return command == 0;
  }

  private final int magic = 0xfffff;

  private final int version = 1;

  @Getter
  @Setter
  private int command;

  @Getter
  @Setter
  private int length;

  @Getter
  @Setter
  private byte[] data;

  private transient final Map<Integer, Class> commandMapping = Maps.newConcurrentMap();

  private transient static final Packet INSTANCE = new Packet();
}
