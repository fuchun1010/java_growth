package com.tank.common.tree;

import com.tank.common.entity.Organization;
import org.junit.Before;
import org.junit.Test;

public class NodeTest {

  @Before
  public void init() {
    //Organization global = new Organization("集团", "global");
    Node<Organization> szpszx = new Node<>(new Organization("深圳配送中心", "szpszx"));
    Node<Organization> jack = new Node<>(new Organization("jack片区", "jack_0001"));
    //Node<Organization> lee = new Node<>(new Organization("lee片区", "lee_0001"));

    Leaf<Organization> store_0001 = new Leaf<>(new Organization("深圳湾店", "s0001"));

    this.root.addChild(szpszx);
    szpszx.addChild(jack);
    jack.addChild(store_0001);
    //szpszx.addChild(lee);

    Node<Organization> gzpszx = new Node<>(new Organization("广州配送中心", "szpszx"));
    Node<Organization> king = new Node<>(new Organization("king片区", "king_0001"));
    //Node<Organization> kite = new Node<>(new Organization("kite片区", "kite_0001"));
    Leaf<Organization> store_0002 = new Leaf<>(new Organization("广州天河店", "s0002"));

    this.root.addChild(gzpszx);
    gzpszx.addChild(king);
    king.addChild(store_0002);
    //gzpszx.addChild(kite);
  }

  @Test
  public void addChild() {

  }

  @Test
  public void print() {
    this.root.print();
  }

  private final Node<Organization> root = new Node<>(new Organization("集团", "global"));
}