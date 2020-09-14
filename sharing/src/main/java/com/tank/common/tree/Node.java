package com.tank.common.tree;

import com.google.common.base.MoreObjects;
import com.google.common.collect.Lists;
import lombok.*;
import lombok.experimental.Accessors;

import java.util.Comparator;
import java.util.List;

/**
 * @param <T>
 * @author tank198435163.com
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class Node<T extends Comparator<T>> implements TreeNode {

  public void addChild(@NonNull final TreeNode treeNode) {
    this.children.add(treeNode);
  }

  public void print() {
    System.out.println(this.toString());
    this.print(this);
  }

  private void print(@NonNull final Node<T> node) {

    for (TreeNode child : node.getChildren()) {
      System.out.println(child.toString());
      if (child instanceof Leaf) {
        continue;
      }
      final Node<T> tmp = ((Node<T>) child);
      this.print(tmp);
    }
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("node", this.getData().toString()).toString();
  }

  public boolean isEmpty() {
    return this.children.isEmpty();
  }

  @Getter
  private final List<TreeNode> children = Lists.newArrayList();

  @Getter
  @Setter
  private T data;
}
