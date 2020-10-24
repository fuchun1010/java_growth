package com.tank.pattern.meditor;

import com.google.common.collect.Maps;
import io.vavr.Function1;
import lombok.NonNull;
import lombok.val;

import java.util.Map;
import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class Mediator extends AbstractMediator {

  public Mediator() {
    super();
    this.functions.put(Action.STOCK_INCREMENT, quality -> {
      super.stock.increment(quality);
      return super.stock.remaining();
    });
    this.functions.put(Action.CLEANUP, quality -> {
      super.stock.decrement(super.stock.remaining());
      return super.stock.remaining();
    });
    this.functions.put(Action.SALES, quality -> {
      super.sales.sellProduction(quality);
      return super.stock.remaining();
    });
  }

  @Override
  public Integer execute(Action action, @NonNull final Integer quality) {
    val function = this.functions.get(action);
    if (Objects.isNull(function)) {
      throw new IllegalArgumentException("not supported action");
    }
    return function.apply(quality);
  }

  private final Map<Action, Function1<Integer, Integer>> functions = Maps.newConcurrentMap();


}
