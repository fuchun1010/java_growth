package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tank198435163.com
 */
public class DataStructureForStackTest {

  @Test
  public void pushTest() {
    this.stack.push(1);
    this.stack.push(2);
    val result = this.stack.pop();
    Assert.assertEquals(2, (int) result);
    Assert.assertEquals(1, this.stack.obtainSize());
    this.stack.print();
  }

  @Before
  public void initialize() {
    this.stack = new DataStructureForStack<>();
  }

  private DataStructureForStack<Integer> stack;

}