package com.tank.practice.bit;

import lombok.NonNull;
import lombok.val;
import org.junit.Test;


/**
 * @author tank198435163.com
 */
public class BitSetTest {

  @Test
  public void testXor() {
    System.out.println(1 | 0);
    System.out.println(0 | 0);
    System.out.println(0 | 1);
    System.out.println(1 << 10);
  }

  @Test
  public void testBitMap() {
    val tagBitMap = new TagBitMap();
    int index = 18743;
    tagBitMap.assignedIndex(index);
    val existed = tagBitMap.getBit(index);
    System.out.println(existed);
  }


  private static class TagBitMap {

    public TagBitMap(@NonNull final Integer size) {
      this.size = size;
      this.words = new long[this.obtainIndex(size - 1) + 1];
    }

    public TagBitMap() {
      this(1 << 30);
    }

    public void assignedIndex(@NonNull final Integer index) {
      if (index.compareTo(0) <= 0 || index > this.size - 1) {
        throw new IndexOutOfBoundsException("xx");
      }
      val targetIndex = this.obtainIndex(index);
      words[targetIndex] |= (1L << index);
      System.out.println("index = " + index);
    }

    public boolean getBit(@NonNull final Integer index) {
      if (index.compareTo(0) <= 0 || index > this.size - 1) {
        throw new IndexOutOfBoundsException("xx");
      }
      val targetIndex = this.obtainIndex(index);
      return (words[targetIndex] & (1L << index)) != 0;

    }

    private int obtainIndex(@NonNull final Integer index) {
      return index >> 6;
    }

    private Integer size;
    private long[] words;
  }


}
