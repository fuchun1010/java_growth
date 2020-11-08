package com.tank.algorithm.sort;

import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class MergeSortingTest {

  @Test
  public void testDiv() {
    val a = 9;
    val b = 2;
    Assert.assertEquals(a / b, 4);
  }
  

  @Test
  public void testMergeSort() {
    final int[] arr = {3, 1, 2, 7, 5};
    final int[] result = this.mergeSorting2.sort(arr);
    final int[] expected = new int[]{1, 2, 3, 5, 7};
    Assert.assertEquals(result, expected);
  }

  @Before
  public void init() {
    this.mergeSorting = new MergeSorting();
    this.mergeSorting2 = new MergeSorting();
  }

  private MergeSorting mergeSorting;

  private MergeSorting mergeSorting2;

}