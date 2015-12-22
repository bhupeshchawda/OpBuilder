package com.datatorrent;

import com.datatorrent.api.DefaultOutputPort;
import com.datatorrent.api.InputOperator;

/**
 * Created by bhupesh on 20/12/15.
 */
public class SOInputOpBuilder<O> extends OperatorBase implements InputOperator
{
  public SOInputOpBuilder()
  {
  }

  public SOInputOpBuilder(String methodName, Object library)
  {
    this.methodName = methodName;
    try {
      if (library instanceof Class) {
        this.library = ((Class)library).newInstance();
      } else {
        this.library = library;
      }
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

  public transient DefaultOutputPort<O> output = new DefaultOutputPort<>();

  @Override
  public void emitTuples()
  {
    emitTuple();
  }

  protected void emitTuple()
  {
    try {
      Object returnValue = null;
      if (isStatic) {
        returnValue = method.invoke(null);
      } else {
        returnValue = method.invoke(library);
      }
      output.emit((O)returnValue);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}
