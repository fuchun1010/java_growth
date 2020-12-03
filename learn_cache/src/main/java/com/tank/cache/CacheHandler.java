package com.tank.cache;


import io.vavr.Function1;
import lombok.SneakyThrows;
import lombok.val;

import java.util.Objects;
import java.util.concurrent.locks.ReentrantLock;

public class CacheHandler {

  public static CacheHandler instance() {
    return cacheHandler;
  }


  @SneakyThrows({InterruptedException.class})
  public <R> R retrieve(String key, Function1<String, R> function) {
    val result = function.apply(key);
    if (Objects.nonNull(result)) {
      return result;
    }

    rwLock.tryLock();

    try {

      boolean isLocked = tryLock(key);
      if (isLocked) {
        
      } else {

      }

    } finally {
      rwLock.unlock();
    }

  }

  private boolean tryLock(String key) {
    return false
  }


  private CacheHandler() {
    super();
  }

  private static final CacheHandler cacheHandler = new CacheHandler();

  private ReentrantLock rwLock = new ReentrantLock(false);


}
