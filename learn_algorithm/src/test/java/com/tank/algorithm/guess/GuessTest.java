package com.tank.algorithm.guess;

import com.google.common.collect.Sets;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author tank198435163.com
 */
public class GuessTest {

  @Test
  public void testMerge() {
    val users = Sets.newHashSet("jack", "john");
    val tmpUsers = Sets.newHashSet("jack");
    users.addAll(tmpUsers);
    Assert.assertEquals(users.size(), 2);
  }

}
