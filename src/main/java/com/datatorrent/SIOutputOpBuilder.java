package com.datatorrent;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;

/**
 * Created by bhupesh on 20/12/15.
 */
public class SIOutputOpBuilder<I> extends OperatorBase
{
  public SIOutputOpBuilder()
  {
  }

  public SIOutputOpBuilder(String methodName, Object library)
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

  public transient DefaultInputPort<I> input = new DefaultInputPort<I>()
  {
    @Override
    public void process(I tuple)
    {
      processTuple(tuple);
    }
  };

  protected void processTuple(I tuple)
  {
    try {
      Object returnValue = null;
      if (isStatic) {
        returnValue = method.invoke(null, tuple);
      } else {
        returnValue = method.invoke(library, tuple);
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
