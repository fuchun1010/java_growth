package com.tank.algorithm.datastructure;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.lang.reflect.Array;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class LoopQueue<T> {

  public LoopQueue(@NonNull final Class<T> clazz) {
    this(1 << 3, clazz);
  }

  @SuppressWarnings("unchecked")
  public LoopQueue(int capacity, @NonNull final Class<T> clazz) {
    this.array = ((T[]) Array.newInstance(clazz, capacity));
  }

  @SneakyThrows
  public void enQueue(@NonNull final T data) {
    val tail = (this.rear + 1) % this.array.length;

    if (tail == front) {
      throw new IllegalAccessException("queue is full");
    }
    this.array[rear] = data;
    this.rear = tail;
  }

  @SneakyThrows
  public T deQueue() {
    if (rear == front) {
      throw new IllegalAccessException("queue is empty");
    }
    T result = array[front];
    front = (this.front + 1) % this.array.length;
    return result;
  }

  @SneakyThrows
  public int size() {
    return this.array == null ? 0 : this.array.length;
  }


  private int front = 0;

  private int rear = 0;

  private T[] array = null;
}
