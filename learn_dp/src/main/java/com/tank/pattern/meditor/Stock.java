package com.tank.pattern.meditor;

import lombok.NonNull;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author tank198435163.com
 */
public class Stock {

  public void increment(@NonNull final Integer quantity) {
    if (quantity.compareTo(0) < 0) {
      throw new IllegalArgumentException("quantity not allowed less than zero");
    }
    this.quantity.accumulateAndGet(quantity, Integer::sum);
  }

  public void decrement(@NonNull final Integer quantity) {
    synchronized (this) {
      if (this.remaining().compareTo(quantity) < 0) {
        throw new IllegalArgumentException("not enough stock");
      }
      this.quantity.accumulateAndGet(-quantity, Integer::sum);
    }
  }

  public Integer remaining() {
    return quantity.get();
  }

  Stock(@NonNull final AbstractMediator abstractMediator) {
    this.abstractMediator = abstractMediator;
  }

  private final AtomicInteger quantity = new AtomicInteger(100);

  private final AbstractMediator abstractMediator;
}
