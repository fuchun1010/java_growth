package com.tank.clazz;

import cn.hutool.core.util.StrUtil;
import com.tank.common.entity.Person;
import javassist.*;
import lombok.SneakyThrows;
import lombok.val;

import java.lang.reflect.Method;

/**
 * @author tank198435163.com
 */
public class ClazzApp {

  @SneakyThrows
  public static void main(final String[] args) {
    val classPool = ClassPool.getDefault();
    val fullName = StrUtil.format("{}.{}", Person.class.getPackage().getName(), "Gun");
    val ctClass = classPool.makeClass(fullName);
    val createdPriceField = new CtField(CtClass.intType, "price", ctClass);
    createdPriceField.setModifiers(Modifier.PUBLIC);
    ctClass.addField(createdPriceField);

    val methodName = "setPrice";
    val createdSetter = new CtMethod(CtClass.voidType, methodName, new CtClass[]{
            CtClass.intType
    }, ctClass);
    createdSetter.setModifiers(Modifier.PUBLIC);
    createdSetter.setBody("this.price = $1;");
    ctClass.addMethod(createdSetter);

    val gun = ClassPool.getDefault().get(fullName).toClass().newInstance();
    for (final Method method : gun.getClass().getDeclaredMethods()) {
      if (methodName.equalsIgnoreCase(method.getName())) {
        method.invoke(gun, 100);
        break;
      }
    }
    val field = gun.getClass().getDeclaredField("price");
    System.out.println(field.get(gun));
  }
}
