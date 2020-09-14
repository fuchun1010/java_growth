package com.tank.common.tree;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.Objects;

/**
 * @param <T>
 * @author tank198435163.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Node<T> implements TreeNode<T> {

  public void addChild(@NonNull final TreeNode<T> treeNode) {
    this.children.add(treeNode);
  }

  public void print() {
    this.print(this);
  }

  public List<T> parentPath(@NonNull final T target) {
    return parentPath(target, this, Lists.<T>newArrayList());
  }

  private List<T> parentPath(@NonNull final T target,
                             @NonNull final Node<T> root,
                             @NonNull final List<T> path) {

    path.add(root.data());

    if (root.data().equals(target)) {
      path.add(root.data());
      return path;
    }

    for (TreeNode<T> child : root.getChildren()) {

      if (child.data().equals(target)) {
        path.add(child.data());
        return path;
      }

      val isNode = child instanceof Node;

      if (isNode) {
        final Node<T> tmpNode = ((Node<T>) child);
        val result = parentPath(target, tmpNode, path);
        if (!result.isEmpty()) {
          if (result.contains(target)) {
            return path;
          }
          val tailIndex = result.size() - 1;
          result.remove(tailIndex);
        }
      }

    }

    return path;
  }

  public TreeNode<T> find(@NonNull final T target) {
    return this.find(target, this);
  }

  private TreeNode<T> find(@NonNull final T target, @NonNull final Node<T> root) {
    if (root.data().equals(target)) {
      return root;
    }

    for (TreeNode<T> child : root.getChildren()) {
      if (child.data().equals(target)) {
        return child;
      }
      if (child instanceof Node) {
        final Node<T> tmpNode = ((Node<T>) child);
        TreeNode<T> result = this.find(target, tmpNode);
        if (Objects.nonNull(result)) {
          return result;
        }
      }
    }

    return null;
  }

  private void print(@NonNull final Node<T> node) {
    System.out.println(node.toString());
    for (TreeNode<T> child : node.getChildren()) {
      if (child instanceof Leaf) {
        System.out.println(child.toString());
        continue;
      }
      final Node<T> tmp = ((Node<T>) child);
      this.print(tmp);
    }
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("node", this.data().toString()).toString();
  }

  public boolean isEmpty() {
    return this.children.isEmpty();
  }

  @Getter
  private final List<TreeNode<T>> children = Lists.newArrayList();

  @Setter
  private T data;

  @Override
  public T data() {
    return this.data;
  }
}
