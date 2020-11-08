package com.tank.algorithm.sort;

import lombok.NonNull;
import lombok.val;
import lombok.var;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

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


  @Test
  public void testMerge() {
    int[] expected = {1, 2, 3, 4, 5};
    int[] result = this.merge(new int[]{1, 3}, new int[]{2, 4, 5});
    Assert.assertEquals(expected, result);
  }


  private int[] merge(@NonNull int[] left, @NonNull int[] right) {
    int[] result = new int[left.length + right.length];

    var index = 0;
    while (left.length > 0 && right.length > 0) {
      if (left[0] < right[0]) {
        result[index] = left[0];
        index++;
        left = Arrays.copyOfRange(left, 1, left.length);
      } else {
        result[index] = right[0];
        index++;
        right = Arrays.copyOfRange(right, 1, right.length);
      }

    }

    while (left.length > 0) {
      result[index] = left[0];
      index++;
      left = Arrays.copyOfRange(left, 1, left.length);
    }

    while (right.length > 0) {
      result[index] = right[0];
      index++;
      right = Arrays.copyOfRange(right, 1, right.length);
    }


    return result;

  }


  private MergeSorting mergeSorting;

  private MergeSorting mergeSorting2;

}