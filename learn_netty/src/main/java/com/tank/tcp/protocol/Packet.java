package com.tank.tcp.protocol;

import com.google.common.collect.Maps;
import com.tank.tcp.util.Command;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.*;

import java.beans.Transient;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.Optional;

import static com.tank.tcp.util.Command.END;

/**
 * @author tank198435163.com
 */
public final class Packet implements Serializable {

  public static Packet instance() {
    return INSTANCE;
  }

  @Transient
  public <T extends Serializable> ByteBuf encode() {
    val byteBuff = Unpooled.buffer();
    //magic(int) + version(int) + command(int) + dataLength(int) + data(bytes) + end(bytes)
    byteBuff.writeInt(Command.MAGIC);
    byteBuff.writeInt(1);
    byteBuff.writeInt(command);
    byteBuff.writeInt(this.data.length);
    byteBuff.writeBytes(this.data);
    byteBuff.writeBytes(END.getBytes());
    return byteBuff;
  }

  @Transient
  public int minRemainingBytes() {
    return 12;
  }

  @Transient
  @SuppressWarnings("unchecked")
  public <T extends Serializable> Optional<T> decode() throws Exception {
    val clazz = this.fetchClazz();
    return this.deSerial(this.data, clazz);
  }

  private Packet() {
    this.register();
  }

//  @Transient
//  @SuppressWarnings("unchecked")
//  private <T extends Serializable> T parse2Packet(@NonNull final ByteBuf byteBuf) throws Exception {
//    val packetArrayLength = byteBuf.readableBytes();
//    val data = new byte[packetArrayLength];
//    byteBuf.readBytes(data);
//    val packetOpt = this.<T>deSerial(data, this.fetchClazz());
//    if (!packetOpt.isPresent()) {
//      throw new Exception("xxxx");
//    }
//    return (T) packetOpt.get();
//  }


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

  public Class fetchClazz() {
    return this.commandMapping.get(this.command);
  }

//  @Transient
//  public boolean isEmptyCommand() {
//    return command == 0;
//  }

  @Getter
  @Setter
  private int command;

  @Getter
  @Setter
  private byte[] data;

  private transient final Map<Integer, Class> commandMapping = Maps.newConcurrentMap();

  private transient static final Packet INSTANCE = new Packet();
}
