package com.datatorrent;

import com.datatorrent.api.DefaultInputPort;
import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.common.util.BaseOperator;

/**
 * Created by bhupesh on 20/12/15.
 */
public class SingleIOOperator extends BaseOperator
{
  public SingleIOOperator()
  {
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
  }
}
