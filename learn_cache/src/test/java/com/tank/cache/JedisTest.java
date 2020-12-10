package com.tank.cache;

import lombok.val;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.JedisPool;

/**
 * @author tank198435163.com
 */
public class JedisTest {

  @Test
  public void testSetEx() {

    try (val conn = this.jedisPool.getResource();) {
      val result = conn.setnx("lock", Thread.currentThread().getName());
      System.out.println(result);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Test
  public void testMultiThreadRead() {
    CacheHandler cacheHandler = CacheHandler.instance();
    Thread t1 = new Thread(() -> {
      val r1 = cacheHandler.readCache("lock");
      System.out.println("Thread: " + Thread.currentThread().getName() + " read: " + r1);
    }, "ThreadA");

    Thread t2 = new Thread(() -> {
      val r2 = cacheHandler.readCache("lock");
      System.out.println("Thread: " + Thread.currentThread().getName() + " read: " + r2);
    }, "ThreadB");

    Thread t3 = new Thread(() -> {
      val r3 = cacheHandler.readCache("lock");
      System.out.println("Thread: " + Thread.currentThread().getName() + " read: " + r3);
    }, "ThreadC");

    t3.start();
    t2.start();
    t1.start();

    for (; ; ) {

    }

  }

  @Before
  public void init() {
    val poolConfig = new GenericObjectPoolConfig();
    poolConfig.setMaxTotal(100);
    poolConfig.setMinIdle(50);
    poolConfig.setTestOnBorrow(true);
    this.jedisPool = new JedisPool(poolConfig, "localhost", 6379);
  }

  private JedisPool jedisPool;

}
