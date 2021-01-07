package com.tank.algorithm.add;

import lombok.NonNull;
import lombok.SneakyThrows;

/**
 * @author tank198435163.com
 */
public class DataStructureForQueue<T> {

  public DataStructureForQueue() {
    this(Integer.MAX_VALUE);
  }

  public DataStructureForQueue(int maxCapacity) {
    this.maxCapacity = maxCapacity;
  }

  @SneakyThrows
  public void push(@NonNull final T data) {
    synchronized (lock) {
      if (this.size() < this.maxCapacity) {
        chain.addNode(data);
      } else {
        throw new IllegalAccessException("full queue");
      }

    }
  }

  @SneakyThrows
  public T pop() {
    synchronized (lock) {
      if (this.size() == 0) {
        throw new IllegalAccessException("empty queue");
      }
      return this.chain.deleteHead();
    }
  }

  public int size() {
    return this.chain.size();
  }

  private int maxCapacity = 0;

  private final byte[] lock = new byte[0];

  private final DataStructureForChain<T> chain = new DataStructureForChain<>();

}
