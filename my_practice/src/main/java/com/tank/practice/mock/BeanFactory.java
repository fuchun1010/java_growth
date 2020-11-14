package com.tank.practice.mock;

import com.tank.practice.service.UserDao;
import lombok.NonNull;

/**
 * @author tank198435163.com
 */
public class BeanFactory {

  public UserDao createUserDao(@NonNull final String id) {
    return (UserDao) this.beanFactory.getBean(id);
  }


  private final BeanBuilder beanFactory = new BeanBuilder();

}
