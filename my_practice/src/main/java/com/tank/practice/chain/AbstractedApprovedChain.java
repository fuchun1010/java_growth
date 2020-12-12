package com.tank.practice.chain;

import io.vavr.CheckedConsumer;
import lombok.NonNull;

/**
 * @param <T>
 * @author tank198435163.com
 */
public abstract class AbstractedApprovedChain<T> {

  public void handleApplication(T data, CheckedConsumer<T> consumer) {
    if (isAllowed(data)) {
      try {
        consumer.accept(data);
      } catch (Throwable throwable) {
        throwable.printStackTrace();
      }
    }
  }

  /**
   *
   * @param data
   * @return
   */
  protected abstract boolean isAllowed(@NonNull final T data);


}
