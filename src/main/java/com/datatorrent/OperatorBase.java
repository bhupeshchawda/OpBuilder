package com.datatorrent;

import java.lang.reflect.Method;

import com.datatorrent.api.Context;
import com.datatorrent.common.util.BaseOperator;

/**
 * Created by bhupesh on 20/12/15.
 */
public class OperatorBase extends BaseOperator
{
  protected transient Method method;
  protected String methodName = "string_length";
  protected Object library = new Library();

  public OperatorBase()
  {
  }

  @Override
  public void setup(Context.OperatorContext context)
  {
    try {
      method = library.getClass().getMethod(methodName, String.class);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
