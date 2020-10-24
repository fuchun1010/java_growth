package com.tank.pattern;

import com.tank.pattern.meditor.Action;
import com.tank.pattern.meditor.Mediator;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class MediatorTest {

  @Test
  public void testStock() {
    val mediator = new Mediator();
    val result = mediator.execute(Action.STOCK_INCREMENT, 20);
    Assert.assertTrue(Objects.nonNull(result));
    Assert.assertEquals(0, result.compareTo(120));
  }

  @Test
  public void testSells() {
    val mediator = new Mediator();
    val result = mediator.execute(Action.SALES, 20);
    Assert.assertNotNull(result);
    Assert.assertEquals(0, result.compareTo(80));
  }
}
