package com.tank.practice.chain;

import com.google.common.collect.Lists;
import lombok.NonNull;
import lombok.val;

import java.util.List;

/**
 * @param <I>
 * @param <R>
 * @author tank198435163.com
 */
public class ApprovedChainRegister<I, R extends AbstractedApprovedChain<I>> {


  public ApprovedChainRegister<I, R> addChain(@NonNull final R chain) {
    synchronized (lock) {
      val isExisting = this.chains.contains(chain);
      if (isExisting) {
        throw new IllegalArgumentException("chain existed");
      }
      this.chains.add(chain);
    }
    return this;

  }

  public void removeChain(@NonNull final R chain) {
    synchronized (lock) {
      this.chains.remove(chain);
    }
  }

  public List<R> toChains() {
    synchronized (lock) {
      return this.chains;
    }
  }

  private final List<R> chains = Lists.newArrayList();

  private final byte[] lock = new byte[1];



}
