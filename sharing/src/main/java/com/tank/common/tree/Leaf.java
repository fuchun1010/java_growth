package com.tank.common.tree;

import com.google.common.base.MoreObjects;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @param <T>
 * @author tank198435163.com
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Leaf<T> implements TreeNode<T> {

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this).add("Leaf", this.data().toString()).toString();
  }

  @Setter
  private T data;

  @Override
  public T data() {
    return this.data;
  }
}
