package com.tank.algorithm.interset;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import lombok.*;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author tank198435163.com
 */
public class InterSettingTest {

  @Test
  public void splitBucketsTest() {
    val p1 = new Person("lisi", 44);
    val p2 = new Person("wangwu", 14);
    val p3 = new Person("caocao", 51);
    val persons = Arrays.asList(p1, p2, p3);
    val result = InterSetting.splitBuckets(persons, Person.class);
    assertNotNull(result);
    assertEquals(2, result.size());
    val segment1 = result.poll();
    assertNotNull(segment1);
    assertEquals(2, segment1.size());
  }

  @Test
  void innerTest() {
    val p1 = new Person("lisi", 44);
    val p2 = new Person("wangwu", 14);
    val p3 = new Person("caocao", 51);
    val left = InterSetting.splitBuckets(Arrays.asList(p1, p2, p3), Person.class);

    val p4 = new Person("guanyu", 44);
    val p5 = new Person("zhangfei", 14);
    val right = InterSetting.splitBuckets(Arrays.asList(p4, p5), Person.class);

    val resultWrapper = InterSetting.inner(left, right);

    assertNotNull(resultWrapper);
    val result = resultWrapper.iterator().hasNext() ? resultWrapper.iterator().next() : Sets.<Person>newHashSet();
    assertEquals(2, result.size());

  }

  @Test
  void parallelInterSetting() {

  }


  /**
   * @author tank198435163.com
   */
  @Getter
  @Setter
  @AllArgsConstructor
  @NoArgsConstructor
  private static class Person implements Comparator<Person> {
    @Override
    public boolean equals(Object o) {
      if (this == o) return true;
      if (o == null || getClass() != o.getClass()) return false;
      Person person = (Person) o;
      return Objects.equal(age, person.age);
    }

    @Override
    public int hashCode() {
      return Objects.hashCode(age);
    }

    @Override
    public int compare(Person p1, Person p2) {
      return p1.age.compareTo(p2.getAge());
    }

    private String name;
    private Integer age;
  }
}