package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author tank198435163.com
 */
class DataStructureQueueTest {

  @Test
  void pushTest() {
    this.queue.push(1);
    this.queue.push(2);
    assertEquals(this.queue.size(), 2);
  }

  @Test
  void popTest() {
    this.queue.push(1);
    val result = this.queue.pop();
    assertNotNull(result);
  }

  private final DataStructureForQueue<Integer> queue = new DataStructureForQueue<>();
}