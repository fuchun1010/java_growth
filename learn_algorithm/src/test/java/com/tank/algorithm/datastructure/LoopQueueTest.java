package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class LoopQueueTest {

  @Test
  public void enQueue() {
    IntStream.rangeClosed(1, 7).boxed().forEach(this.queue::enQueue);
    val result = this.queue.deQueue();
    Assert.assertNotNull(result);
    Assert.assertEquals(1, (int) result);
  }
  

  @Before
  public void initialize() {
    this.queue = new LoopQueue<>(Integer.class);
  }

  private LoopQueue<Integer> queue;
}