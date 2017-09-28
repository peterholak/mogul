/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GFormatSizeFlags {
  public final static GFormatSizeFlags G_FORMAT_SIZE_DEFAULT = new GFormatSizeFlags("G_FORMAT_SIZE_DEFAULT", pangoJNI.G_FORMAT_SIZE_DEFAULT_get());
  public final static GFormatSizeFlags G_FORMAT_SIZE_LONG_FORMAT = new GFormatSizeFlags("G_FORMAT_SIZE_LONG_FORMAT", pangoJNI.G_FORMAT_SIZE_LONG_FORMAT_get());
  public final static GFormatSizeFlags G_FORMAT_SIZE_IEC_UNITS = new GFormatSizeFlags("G_FORMAT_SIZE_IEC_UNITS", pangoJNI.G_FORMAT_SIZE_IEC_UNITS_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GFormatSizeFlags swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GFormatSizeFlags.class + " with value " + swigValue);
  }

  private GFormatSizeFlags(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GFormatSizeFlags(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GFormatSizeFlags(String swigName, GFormatSizeFlags swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GFormatSizeFlags[] swigValues = { G_FORMAT_SIZE_DEFAULT, G_FORMAT_SIZE_LONG_FORMAT, G_FORMAT_SIZE_IEC_UNITS };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

