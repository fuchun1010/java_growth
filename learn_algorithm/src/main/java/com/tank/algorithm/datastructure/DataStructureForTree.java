package com.tank.algorithm.datastructure;

import lombok.*;

import java.util.List;
import java.util.Objects;

/**
 * @param <T>
 * @author tank198435163.com
 */
public class DataStructureForTree<T> {

  @SneakyThrows
  public TreeNode<T> createBinaryNode(@NonNull final List<T> inputs) {
    if (inputs.isEmpty()) {
      return null;
    }
    TreeNode<T> node = null;
    val data = inputs.remove(0);

    if (data != null) {
      node = new TreeNode<>(data);
      node.left = this.createBinaryNode(inputs);
      node.right = this.createBinaryNode(inputs);
    }


    return node;
  }


  public void preOrder(final TreeNode<T> root) {
    if (Objects.isNull(root)) {
      return;
    }
    System.out.println(root.data);
    this.preOrder(root.left);
    this.preOrder(root.right);
  }

  public void middleOrderTraversal(final TreeNode<T> root) {
    if (Objects.isNull(root)) {
      return;
    }
    this.middleOrderTraversal(root.left);
    System.out.println(root.data);
    this.middleOrderTraversal(root.right);
  }

  public void postOrderTraversal(final TreeNode<T> root) {
    if (Objects.isNull(root)) {
      return;
    }
    this.postOrderTraversal(root.left);
    this.postOrderTraversal(root.right);
    System.out.println(root.data);
  }

  @Getter
  @Setter
  public static class TreeNode<T> {

    public TreeNode(@NonNull final T data) {
      this.data = data;
    }

    private TreeNode<T> left;

    private T data;

    private TreeNode<T> right;
  }

  private String sayHello() {
    return "hello";
  }
}
