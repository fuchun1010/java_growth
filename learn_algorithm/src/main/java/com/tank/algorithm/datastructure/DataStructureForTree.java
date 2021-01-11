package com.tank.algorithm.datastructure;

import com.google.common.collect.Queues;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DataStructureForTree<T> {

  @SneakyThrows
  public SimpleTreeNode<T> createBinaryNode(@NonNull final List<T> inputs) {
    if (inputs.isEmpty()) {
      return null;
    }
    SimpleTreeNode<T> node = null;
    val data = inputs.remove(0);

    if (data != null) {
      node = new SimpleTreeNode<>(data);
      node.left = this.createBinaryNode(inputs);
      node.right = this.createBinaryNode(inputs);
    }
    return node;
  }


  public void preOrder(final SimpleTreeNode<T> root) {
    if (Objects.isNull(root)) {
      return;
    }
    System.out.println(root.data);
    this.preOrder(root.left);
    this.preOrder(root.right);
  }

  public void middleOrderTraversal(final SimpleTreeNode<T> root) {
    if (Objects.isNull(root)) {
      return;
    }
    this.middleOrderTraversal(root.left);
    System.out.println(root.data);
    this.middleOrderTraversal(root.right);
  }

  public void postOrderTraversal(final SimpleTreeNode<T> root) {
    if (Objects.isNull(root)) {
      return;
    }
    this.postOrderTraversal(root.left);
    this.postOrderTraversal(root.right);
    System.out.println(root.data);
  }

  public void printWithStack(SimpleTreeNode<T> root) {
    val stack = new Stack<SimpleTreeNode<T>>();
    while (root != null || !stack.isEmpty()) {
      while (root != null) {
        System.out.println("node value = " + root.getData());
        stack.push(root);
        root = root.left;
      }
      if (!stack.isEmpty()) {
        val result = stack.pop();
        root = result.right;
      }
    }
  }

  public void traversalByStack(@NonNull SimpleTreeNode<T> root) {
    val queue = Queues.<SimpleTreeNode<T>>newArrayDeque();

    queue.push(root);

    while (!queue.isEmpty()) {
      val node = queue.pop();
      System.out.println("node data = " + node.getData());
      if (node.left != null) {
        queue.push(node.left);
      }
      if (node.right != null) {
        queue.push(node.right);
      }
    }
  }

  @Getter
  @Setter
  public static class SimpleTreeNode<T> {

    public SimpleTreeNode(@NonNull final T data) {
      this.data = data;
    }

    private SimpleTreeNode<T> left;

    private T data;

    private SimpleTreeNode<T> right;
  }

}
