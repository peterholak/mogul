/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class cairo_region_overlap_t {
  public final static cairo_region_overlap_t CAIRO_REGION_OVERLAP_IN = new cairo_region_overlap_t("CAIRO_REGION_OVERLAP_IN");
  public final static cairo_region_overlap_t CAIRO_REGION_OVERLAP_OUT = new cairo_region_overlap_t("CAIRO_REGION_OVERLAP_OUT");
  public final static cairo_region_overlap_t CAIRO_REGION_OVERLAP_PART = new cairo_region_overlap_t("CAIRO_REGION_OVERLAP_PART");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static cairo_region_overlap_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + cairo_region_overlap_t.class + " with value " + swigValue);
  }

  private cairo_region_overlap_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private cairo_region_overlap_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private cairo_region_overlap_t(String swigName, cairo_region_overlap_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static cairo_region_overlap_t[] swigValues = { CAIRO_REGION_OVERLAP_IN, CAIRO_REGION_OVERLAP_OUT, CAIRO_REGION_OVERLAP_PART };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

