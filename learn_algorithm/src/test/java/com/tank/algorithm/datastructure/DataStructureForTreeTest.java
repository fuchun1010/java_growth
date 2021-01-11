package com.tank.algorithm.datastructure;

import com.alibaba.testable.processor.annotation.EnablePrivateAccess;
import com.google.common.collect.Lists;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * @author tank198435163.com
 */
@EnablePrivateAccess
class DataStructureForTreeTest {

  @Test
  void createBinaryNodeTest() {
    assertNotNull(this.root);
    assertNotNull(this.root.getLeft());
  }


  @Test
  void preOrderTraversalTest() {
    this.tree.preOrder(this.root);
    assertNotNull(this.root);
  }

  @Test
  void middleOrderTraversalTest() {
    this.tree.middleOrderTraversal(this.root);
    assertNotNull(this.root);
  }

  @Test
  void postOrderTraversalTest() {
    this.tree.postOrderTraversal(this.root);
    assertNotNull(this.root);
  }

  @Test
  void printWithStackTest() {
    this.tree.printWithStack(this.root);
    Assertions.assertNotNull(this.tree);
  }

  @Test
  void traversalByStackTest() {
    Assertions.assertNotNull(this.tree);
    this.tree.printWithStack(this.root);
  }

  @BeforeEach
  void initializerRoot() {
    this.tree = new DataStructureForTree<>();
    val list = Lists.<Integer>newLinkedList();
    Stream.of(3, 2, 9, null, null, 10, null, null, 8, null, 4).forEach(list::add);
    this.root = this.tree.createBinaryNode(list);
  }

  private DataStructureForTree<Integer> tree;

  private DataStructureForTree.SimpleTreeNode<Integer> root;

}