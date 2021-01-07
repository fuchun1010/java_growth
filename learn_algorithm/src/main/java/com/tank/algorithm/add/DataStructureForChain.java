package com.tank.algorithm.add;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.val;

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

  private Node<T> obtainHead() {
    return this.head;
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
