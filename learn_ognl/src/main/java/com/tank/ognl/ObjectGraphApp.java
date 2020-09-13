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
    System.out.println(result);
  }
}
