package com.tank.pattern;

import com.tank.pattern.jdkproxy.InterfaceInvokedHandler;
import com.tank.pattern.jdkproxy.service.DefaultHello;
import com.tank.pattern.jdkproxy.service.Hello;

import java.lang.reflect.Proxy;


/**
 * @author tank198435163.com
 */
public class App {
  public static void main(String[] args) {
    final Hello hello = new DefaultHello();
    final Hello result = ((Hello) Proxy.newProxyInstance(
            hello.getClass().getClassLoader(),
            hello.getClass().getInterfaces(),
            new InterfaceInvokedHandler(hello)));
    System.out.println("args = " + result.hello());
  }
}
