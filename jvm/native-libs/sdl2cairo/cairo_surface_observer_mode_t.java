/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class cairo_surface_observer_mode_t {
  public final static cairo_surface_observer_mode_t CAIRO_SURFACE_OBSERVER_NORMAL = new cairo_surface_observer_mode_t("CAIRO_SURFACE_OBSERVER_NORMAL", cairoJNI.CAIRO_SURFACE_OBSERVER_NORMAL_get());
  public final static cairo_surface_observer_mode_t CAIRO_SURFACE_OBSERVER_RECORD_OPERATIONS = new cairo_surface_observer_mode_t("CAIRO_SURFACE_OBSERVER_RECORD_OPERATIONS", cairoJNI.CAIRO_SURFACE_OBSERVER_RECORD_OPERATIONS_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static cairo_surface_observer_mode_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + cairo_surface_observer_mode_t.class + " with value " + swigValue);
  }

  private cairo_surface_observer_mode_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private cairo_surface_observer_mode_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private cairo_surface_observer_mode_t(String swigName, cairo_surface_observer_mode_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static cairo_surface_observer_mode_t[] swigValues = { CAIRO_SURFACE_OBSERVER_NORMAL, CAIRO_SURFACE_OBSERVER_RECORD_OPERATIONS };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

