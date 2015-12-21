package com.datatorrent;

import java.lang.reflect.Method;

import com.datatorrent.api.Context;
import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.Operator;
import com.datatorrent.common.util.BaseOperator;

/**
 * Created by bhupesh on 20/12/15.
 */
public class OpBuilder extends SingleIOOperator
{
  private transient Method method;
  private transient String methodName = "string_length";
  private Library library = new Library();

  public OpBuilder()
  {
  }

  @Override
  public void setup(Context.OperatorContext context)
  {
    try {
      method = library.getClass().getMethod(methodName, String.class);
      System.out.println("Method: " + method);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public transient DefaultInputPort<Object> input = new DefaultInputPort()
  {
    @Override
    public void process(Object tuple)
    {
      processTuple(tuple);
    }
  };
  public transient DefaultOutputPort<Object> output = new DefaultOutputPort<>();

  protected void processTuple(Object tuple)
  {
    try {
      System.out.println("Input"+tuple);
      Object returnValue = method.invoke(library, tuple);
      System.out.println("Output"+returnValue);
      output.emit(returnValue);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
