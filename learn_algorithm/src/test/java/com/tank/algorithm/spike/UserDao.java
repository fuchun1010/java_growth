package com.tank.algorithm.spike;

import cn.hutool.core.util.StrUtil;
import io.vavr.collection.Stream;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserDao {

  public List<String> listAllNames() {
    return Arrays.asList("lisi", "wangwu", "zhangsan");
  }


  public String sayHello() {
    return Stream.ofAll(this.listAllNames())
            .map(name -> StrUtil.format("hello:[{}]", name))
            .collect(Collectors.joining(","));

  }

}
