package com.tank.ognl.entity;

import cn.hutool.core.util.StrUtil;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author tank198435163.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class Person {

  public void sayHello(@NonNull final Human human) {
    System.out.println(StrUtil.format("human say = {}", human.say()));
  }

  public void sayHello(@NonNull final String word) {
    System.out.println(StrUtil.format("words = {}", word));
  }


}
