package com.tank.pattern.meditor;

import lombok.Getter;
import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public enum Action {

  SALES(1, "销售"),
  OFF_SALES(2, "折扣销售"),
  PURCHASE(3, "采购"),
  CLEANUP(4, "清除库存"),
  STOCK_REMAINING(5, "当前库存"),
  STOCK_INCREMENT(6, "添加库存");

  Action(@NonNull final Integer op, @NonNull final String desc) {
    this.op = op;
    this.desc = desc;
  }

  @Getter
  private Integer op;

  private String desc;
}
