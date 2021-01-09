package com.tank.algorithm.vavr;

import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tank198435163.com
 */
class RichCollectionTest {

  @Test
  void groupByTest() {
    val p1 = new Person("driver", "lisi");
    val p2 = new Person("teacher", "wangwu");
    val p3 = new Person("teacher", "caocao");
    val result = Stream.of(p1, p2, p3).groupBy(Person::getJob);
    assertNotNull(result);
    val teachers = result.get("teacher").flatMap(list -> Option.some(list.toList().size())).getOrElse(10);
    assertEquals(0, teachers.compareTo(2));
  }

  @Test
  void testRandom() {
    assertNotNull(this.random);
    val result = IntStream.range(0, 10).boxed().map(index -> this.random.nextInt(100)).collect(Collectors.toList());
    assertFalse(result.isEmpty());
  }


  private final ThreadLocalRandom random = ThreadLocalRandom.current();

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Person {
    private String job;
    private String name;
  }
}
