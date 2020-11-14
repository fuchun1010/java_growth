package com.tank.practice.mock;

import lombok.val;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class BeanFactoryTest {

  @Test
  public void testCreateUserDao() {
    val userDao = this.beanFactory.createUserDao("userDao");
    Assert.assertNotNull(userDao);
    Assert.assertNotNull(userDao.listUsers());
    Assert.assertEquals(userDao.listUsers().size(), 3);
  }

  @Test
  public void testUserDaoRef() {
    val userDaoA = this.beanFactory.createUserDao("userDao");
    val userDaoB = this.beanFactory.createUserDao("userDao");
    Assert.assertSame(userDaoA, userDaoB);
  }

  @Before
  public void init() {
    this.beanFactory = new BeanFactory();
  }

  private BeanFactory beanFactory = null;
}