package com.tank.ognl.script;

import com.tank.common.entity.Person;
import com.tank.common.entity.Student;
import com.tank.common.entity.Teacher;
import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.val;
import ognl.MemberAccess;
import ognl.Ognl;
import ognl.OgnlContext;

import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Member;
import java.lang.reflect.Modifier;
import java.util.Map;
import java.util.Objects;

/**
 * @author tank198435163.com
 */
public class OgnlScript {

  public static OgnlScript createOgnlExpr() {
    return OGNL_SCRIPT;
  }

  @SneakyThrows
  public Object run(@NonNull final String expression) {
    val tree = Ognl.parseExpression(expression);
    return Ognl.getValue(tree, this.context, this.context.getRoot());
  }


  private OgnlScript() {
    this.context.put("person", new Person());
    this.context.put("student", new Student());
    this.context.put("teacher", new Teacher());
    this.context.setRoot(this);
  }

  private static final OgnlScript OGNL_SCRIPT = new OgnlScript();


  private final OgnlContext context = (OgnlContext) Ognl.createDefaultContext(this, new MemberAccess() {

    @Override
    public Object setup(Map context, Object target, Member member, String propertyName) {
      Object result = null;
      if (this.isAccessible(context, target, member, propertyName)) {
        final AccessibleObject accessibleObject = ((AccessibleObject) member);
        if (!accessibleObject.isAccessible()) {
          result = Boolean.TRUE;
          accessibleObject.setAccessible(true);
        }
      }
      return result;
    }

    @Override
    public void restore(Map context, Object target, Member member, String propertyName, Object state) {
      if (Objects.nonNull(state)) {
        final AccessibleObject accessibleObject = (AccessibleObject) member;
        accessibleObject.setAccessible(true);
      }
    }

    @Override
    public boolean isAccessible(Map context, Object target, Member member, String propertyName) {
      return Modifier.isPublic(member.getModifiers());
    }
  });
}
