package com.tank.practice.service.impl;

import com.tank.practice.service.UserDao;

import java.util.Arrays;
import java.util.List;

/**
 * @author tank198435163.com
 */
public class UserDaoImpl implements UserDao {

  @Override
  public List<String> listUsers() {
    return Arrays.asList("jack", "john", "tank");
  }

}
