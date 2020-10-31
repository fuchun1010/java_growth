package com.tank.algorithm.sort;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tank198435163.com
 */
public class SelectionSortingTest {

  @Test
  public void testSort() {
    val selectionSort = new SelectionSorting();
    int[] target = {29, 10, 14, 37, 20, 25, 44, 15};
    int[] result = selectionSort.sort(target);
    Assert.assertEquals(result[0], 10);
    Assert.assertEquals(result[1], 14);
  }

}