package com.tank.net;

import org.junit.Test;

import java.util.ArrayDeque;

public class DequeueTest {

  @Test
  public void testPop() {
    ArrayDeque<String> arrayDeque = new ArrayDeque<>();
    arrayDeque.push("1");
    arrayDeque.push("2");
    arrayDeque.push("3");

    arrayDeque.remove();
    System.out.println(arrayDeque.size());
  }
}
