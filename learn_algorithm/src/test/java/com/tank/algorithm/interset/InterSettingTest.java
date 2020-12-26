package com.tank.algorithm.interset;

import com.google.common.base.Objects;
import com.google.common.collect.Sets;
import lombok.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

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
    Assert.assertNotNull(result);
    Assert.assertEquals(2, result.size());
    val segment1 = result.poll();
    Assert.assertNotNull(segment1);
    Assert.assertEquals(2, segment1.size());
  }

  @Test
  public void innerTest() {
    val p1 = new Person("lisi", 44);
    val p2 = new Person("wangwu", 14);
    val p3 = new Person("caocao", 51);
    val left = InterSetting.splitBuckets(Arrays.asList(p1, p2, p3), Person.class);

    val p4 = new Person("guanyu", 44);
    val p5 = new Person("zhangfei", 14);
    val right = InterSetting.splitBuckets(Arrays.asList(p4, p5), Person.class);

    val resultWrapper = InterSetting.inner(left, right);

    Assert.assertNotNull(resultWrapper);
    val result = resultWrapper.iterator().hasNext() ? resultWrapper.iterator().next(): Sets.<Person>newHashSet();
    Assert.assertEquals(2, result.size());

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