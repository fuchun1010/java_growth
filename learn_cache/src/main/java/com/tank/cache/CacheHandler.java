package com.tank.cache;


import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import org.apache.commons.pool2.impl.GenericObjectPoolConfig;
import redis.clients.jedis.JedisPool;

/**
 * @author tank198435163.com
 */
public class CacheHandler {

  public static CacheHandler instance() {
    return cacheHandler;
  }


  @SneakyThrows({InterruptedException.class})
  public String readCache(@NonNull final String key) {

    val result = this.readStrKv(key);
    synchronized (read) {
      if (result != null) {
        read.notifyAll();
        return result;
      } else {
        if (this.tryLock(key)) {
          val r1 = this.loadDataToCache(key);
          read.wait();
          return r1;
        } else {
          return this.readCache(key);
        }
      }
    }

  }

  private void unLock(String key) {
    try (val conn = this.jedisPool.getResource()) {
      boolean isOk = conn.get(key).equals(Thread.currentThread().getName());
      if (isOk) {
        conn.del(key);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private boolean tryLock(String key) {
    try (val conn = this.jedisPool.getResource()) {
      return conn.setnx(key, Thread.currentThread().getName()) == 1;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  private String readStrKv(String key) {
    try (val conn = this.jedisPool.getResource()) {
      return conn.get(key);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private CacheHandler() {
    super();
    val poolConfig = new GenericObjectPoolConfig();
    poolConfig.setMaxTotal(100);
    poolConfig.setMinIdle(50);
    poolConfig.setTestOnBorrow(true);
    jedisPool = new JedisPool(poolConfig, "localhost", 6379);
  }

  @SneakyThrows
  private synchronized String loadDataToCache(@NonNull final String key) {
    try (val conn = this.jedisPool.getResource()) {
      conn.setnx(key, Thread.currentThread().getName());
      return conn.get(key);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private static final CacheHandler cacheHandler = new CacheHandler();


  private JedisPool jedisPool = null;

  private final byte[] read = new byte[1];

}
