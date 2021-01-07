package com.tank.algorithm.datastructure;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public class DataStructureForStack<T> {

  public DataStructureForStack() {
    this.chain = new DataStructureForChain<>();
  }

  public void push(@NonNull final T data) {
    synchronized (this.lock) {
      this.chain.addNode(data);
    }
  }

  public T pop() {
    synchronized (this.lock) {
      return this.chain.deletedTail();
    }
  }

  public void print() {
    this.chain.print();
  }


  public int obtainSize() {
    return this.chain.size();
  }

  private final byte[] lock = new byte[0];

  private DataStructureForChain<T> chain = null;

  private int size;
}
