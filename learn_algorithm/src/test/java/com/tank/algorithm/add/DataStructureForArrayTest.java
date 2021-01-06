package com.tank.algorithm.add;

import io.vavr.Function1;
import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.stream.IntStream;

/**
 * @author tank198435163.com
 */
public class DataStructureForArrayTest {

  @Test
  public void assignTest() {
    val index = 0;
    this.dataStructureForArray.assign(index, 7);
    val value = this.dataStructureForArray.index(index);
    Assert.assertEquals(7, (int) value);
  }

  @Test
  public void sizeTest() {
    this.dataStructureForArray = new DataStructureForArray<>(Integer.class);
    this.dataStructureForArray.assign(0, 1);
    int result = this.dataStructureForArray.size();
    Assert.assertEquals(1, result);
  }

  @Test
  public void deleteTestWithMiddle() {
    val deletedIndex = 3;
    val result = this.processIndex(deletedIndex, this.dataStructureForArray::deleted);
    Assert.assertNotNull(result);
    Assert.assertEquals(4, (int) result[2]);
  }

  @Test
  public void deleteTestWithHead() {
    val deletedIndex = 1;
    val result = this.processIndex(deletedIndex, this.dataStructureForArray::deleted);
    Assert.assertNotNull(result);
    Assert.assertEquals(7, result.length);
    Assert.assertEquals(2, (int) result[0]);
  }

  @Test
  public void deleteTestWithTail() {
    val deletedIndex = 7;
    val result = this.processIndex(deletedIndex, this.dataStructureForArray::deleted);
    Assert.assertNotNull(result);
    Assert.assertEquals(7, result.length);
  }

  @Test
  public void testArrayLength() {
    final int[] array = {1, 2, 3};
    Assert.assertEquals(4, array.length);
  }

  @Test
  public void insertNotFullArrayTest() {
    this.dataStructureForArray = new DataStructureForArray<>(Integer.class);
    IntStream.range(1, 10).boxed().forEach(index -> this.dataStructureForArray.assign(index - 1, index));
    val result = this.processIndex(2, index -> {
      this.dataStructureForArray.insert(index, 50);
      return this.dataStructureForArray.obtainArray();
    });
    Assert.assertNotNull(result);
  }

  @Test
  public void insertFullArrayTest() {
    this.dataStructureForArray = new DataStructureForArray<>(10, Integer.class);
    IntStream.rangeClosed(1, 10).boxed().forEach(index -> this.dataStructureForArray.assign(index - 1, index));
    val result = this.processIndex(2, index -> {
      this.dataStructureForArray.insert(index, 888);
      return this.dataStructureForArray.obtainArray();
    });
    Assert.assertNotNull(result);
  }


  @Before
  public void initialize() {

    this.dataStructureForArray = new DataStructureForArray<>(default_capacity, Integer.class);
  }

  private <T> T[] processIndex(int index, Function1<Integer, T[]> consumer) {
    for (int i = 0; i < this.default_capacity; i++) {
      this.dataStructureForArray.assign(i, i + 1);
    }
    return consumer.apply(index);
  }


  private final int default_capacity = 1 << 3;
  private DataStructureForArray<Integer> dataStructureForArray;
}