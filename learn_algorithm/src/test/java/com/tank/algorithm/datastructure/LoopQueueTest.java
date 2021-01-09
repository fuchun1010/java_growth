package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class LoopQueueTest {

  @Test
  void enQueue() {
    IntStream.rangeClosed(1, 7).boxed().forEach(this.queue::enQueue);
    val result = this.queue.deQueue();
    assertNotNull(result);
    assertEquals(1, (int) result);
  }


  private final LoopQueue<Integer> queue = new LoopQueue<>(Integer.class);
}