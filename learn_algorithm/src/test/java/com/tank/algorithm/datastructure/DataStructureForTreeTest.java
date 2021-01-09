package com.tank.algorithm.datastructure;

import com.alibaba.testable.core.accessor.PrivateAccessor;
import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import com.google.common.collect.Lists;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author tank198435163.com
 */
@EnablePrivateAccess
class DataStructureForTreeTest {

  @Test
  void createBinaryNodeTest() {
    val list = Lists.<Integer>newLinkedList();
    Stream.of(2, 1, 7, 4).forEach(list::add);
    val node = tree.createBinaryNode(list);
    assertNotNull(node);
  }

  @Test
  void testSayHello() {
    val result = PrivateAccessor.invoke(this.tree, "sayHello");
    Assertions.assertEquals("hello", result);
  }

  private DataStructureForTree<Integer> tree = new DataStructureForTree<Integer>();

}