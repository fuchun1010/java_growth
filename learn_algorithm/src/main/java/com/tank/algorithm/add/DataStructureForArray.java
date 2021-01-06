package com.tank.algorithm.add;

import com.google.common.base.Preconditions;
import lombok.NonNull;

import java.lang.reflect.Array;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class DataStructureForArray<T extends Comparable<T>> {

  @SuppressWarnings("unchecked")
  public DataStructureForArray(int capacity, @NonNull final Class<T> clazz) {
    Preconditions.checkArgument(capacity >= 0, "not allowed less than zero");
    this.clazz = clazz;
    this.capacity = capacity;
    this.arr = ((T[]) Array.newInstance(this.clazz, this.capacity));
  }

  public DataStructureForArray(@NonNull final Class<T> clazz) {
    this(1 << 6, clazz);
  }

  public T[] obtainArray() {
    return this.arr;
  }

  public T index(@NonNull Integer index) {
    this.basicCheck(index);
    return this.arr[index];
  }

  public synchronized void assign(@NonNull Integer index, @NonNull T value) {
    this.basicCheck(index);
    if (Objects.isNull(this.arr[index])) {
      this.counter.incrementAndGet();
    }

    if (lastPosition < index) {
      lastPosition = index;
    }
    this.arr[index] = value;
  }

  @SuppressWarnings("unchecked")
  public synchronized void insert(@NonNull final Integer index, @NonNull final T value) {
    Preconditions.checkArgument(Objects.nonNull(this.arr), "array not initialized");
    Preconditions.checkArgument(index >= 0, "not allowed less than zero");
    int start = this.lastPosition + 1;
    if (this.capacity != this.size()) {
      for (int i = start; i > index; i--) {
        this.arr[i] = this.arr[i - 1];
      }
    } else {
      this.capacity = this.capacity << 1;
      final T[] targetArr = ((T[]) Array.newInstance(this.clazz, this.capacity));
      System.arraycopy(this.arr, 0, targetArr, 0, this.arr.length);
      this.arr = targetArr;
      this.insert(index, value);
    }
    //TODO check latest position
    this.arr[index] = value;

  }

  @SuppressWarnings("unchecked")
  public T[] deleted(@NonNull Integer index) {
    Preconditions.checkArgument(index > 0, "index not allowed less than zero");
    Preconditions.checkArgument(index <= this.capacity, "index not over capacity");

    final T[] result = ((T[]) Array.newInstance(this.clazz, this.capacity - 1));
    final int size = result.length;
    final int deletedIndex = index - 1;

    //TODO check latest position

    for (int i = 0; i < size; i++) {
      if (i < deletedIndex) {
        result[i] = arr[i];
      } else {
        result[i] = arr[i + 1];
      }
    }

    this.arr = result;
    return this.arr;
  }

  public int size() {
    return this.counter.get();
  }


  private void basicCheck(@NonNull Integer index) {
    Preconditions.checkArgument(Objects.nonNull(this.arr), "array not initialized");
    Preconditions.checkArgument(index >= 0, "not allowed less than zero");
    Preconditions.checkArgument(index <= this.arr.length - 1, "not allowed over array index");
  }

  private T[] arr;

  private Class<T> clazz;

  private int capacity;

  private final AtomicInteger counter = new AtomicInteger(0);

  private int lastPosition = -1;

}
