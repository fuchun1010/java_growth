package com.tank.algorithm.date;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Solar {

  public String toDateStr() {
    return StrUtil.format("{}-{}-{}", this.solarYear, this.solarMonth, this.solarDay);
  }

  public int solarDay;
  public int solarMonth;
  public int solarYear;


}
