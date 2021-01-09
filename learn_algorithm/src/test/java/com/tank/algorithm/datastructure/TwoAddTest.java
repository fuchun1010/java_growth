package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class TwoAddTest {

  @Test
  void testAddTwoSuccess() {
    val result = this.two.differIndex(this.target, 9);
    assertNotNull(result);
    assertEquals(1, result[0]);
    assertEquals(3, result[1]);
  }

  @Test
  void testAddTowFailure() {
    val result = this.two.differIndex(this.target, 16);
    assertNotNull(result);
    assertEquals(0, result[0]);
    assertEquals(0, result[1]);
  }


  private final int[] target = new int[]{1, 2, 3, 7, 11};

  private final TwoAdd two = new TwoAdd();

}