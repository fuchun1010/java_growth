package com.tank.algorithm.datastructure;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class TwoAddTest {

  @Test
  public void testAddTwoSuccess() {
    val result = this.two.differIndex(this.target, 9);
    Assert.assertNotNull(result);
    Assert.assertEquals(1, result[0]);
    Assert.assertEquals(3, result[1]);
  }

  @Test
  public void testAddTowFailure() {
    val result = this.two.differIndex(this.target, 16);
    Assert.assertNotNull(result);
    Assert.assertEquals(0, result[0]);
    Assert.assertEquals(0, result[1]);
  }


  private final int[] target = new int[]{1, 2, 3, 7, 11};

  private final TwoAdd two = new TwoAdd();

}