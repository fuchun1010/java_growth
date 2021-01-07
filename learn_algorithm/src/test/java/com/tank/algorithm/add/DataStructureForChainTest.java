package com.tank.algorithm.add;

import lombok.NonNull;
import org.junit.Assert;
import org.junit.Test;

import java.util.function.Consumer;

/**
 * @author tank198435163.com
 */
public class DataStructureForChainTest {


  @Test
  public void sizeTest() {
    this.chain = new DataStructureForChain<>();
    this.chain.addNode(1).addNode(2).addNode(3);
    int size = this.chain.size();
    Assert.assertEquals(size, 3);
  }

  @Test
  public void deletedNodeInMiddleTest() {
    this.processChain(chain -> {
      chain.deletedNode(2);
      Assert.assertEquals(this.chain.size(), 2);
    });
  }

  @Test
  public void deletedNodeHeadTest() {
    this.processChain(chain -> {
      chain.deletedNode(1);
      Assert.assertEquals(chain.size(), 2);
    });
  }

  @Test
  public void deletedNodeTailTest() {
    this.processChain(chain -> {
      chain.deletedNode(3);
      Assert.assertEquals(chain.size(), 2);
    });
  }

  @Test
  public void deleteHeadTest() {
    this.processChain(chain -> {
      chain.deleteHead();
      Assert.assertEquals(chain.size(), 2);
    });
  }

  private void processChain(@NonNull final Consumer<DataStructureForChain<Integer>> chainConsumer) {
    this.chain = new DataStructureForChain<>();
    this.chain.addNode(1).addNode(2).addNode(3);
    chainConsumer.accept(this.chain);
    Assert.assertNotNull(this.chain);
    this.chain.print();
  }

  private DataStructureForChain<Integer> chain;
}