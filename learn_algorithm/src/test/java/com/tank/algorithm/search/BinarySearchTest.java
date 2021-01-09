package com.tank.algorithm.search;

import io.vavr.Function3;
import lombok.NonNull;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class BinarySearchTest {

  @Test
  void searchIndex1() {
    val target = 3;
    val index = this.binarySearch.searchIndex(targets, 0, target);
    val expected = 2;
    assertEquals(expected, index);
  }

  @Test
  void searchIndex2() {
    val target = 2;
    val index = this.binarySearch.searchIndex(targets, 0, target);
    val expected = 1;
    assertEquals(expected, index);
  }

  @Test
  void searchIndex3() {
    val target = 5;
    val index = this.binarySearch.searchIndex(targets, 0, target);
    val expected = 4;
    assertEquals(expected, index);
  }

  @Test
  void searchIndex4() {
    val target = 4;
    val expected = 3;
    val result = this.isExisted(this.targets, target, expected, this.binarySearch::searchIndex);
    assertTrue(result);
  }

  private boolean isExisted(@NonNull final int[] targets,
                            @NonNull final int target,
                            @NonNull final Integer expectIndex,
                            Function3<int[], Integer, Integer, Integer> function2) {
    int actualIndex = function2.apply(targets, 0, target);
    return actualIndex == expectIndex;
  }

  private final BinarySearch binarySearch = new BinarySearch();

  private final int[] targets = {1, 2, 3, 4, 5};
}