package com.tank.algorithm.search;

import lombok.NonNull;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class BinarySearch {

  public int searchIndex(@NonNull final int[] arr, int low, int target) {

    val length = arr.length;

    val mid = (low + length) / 2;

    if (arr[mid] == target) {
      return mid;
    }


    if (arr[mid] < target) {
      int[] tmp = new int[length - mid];
      System.arraycopy(arr, mid, tmp, 0, tmp.length);
      return this.searchIndex(tmp, 0, target) + mid;
    }

    if (arr[mid] > target) {

      int[] tmp = new int[mid - low + 1];
      System.arraycopy(arr, 0, tmp, 0, mid + 1);
      return this.searchIndex(tmp, low, target);
    }


    return -1;

  }

}
