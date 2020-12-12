package com.tank.practice.chain;

import com.tank.practice.chain.req.MoneyRequest;
import io.vavr.collection.Stream;
import lombok.val;
import org.junit.Assert;
import org.junit.Test;

public class ApprovedChainRegisterTest {

  @Test(expected = IllegalArgumentException.class)
  public void toChains3() {
    val chainRegister = new ApprovedChainRegister<MoneyRequest, AbstractedApprovedChain<MoneyRequest>>();
    val ceoChain = new CeoChain();
    chainRegister.addChain(ceoChain).addChain(ceoChain).toChains();
  }

  @Test
  public void toChains() {
    val chainRegister = new ApprovedChainRegister<MoneyRequest, AbstractedApprovedChain<MoneyRequest>>();
    val ceoChain = new CeoChain();
    val director = new DirectorChain();
    val manger = new ManagerChain();
    val chains = chainRegister.addChain(ceoChain).addChain(director).addChain(manger).toChains();
    val moneyRequest = new MoneyRequest();
    moneyRequest.setMoney(2000);
    Stream.ofAll(chains).forEach(chain -> chain.handleApplication(moneyRequest, c -> System.out.println(chain.toString() + "处理")));
  }

  @Test
  public void toChains2() {
    val chainRegister = new ApprovedChainRegister<MoneyRequest, AbstractedApprovedChain<MoneyRequest>>();
    val ceoChain = new CeoChain();
    val director = new DirectorChain();
    val manger = new ManagerChain();
    val chains = chainRegister.addChain(ceoChain).addChain(director).addChain(manger).toChains();
    Assert.assertEquals(chains.size(), 3);
    chains.remove(ceoChain);
    Assert.assertEquals(chains.size(), 2);
  }


}