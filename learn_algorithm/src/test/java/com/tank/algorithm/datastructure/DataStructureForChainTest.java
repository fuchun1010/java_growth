package com.tank.algorithm.datastructure;

import lombok.NonNull;
import org.junit.jupiter.api.Test;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author tank198435163.com
 */
class DataStructureForChainTest {


  @Test
  void sizeTest() {
    this.chain = new DataStructureForChain<>();
    this.chain.addNode(1).addNode(2).addNode(3);
    int size = this.chain.size();
    assertEquals(size, 3);
  }

  @Test
  void deletedNodeInMiddleTest() {
    this.processChain(chain -> {
      chain.deletedNode(2);
      assertEquals(this.chain.size(), 2);
    });
  }

  @Test
  void deletedNodeHeadTest() {
    this.processChain(chain -> {
      chain.deletedNode(1);
      assertEquals(chain.size(), 2);
    });
  }

  @Test
  void deletedNodeTailTest() {
    this.processChain(chain -> {
      chain.deletedNode(3);
      assertEquals(chain.size(), 2);
    });
  }

  @Test
  void deleteHeadTest() {
    this.processChain(chain -> {
      chain.deleteHead();
      assertEquals(chain.size(), 2);
    });
  }

  private void processChain(@NonNull final Consumer<DataStructureForChain<Integer>> chainConsumer) {
    this.chain = new DataStructureForChain<>();
    this.chain.addNode(1).addNode(2).addNode(3);
    chainConsumer.accept(this.chain);
    assertNotNull(this.chain);
    this.chain.print();
  }

  private DataStructureForChain<Integer> chain;
}