package com.datatorrent;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;

/**
 * Created by bhupesh on 20/12/15.
 */
public class SIDOOpBuilder<T, V1, V2> extends OperatorBase
{
  public SIDOOpBuilder()
  {
  }

  public transient DefaultInputPort<T> input = new DefaultInputPort<T>()
  {
    @Override public void process(T tuple)
    {
      processTuple(tuple);
    }
  };
  public transient DefaultOutputPort<V1> output1 = new DefaultOutputPort<>();
  public transient DefaultOutputPort<V2> output2 = new DefaultOutputPort<>();

  protected void processTuple(T tuple)
  {
    try {
      Object returnValue = method.invoke(library, tuple);
      Object[] returnValues = (Object[])returnValue;
      output1.emit((V1)returnValues[0]);
      output2.emit((V2)returnValues[1]);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
