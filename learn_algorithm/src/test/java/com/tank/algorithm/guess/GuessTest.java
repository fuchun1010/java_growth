package com.tank.algorithm.guess;

import com.google.common.collect.Sets;
import lombok.val;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author tank198435163.com
 */
class GuessTest {

  @Test
  void testMerge() {
    val users = Sets.newHashSet("jack", "john");
    val tmpUsers = Sets.newHashSet("jack");
    users.addAll(tmpUsers);
    assertEquals(users.size(), 2);
  }

}
