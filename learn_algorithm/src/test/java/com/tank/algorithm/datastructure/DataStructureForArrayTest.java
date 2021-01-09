package com.tank.algorithm.datastructure;

import io.vavr.Function1;
import lombok.val;
import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author tank198435163.com
 */
public class DataStructureForArrayTest {

  @Test
  void assignTest() {
    val index = 0;
    this.dataStructureForArray.assign(index, 7);
    val value = this.dataStructureForArray.index(index);
    assertEquals(7, (int) value);
  }

  @Test
  void sizeTest() {
    this.dataStructureForArray.assign(0, 1);
    int result = this.dataStructureForArray.size();
    assertEquals(1, result);
  }

  @Test
  void deleteTestWithMiddle() {
    val deletedIndex = 3;
    val result = this.processIndex(deletedIndex, this.dataStructureForArray::deleted);
    assertNotNull(result);
    assertEquals(4, (int) result[2]);
  }

  @Test
  void deleteTestWithHead() {
    val deletedIndex = 1;
    val result = this.processIndex(deletedIndex, this.dataStructureForArray::deleted);
    assertNotNull(result);
    assertEquals(7, result.length);
    assertEquals(2, (int) result[0]);
  }

  @Test
  void deleteTestWithTail() {
    val deletedIndex = 7;
    val result = this.processIndex(deletedIndex, this.dataStructureForArray::deleted);
    assertNotNull(result);
    assertEquals(7, result.length);
  }

  @Test
  void testArrayLength() {
    final int[] array = {1, 2, 3};
    assertEquals(4, array.length);
  }

  @Test
  void insertNotFullArrayTest() {
    IntStream.range(1, 10).boxed().forEach(index -> this.dataStructureForArray.assign(index - 1, index));
    val result = this.processIndex(2, index -> {
      this.dataStructureForArray.insert(index, 50);
      return this.dataStructureForArray.obtainArray();
    });
    assertNotNull(result);
  }

  @Test
  void insertFullArrayTest() {
    this.dataStructureForArray = new DataStructureForArray<>(10, Integer.class);
    IntStream.rangeClosed(1, 10).boxed().forEach(index -> this.dataStructureForArray.assign(index - 1, index));
    val result = this.processIndex(2, index -> {
      this.dataStructureForArray.insert(index, 888);
      return this.dataStructureForArray.obtainArray();
    });
    assertNotNull(result);
  }

  private <T> T[] processIndex(int index, Function1<Integer, T[]> consumer) {
    for (int i = 0; i < this.default_capacity; i++) {
      this.dataStructureForArray.assign(i, i + 1);
    }
    return consumer.apply(index);
  }


  private final int default_capacity = 1 << 3;
  private DataStructureForArray<Integer> dataStructureForArray = new DataStructureForArray<>(default_capacity, Integer.class);
}