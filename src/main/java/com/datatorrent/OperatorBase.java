package com.datatorrent;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;

import com.google.common.base.Preconditions;

import com.datatorrent.api.Context;
import com.datatorrent.common.util.BaseOperator;

/**
 * Created by bhupesh on 20/12/15.
 */
public class OperatorBase extends BaseOperator
{
  protected transient Method method;
  protected String methodName;
  protected Object library;
  protected boolean isStatic;

  public OperatorBase()
  {
  }

  @Override
  public void setup(Context.OperatorContext context)
  {
    Preconditions.checkArgument(methodName != null && methodName.length() > 0);
    Preconditions.checkArgument(library != null);
    try {
      for (Method m : library.getClass().getDeclaredMethods()) {
        if (m.getName().equals(methodName)) {
          method = m;
          break;
        }
      }
      Preconditions.checkArgument(method != null);
      isStatic = Modifier.isStatic(method.getModifiers());
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
