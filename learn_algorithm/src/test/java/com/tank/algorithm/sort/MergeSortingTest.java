package com.tank.algorithm.sort;

import lombok.NonNull;
import lombok.val;
import lombok.var;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeSortingTest {

  @Test
  void testDiv() {
    val a = 9;
    val b = 2;
    assertEquals(a / b, 4);
  }


  @Test
  void testMergeSort() {
    final int[] arr = {3, 1, 2, 7, 5};
    final int[] result = this.mergeSorting2.sort(arr);
    final int[] expected = new int[]{1, 2, 3, 5, 7};
    assertEquals(result, expected);
  }


  @Test
  void testMerge() {
    int[] expected = {1, 2, 3, 4, 5};
    int[] result = this.merge(new int[]{1, 3}, new int[]{2, 4, 5});
    assertEquals(expected, result);
  }


  @Test
  void testTryFinally() {
    AtomicBoolean running = new AtomicBoolean(true);
    int counter = 0;
    try {
      while (running.get()) {
        counter++;
        TimeUnit.MILLISECONDS.sleep(10);
        if (counter == 100) {
          throw new Exception("xxx");
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      running.set(false);
      System.out.println("exit");
    }
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


  private final MergeSorting mergeSorting = new MergeSorting();

  private final MergeSorting mergeSorting2 = new MergeSorting();

}