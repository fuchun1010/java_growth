package com.tank.algorithm.vavr;

import io.vavr.collection.Stream;
import io.vavr.control.Option;
import lombok.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
public class RichCollectionTest {

  @Test(timeout = 300)
  public void groupByTest() {
    val p1 = new Person("driver", "lisi");
    val p2 = new Person("teacher", "wangwu");
    val p3 = new Person("teacher", "caocao");
    val result = Stream.of(p1, p2, p3).groupBy(Person::getJob);
    Assert.assertNotNull(result);
    val teachers = result.get("teacher").flatMap(list -> Option.some(list.toList().size())).getOrElse(10);
    Assert.assertEquals(0, teachers.compareTo(2));
  }

  @Test(timeout = 100)
  public void testRandom() {
    Assert.assertNotNull(this.random);
    val result = IntStream.range(0, 10).boxed().map(index -> this.random.nextInt(100)).collect(Collectors.toList());
    Assert.assertTrue(!result.isEmpty());
  }

  @Before
  public void init() {
    this.random = ThreadLocalRandom.current();
  }


  private ThreadLocalRandom random = null;

  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Person {
    private String job;
    private String name;
  }
}
