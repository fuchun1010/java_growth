package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author tank198435163.com
 */
public class DataStructureQueueTest {

  @Test
  public void pushTest() {
    this.queue.push(1);
    this.queue.push(2);
    Assert.assertEquals(this.queue.size(), 2);
  }

  @Test
  public void popTest() {
    this.queue.push(1);
    val result = this.queue.pop();

  }


  @Before
  public void initialize() {
    this.queue = new DataStructureForQueue<>();
  }

  private DataStructureForQueue<Integer> queue;
}