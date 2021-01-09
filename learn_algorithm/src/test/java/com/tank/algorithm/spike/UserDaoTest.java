package com.tank.algorithm.spike;

import com.alibaba.testable.core.annotation.MockConstructor;
import com.alibaba.testable.core.annotation.MockMethod;
import com.alibaba.testable.core.tool.TestableTool;
import com.alibaba.testable.core.util.TestableUtil;
import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;
import java.util.List;

/**
 * @author tank198435163.com
 */
@EnablePrivateAccess
class UserDaoTest {

  @Test
  void testListAllNames() {
    val result = listAllNames();
    Assertions.assertNotNull(result);
    Assertions.assertEquals(1, result.size());
  }

  @Test
  void testSayHello() {
    val result = sayHello(mockInstance());
    Assertions.assertNotNull(result);
    Assertions.assertFalse(result.isEmpty());
  }

  @MockConstructor
  private static UserDao mockInstance() {
    return new UserDao();
  }


  @MockMethod(targetClass = UserDao.class)
  private static List<String> listAllNames() {
    return Collections.singletonList("jack");
  }


  @MockMethod(targetClass = UserDao.class)
  private static String sayHello(UserDao userDao) {
    val result = userDao.listAllNames();
    System.out.println(result);
    return userDao.sayHello();
  }


}