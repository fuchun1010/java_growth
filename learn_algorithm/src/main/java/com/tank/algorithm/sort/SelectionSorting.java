package com.tank.algorithm.sort;

import cn.hutool.core.util.StrUtil;
import lombok.NonNull;

import java.util.Arrays;

/**
 * @author tank198435163.com
 */
public final class SelectionSorting<T extends Comparable<T>> {

  public T[] sort(@NonNull final T[] source) {
    final T[] target = Arrays.copyOfRange(source, 0, source.length);
    int arrLength = target.length - 1;

    for (int i = 0; i < arrLength; i++) {

      int minIndex = i;

      for (int j = i + 1; j < target.length; j++) {
        if (target[minIndex].compareTo(target[j]) > 0) {
          minIndex = j;
        }
      }

      final T tmp = target[i];
      target[i] = target[minIndex];
      target[minIndex] = tmp;

      System.out.println(StrUtil.format("经过第[{}]轮排序,数组变为{}", i + 1, target));
    }

    return target;
  }

}
