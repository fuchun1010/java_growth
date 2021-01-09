package com.tank.algorithm.sort;

import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tank198435163.com
 */
class SelectionSortingTest {

  @Test
  void testSort() {
    val selectionSort = new SelectionSorting<Integer>();
    Integer[] target = {29, 10, 14, 37, 20, 25, 44, 15};
    Integer[] result = selectionSort.sort(target);
    assertEquals(result[0].intValue(), 10);
  }

}