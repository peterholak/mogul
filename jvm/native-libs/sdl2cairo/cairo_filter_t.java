/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class cairo_filter_t {
  public final static cairo_filter_t CAIRO_FILTER_FAST = new cairo_filter_t("CAIRO_FILTER_FAST");
  public final static cairo_filter_t CAIRO_FILTER_GOOD = new cairo_filter_t("CAIRO_FILTER_GOOD");
  public final static cairo_filter_t CAIRO_FILTER_BEST = new cairo_filter_t("CAIRO_FILTER_BEST");
  public final static cairo_filter_t CAIRO_FILTER_NEAREST = new cairo_filter_t("CAIRO_FILTER_NEAREST");
  public final static cairo_filter_t CAIRO_FILTER_BILINEAR = new cairo_filter_t("CAIRO_FILTER_BILINEAR");
  public final static cairo_filter_t CAIRO_FILTER_GAUSSIAN = new cairo_filter_t("CAIRO_FILTER_GAUSSIAN");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static cairo_filter_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + cairo_filter_t.class + " with value " + swigValue);
  }

  private cairo_filter_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private cairo_filter_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private cairo_filter_t(String swigName, cairo_filter_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static cairo_filter_t[] swigValues = { CAIRO_FILTER_FAST, CAIRO_FILTER_GOOD, CAIRO_FILTER_BEST, CAIRO_FILTER_NEAREST, CAIRO_FILTER_BILINEAR, CAIRO_FILTER_GAUSSIAN };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

