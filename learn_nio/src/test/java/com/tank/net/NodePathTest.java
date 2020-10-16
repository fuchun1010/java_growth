package com.tank.net;

import com.google.common.collect.Maps;
import io.vavr.Tuple2;
import lombok.val;
import org.junit.Test;

import java.util.Arrays;
import java.util.Map;

/**
 * @author tank198435163.com
 */
public class NodePathTest {

  @Test
  public void testPath() {
    val list = Arrays.asList("A", "B", "C", "D");
    val length = list.size();
    val map = Maps.<String, Tuple2<String, Long>>newHashMap();
    for (int i = 0; i < length; i++) {
      val key = list.get(i);
      if (i == length - 1) {
        map.put(key, new Tuple2<String, Long>(key, 1L));
      } else {
        val childKey = list.get(i + 1);
        map.put(key, new Tuple2<String, Long>(childKey, 1L));
      }
    }

    for (Map.Entry<String, Tuple2<String, Long>> entry : map.entrySet()) {
      System.out.println(entry.toString());
    }

  }

}
