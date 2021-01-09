package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.jupiter.api.Test;

/**
 * @author tank198435163.com
 */
class DataStructureForStackTest {

  @Test
  void pushTest() {
    this.stack.push(1);
    this.stack.push(2);
    val result = this.stack.pop();
    assertEquals(2, (int) result);
    assertEquals(1, this.stack.obtainSize());
    this.stack.print();
  }

  private void assertEquals(int i, int result) {
  }

  private final DataStructureForStack<Integer> stack = new DataStructureForStack<>();

}