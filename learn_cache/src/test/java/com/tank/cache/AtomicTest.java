package com.tank.cache;

import lombok.val;
import org.junit.Assert;
import org.junit.Test;

import java.util.concurrent.atomic.AtomicBoolean;

public class AtomicTest {

  @Test
  public void testRefreshedOnce() {

    val success = this.refreshed.compareAndSet(false, true);
    Assert.assertTrue(success);

    val failure = this.refreshed.compareAndSet(false, true);
    Assert.assertFalse(failure);
  }

  private final AtomicBoolean refreshed = new AtomicBoolean(false);
}
