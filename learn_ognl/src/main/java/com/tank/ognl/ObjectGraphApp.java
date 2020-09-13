package com.tank.ognl;

import com.tank.ognl.script.OgnlScript;
import lombok.val;

/**
 * @author tank198435163.com
 */
public class ObjectGraphApp {
  public static void main(String[] args) {
    val script = OgnlScript.createOgnlExpr();
    val result = script.run("#words=#student.say(),#person.sayHello(#words), #words.length > 0 ? #person.sayHello(#words): #person.sayHello(\"ak\")");
    val list = script.run("{1,2,3,4}");
    System.out.println(list.getClass().getName());
    System.out.println(result);
    val hello = "hello";
  }
}
