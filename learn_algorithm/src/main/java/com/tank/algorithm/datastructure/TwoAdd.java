package com.tank.algorithm.datastructure;

import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class TwoAdd {

  public int[] differIndex(@NonNull final int[] arr, final int data) {
    //1, 2, 3, 7, 11
    val collections = Maps.<Integer, Integer>newHashMap();
    int[] result = new int[2];
    int length = arr.length;
    for (int index = 0; index < length; index++) {
      val value = arr[index];
      val diff = data - value;
      if (collections.containsKey(diff)) {
        result[0] = collections.get(diff);
        result[1] = index;
        return result;
      } else {
        collections.put(value, index);
      }
    }
    return result;
  }

}
