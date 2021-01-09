package com.tank.algorithm.datastructure;

import lombok.*;

import java.util.List;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DataStructureForTree<T> {

  @SneakyThrows
  public Node<T> createBinaryNode(@NonNull final List<T> inputs) {
    if (inputs.isEmpty()) {
      return null;
    }
    Node<T> node = null;
    val data = inputs.remove(0);

    if (data != null) {
      node = new Node<>(data);
      node.left = this.createBinaryNode(inputs);
      node.right = this.createBinaryNode(inputs);
    }


    return node;
  }


  @Getter
  @Setter
  public static class Node<T> {

    public Node(@NonNull final T data) {
      this.data = data;
    }

    private Node<T> left;

    private T data;

    private Node<T> right;
  }

  private String sayHello() {
    return "hello";
  }
}
