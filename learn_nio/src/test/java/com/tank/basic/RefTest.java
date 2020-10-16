package com.tank.basic;

import lombok.NonNull;
import lombok.val;
import org.junit.Test;

public class RefTest {

  @Test
  public void testBasicSet() {
    val data = 20;
    this.set(data);
    System.out.println(data);
  }

  @Test
  public void testRef1() {
    val text = "hello";
    this.set(text);
    System.out.println(text);
  }

  @Test
  public void testRef2() {
    val sb = new StringBuffer("iphone2");
    this.append(sb);
    System.out.println(sb);
  }


  private void append(@NonNull final StringBuffer sb) {
    sb.append("imac");
  }

  private void set(String text) {
    text = "why";
  }

  private void set(int value) {
    value = 100;
  }

}
