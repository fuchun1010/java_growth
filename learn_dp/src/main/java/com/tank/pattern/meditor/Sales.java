package com.tank.pattern.meditor;

import cn.hutool.core.util.StrUtil;
import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public class Sales {

  public void sellProduction(@NonNull final Integer quantity) {
    this.abstractMediator.stock.decrement(quantity);
    System.out.println(StrUtil.format("sells quality:[{}]", quantity));
  }


  Sales(@NonNull final AbstractMediator abstractMediator) {
    this.abstractMediator = abstractMediator;
  }

  private final AbstractMediator abstractMediator;
}
