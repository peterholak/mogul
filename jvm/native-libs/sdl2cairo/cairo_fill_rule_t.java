/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class cairo_fill_rule_t {
  public final static cairo_fill_rule_t CAIRO_FILL_RULE_WINDING = new cairo_fill_rule_t("CAIRO_FILL_RULE_WINDING");
  public final static cairo_fill_rule_t CAIRO_FILL_RULE_EVEN_ODD = new cairo_fill_rule_t("CAIRO_FILL_RULE_EVEN_ODD");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static cairo_fill_rule_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + cairo_fill_rule_t.class + " with value " + swigValue);
  }

  private cairo_fill_rule_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private cairo_fill_rule_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private cairo_fill_rule_t(String swigName, cairo_fill_rule_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static cairo_fill_rule_t[] swigValues = { CAIRO_FILL_RULE_WINDING, CAIRO_FILL_RULE_EVEN_ODD };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

