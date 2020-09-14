package com.tank.common.tree;

import com.tank.common.entity.Organization;
import lombok.val;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

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

    Node<Organization> gzpszx = new Node<>(new Organization("广州配送中心", "szpszx"));
    Node<Organization> king = new Node<>(new Organization("king片区", "king_0001"));
    Leaf<Organization> store_0002 = new Leaf<>(new Organization("广州天河店", "s0002"));

    this.root.addChild(gzpszx);
    gzpszx.addChild(king);
    king.addChild(store_0002);
  }

  @Test
  public void parentPath() {
    val target = new Organization("king片区", "king_0001");
    val store_0002 = new Organization("广州天河店", "s0002");
    List<Organization> result = this.root.parentPath(store_0002);
    System.out.println(result);
  }

  @Test
  public void testFind() {
    val target = new Organization("king片区", "king_0001");
    val node = this.root.find(target);
    if (node instanceof Node) {
      val tmpNode = ((Node<Organization>) node);
      System.out.println(tmpNode);
    }
  }

  @Test
  public void print() {
    this.root.print();
  }

  private final Node<Organization> root = new Node<>(new Organization("集团", "global"));
}