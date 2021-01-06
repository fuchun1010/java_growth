package com.tank.algorithm.interset;

import com.google.common.collect.Queues;
import com.google.common.collect.Sets;
import lombok.NonNull;
import lombok.experimental.UtilityClass;
import lombok.val;

import java.util.*;

/**
 * @author tank198435163.com
 */
@UtilityClass
public class InterSetting {

  public <T extends Comparator<T>> Queue<Collection<T>> splitBuckets(@NonNull final List<T> lists, @NonNull final Class<T> clazz) {
    lists.sort((o1, o2) -> o1.compare(o1, o2));
    val queues = Queues.<Collection<T>>newArrayDeque();
    Set<T> segment = Sets.newLinkedHashSet();
    for (final T result : lists) {
      segment.add(result);
      if (segment.size() == 2) {
        queues.add(segment);
        segment = Sets.newLinkedHashSet();
      }
    }

    if (!segment.isEmpty()) {
      queues.add(segment);
    }

    return queues;
  }

  public <T> Set<T> inner(@NonNull final Collection<T> left, @NonNull final Collection<T> right) {
    val leftSet = new HashSet<>(left);
    val rightSet = new HashSet<>(right);
    return Sets.intersection(leftSet, rightSet);
  }

  public <T> Set<T> differ(@NonNull final Collection<T> left, @NonNull final Collection<T> right) {
    val leftSet = new HashSet<>(left);
    val rightSet = new HashSet<>(right);
    return Sets.difference(leftSet, rightSet);
  }


}
