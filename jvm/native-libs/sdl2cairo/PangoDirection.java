/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class PangoDirection {
  public final static PangoDirection PANGO_DIRECTION_LTR = new PangoDirection("PANGO_DIRECTION_LTR");
  public final static PangoDirection PANGO_DIRECTION_RTL = new PangoDirection("PANGO_DIRECTION_RTL");
  public final static PangoDirection PANGO_DIRECTION_TTB_LTR = new PangoDirection("PANGO_DIRECTION_TTB_LTR");
  public final static PangoDirection PANGO_DIRECTION_TTB_RTL = new PangoDirection("PANGO_DIRECTION_TTB_RTL");
  public final static PangoDirection PANGO_DIRECTION_WEAK_LTR = new PangoDirection("PANGO_DIRECTION_WEAK_LTR");
  public final static PangoDirection PANGO_DIRECTION_WEAK_RTL = new PangoDirection("PANGO_DIRECTION_WEAK_RTL");
  public final static PangoDirection PANGO_DIRECTION_NEUTRAL = new PangoDirection("PANGO_DIRECTION_NEUTRAL");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static PangoDirection swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + PangoDirection.class + " with value " + swigValue);
  }

  private PangoDirection(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private PangoDirection(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private PangoDirection(String swigName, PangoDirection swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static PangoDirection[] swigValues = { PANGO_DIRECTION_LTR, PANGO_DIRECTION_RTL, PANGO_DIRECTION_TTB_LTR, PANGO_DIRECTION_TTB_RTL, PANGO_DIRECTION_WEAK_LTR, PANGO_DIRECTION_WEAK_RTL, PANGO_DIRECTION_NEUTRAL };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}
