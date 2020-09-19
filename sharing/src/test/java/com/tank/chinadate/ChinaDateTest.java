package com.tank.chinadate;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import lombok.val;
import org.junit.Test;

public class ChinaDateTest {

  @Test
  public void testChineseDate() {
    val todayStr = DateUtil.today();
    val today = DateUtil.parse(todayStr, DatePattern.NORM_DATE_PATTERN);
    val chinaDate = new ChineseDate(today);
    val result = StrUtil.format("{}-{}-{}", chinaDate.getChineseYear(), chinaDate.getChineseMonth(), chinaDate.getChineseDay());
    System.out.println(result);
    System.out.println(chinaDate.toStringNormal());
    System.out.println(chinaDate.getCyclical());
  }

}
