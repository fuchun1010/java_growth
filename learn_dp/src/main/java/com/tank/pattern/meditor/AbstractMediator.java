package com.tank.pattern.meditor;

import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public abstract class AbstractMediator {

  public AbstractMediator() {
    this.stock = new Stock(this);
    this.sales = new Sales(this);
  }


  protected abstract Integer execute(Action action, @NonNull final Integer quality);

  protected final Stock stock;

  protected final Sales sales;
}
