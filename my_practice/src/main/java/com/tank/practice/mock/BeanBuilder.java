package com.tank.practice.mock;

import cn.hutool.core.util.StrUtil;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;

import java.io.IOException;
import java.util.Map;
import java.util.Properties;

/**
 * @author tank198435163.com
 */
class BeanBuilder {

  BeanBuilder() {
    super();
    loadProps();
    this.initFactory();
  }

  protected Object getBean(@NonNull final String id) {
    Preconditions.checkArgument(!StrUtil.isEmptyIfStr(id), "id not allowed empty");
    return factory.get(id.toLowerCase());
  }

  private void loadProps() {
    try (val in = BeanBuilder.class.getClassLoader().getResourceAsStream("factory.properties")) {
      props.load(in);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  @SneakyThrows({
          ClassNotFoundException.class,
          InstantiationException.class,
          IllegalAccessException.class
  })
  private void initFactory() {
    for (Object key : props.keySet()) {
      val id = key.toString();
      String lowerId = id.toLowerCase();
      if (factory.containsKey(lowerId)) {
        continue;
      }
      val classFullName = props.getProperty(id);
      Object instance = Class.forName(classFullName).newInstance();
      this.factory.putIfAbsent(lowerId, instance);
    }
  }


  private final Properties props = new Properties();

  private final Map<String, Object> factory = Maps.newConcurrentMap();

}
