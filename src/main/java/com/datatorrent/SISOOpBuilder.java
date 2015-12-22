package com.datatorrent;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;

/**
 * Created by bhupesh on 20/12/15.
 */
public class SISOOpBuilder<T, V> extends OperatorBase
{
  public SISOOpBuilder()
  {
  }

  public SISOOpBuilder(String methodName, Object library)
  {
    this.methodName = methodName;
    try {
      if (library instanceof Class) {
        this.library = ((Class)library).newInstance();
      } else {
        this.library = library;
      }
    } catch(Exception e) {
      throw new RuntimeException(e);
    }
  }

  public transient DefaultInputPort<T> input = new DefaultInputPort<T>()
  {
    @Override
    public void process(T tuple)
    {
      processTuple(tuple);
    }
  };
  public transient DefaultOutputPort<V> output = new DefaultOutputPort<>();

  protected void processTuple(T tuple)
  {
    try {
      Object returnValue = null;
      if (isStatic) {
        returnValue = method.invoke(null, tuple);
      } else {
        returnValue = method.invoke(library, tuple);
      }
      output.emit((V)returnValue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
