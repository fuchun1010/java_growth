package com.tank.pattern.jdkproxy;

import lombok.NonNull;
import lombok.val;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;
import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class InterfaceInvokedHandler implements InvocationHandler {

  public InterfaceInvokedHandler(@NonNull final Object rawTarget) {
    this.rawTarget = rawTarget;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    if (!this.isEligible()) {
      throw new IllegalArgumentException("");
    }
    System.out.println("======");
    return method.invoke(this.rawTarget, args);
  }


  private boolean isEligible() {
    return Objects.nonNull(this.rawTarget)
            && this.rawTarget.getClass().getInterfaces().length > 0;

  }

  private final Object rawTarget;
}
