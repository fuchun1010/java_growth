package com.tank.algorithm.datastructure;

import cn.hutool.core.util.StrUtil;
import lombok.*;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class DataStructureForChain<T> {

  public DataStructureForChain() {
    this(null);
  }

  public DataStructureForChain(final T data) {
    super();
    val node = new Node<T>(null, data, null);
    this.head = this.tail = node;
  }


  public DataStructureForChain<T> addNode(@NonNull T data) {
    val node = new Node<T>(null, data, null);
    node.pre = this.tail;
    this.tail.next = node;
    this.tail = node;
    counter.incrementAndGet();
    return this;
  }

  public void deletedNode(@NonNull final T data) {

    Node<T> tmpHead = this.obtainHead();

    while (tmpHead != null) {
      tmpHead = tmpHead.next;

      if (tmpHead.data.equals(data)) {
        counter.decrementAndGet();
        val preNode = tmpHead.pre;
        if (tmpHead.next == null) {
          preNode.next = null;
          tmpHead.pre = null;
          return;
        }
        tmpHead.next.pre = preNode;
        preNode.next = tmpHead.next;
        tmpHead.next = null;
        tmpHead.pre = null;
        return;
      }
    }

  }

  public void print() {
    Node<T> tmpHead = this.obtainHead();

    while (tmpHead.next != null) {
      if (tmpHead.data == null) {
        tmpHead = tmpHead.next;
        continue;
      }
      System.out.println(StrUtil.format("data value:[{}]", tmpHead.data.toString()));
      tmpHead = tmpHead.next;
    }

    System.out.println(StrUtil.format("data value:[{}]", tmpHead.data.toString()));
  }

  public int size() {
    return this.counter.get();
  }

  @SneakyThrows({IllegalAccessException.class})
  public T deleteHead() {
    counter.decrementAndGet();
    Node<T> nextNode = head.next;
    if (nextNode == null) {
      throw new IllegalAccessException("can't access empty data");
    }
    if (nextNode.next != null) {
      head.next = nextNode.next;
      nextNode.next.pre = head;
      nextNode.next = null;
      nextNode.pre = null;
    } else {
      head.next.pre = null;
      head.next = null;
    }
    return nextNode.data;
  }

  public T deletedTail() {
    counter.decrementAndGet();
    val lastNode = this.obtainTail();
    val preNode = lastNode.pre;
    preNode.next = null;
    lastNode.pre = null;
    return lastNode.data;
  }

  private Node<T> obtainHead() {
    return this.head;
  }

  private Node<T> obtainTail() {
    return this.tail;
  }

  @Getter
  @Setter
  private static class Node<T> {

    public Node(Node<T> pre, final T data, Node<T> next) {
      this.pre = pre;
      this.data = data;
      this.next = next;
    }

    private Node<T> pre;
    private Node<T> next;
    private T data;
  }


  private Node<T> head;

  private Node<T> tail;

  private final AtomicInteger counter = new AtomicInteger(0);

}