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
public class OpBuilder<T, V> extends OperatorBase
{
  public OpBuilder()
  {
  }

  public transient DefaultInputPort<T> input = new DefaultInputPort<T>()
  {
    @Override public void process(T tuple)
    {
      processTuple(tuple);
    }
  };
  public transient DefaultOutputPort<V> output = new DefaultOutputPort<>();

  protected void processTuple(T tuple)
  {
    try {
      Object returnValue = method.invoke(library, tuple);
      output.emit((V)returnValue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
