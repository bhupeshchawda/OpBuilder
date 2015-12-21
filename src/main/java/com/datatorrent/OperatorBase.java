package com.datatorrent;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;

import java.lang.reflect.Method;

/**
 * Created by bhupesh on 20/12/15.
 */
public class OperatorBase extends BaseOperator
{
  protected transient Method method;
  protected transient String methodName = "string_length";
  protected Library library = new Library();

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
