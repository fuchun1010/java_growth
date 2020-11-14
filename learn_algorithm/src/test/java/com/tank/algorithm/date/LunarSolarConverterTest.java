package com.tank.algorithm.date;

import org.junit.Test;

public class LunarSolarConverterTest {

  @Test
  public void lunarToSolar() {
    Lunar lunar = new Lunar();
    lunar.setIsleap(false);
    lunar.setLunarDay(30);
    lunar.setLunarMonth(12);
    lunar.setLunarYear(2019);
    Solar result = LunarSolarConverter.LunarToSolar(lunar);
    System.out.println(result.toDateStr());
  }
}