package com.tank.practice.chain;

import com.tank.practice.chain.req.MoneyRequest;
import lombok.NonNull;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class CeoChain extends AbstractedApprovedChain<MoneyRequest> {

  @Override
  protected boolean isAllowed(@NonNull MoneyRequest data) {
    return true;
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.getClass().getSimpleName());
  }

  @Override
  public boolean equals(Object obj) {
    return this.getClass().getSimpleName().equals(obj.getClass().getSimpleName());
  }

  @Override
  public String toString() {
    return "Ceo";
  }
}
