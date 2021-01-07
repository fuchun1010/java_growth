package com.tank.algorithm.datastructure;

import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

public class LoopQueueTest {

  @Test
  public void enQueue() {
    IntStream.rangeClosed(1, 7).boxed().forEach(this.queue::enQueue);

  }

  @Test
  public void deQueue() {
  }

  @Before
  public void initialize() {
    this.queue = new LoopQueue<>(Integer.class);
  }

  private LoopQueue<Integer> queue;
}