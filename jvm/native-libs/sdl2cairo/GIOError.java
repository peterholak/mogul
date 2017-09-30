/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GIOError {
  public final static GIOError G_IO_ERROR_NONE = new GIOError("G_IO_ERROR_NONE");
  public final static GIOError G_IO_ERROR_AGAIN = new GIOError("G_IO_ERROR_AGAIN");
  public final static GIOError G_IO_ERROR_INVAL = new GIOError("G_IO_ERROR_INVAL");
  public final static GIOError G_IO_ERROR_UNKNOWN = new GIOError("G_IO_ERROR_UNKNOWN");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GIOError swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GIOError.class + " with value " + swigValue);
  }

  private GIOError(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GIOError(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GIOError(String swigName, GIOError swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GIOError[] swigValues = { G_IO_ERROR_NONE, G_IO_ERROR_AGAIN, G_IO_ERROR_INVAL, G_IO_ERROR_UNKNOWN };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}
