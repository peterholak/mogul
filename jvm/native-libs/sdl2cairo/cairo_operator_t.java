/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.10
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class cairo_operator_t {
  public final static cairo_operator_t CAIRO_OPERATOR_CLEAR = new cairo_operator_t("CAIRO_OPERATOR_CLEAR");
  public final static cairo_operator_t CAIRO_OPERATOR_SOURCE = new cairo_operator_t("CAIRO_OPERATOR_SOURCE");
  public final static cairo_operator_t CAIRO_OPERATOR_OVER = new cairo_operator_t("CAIRO_OPERATOR_OVER");
  public final static cairo_operator_t CAIRO_OPERATOR_IN = new cairo_operator_t("CAIRO_OPERATOR_IN");
  public final static cairo_operator_t CAIRO_OPERATOR_OUT = new cairo_operator_t("CAIRO_OPERATOR_OUT");
  public final static cairo_operator_t CAIRO_OPERATOR_ATOP = new cairo_operator_t("CAIRO_OPERATOR_ATOP");
  public final static cairo_operator_t CAIRO_OPERATOR_DEST = new cairo_operator_t("CAIRO_OPERATOR_DEST");
  public final static cairo_operator_t CAIRO_OPERATOR_DEST_OVER = new cairo_operator_t("CAIRO_OPERATOR_DEST_OVER");
  public final static cairo_operator_t CAIRO_OPERATOR_DEST_IN = new cairo_operator_t("CAIRO_OPERATOR_DEST_IN");
  public final static cairo_operator_t CAIRO_OPERATOR_DEST_OUT = new cairo_operator_t("CAIRO_OPERATOR_DEST_OUT");
  public final static cairo_operator_t CAIRO_OPERATOR_DEST_ATOP = new cairo_operator_t("CAIRO_OPERATOR_DEST_ATOP");
  public final static cairo_operator_t CAIRO_OPERATOR_XOR = new cairo_operator_t("CAIRO_OPERATOR_XOR");
  public final static cairo_operator_t CAIRO_OPERATOR_ADD = new cairo_operator_t("CAIRO_OPERATOR_ADD");
  public final static cairo_operator_t CAIRO_OPERATOR_SATURATE = new cairo_operator_t("CAIRO_OPERATOR_SATURATE");
  public final static cairo_operator_t CAIRO_OPERATOR_MULTIPLY = new cairo_operator_t("CAIRO_OPERATOR_MULTIPLY");
  public final static cairo_operator_t CAIRO_OPERATOR_SCREEN = new cairo_operator_t("CAIRO_OPERATOR_SCREEN");
  public final static cairo_operator_t CAIRO_OPERATOR_OVERLAY = new cairo_operator_t("CAIRO_OPERATOR_OVERLAY");
  public final static cairo_operator_t CAIRO_OPERATOR_DARKEN = new cairo_operator_t("CAIRO_OPERATOR_DARKEN");
  public final static cairo_operator_t CAIRO_OPERATOR_LIGHTEN = new cairo_operator_t("CAIRO_OPERATOR_LIGHTEN");
  public final static cairo_operator_t CAIRO_OPERATOR_COLOR_DODGE = new cairo_operator_t("CAIRO_OPERATOR_COLOR_DODGE");
  public final static cairo_operator_t CAIRO_OPERATOR_COLOR_BURN = new cairo_operator_t("CAIRO_OPERATOR_COLOR_BURN");
  public final static cairo_operator_t CAIRO_OPERATOR_HARD_LIGHT = new cairo_operator_t("CAIRO_OPERATOR_HARD_LIGHT");
  public final static cairo_operator_t CAIRO_OPERATOR_SOFT_LIGHT = new cairo_operator_t("CAIRO_OPERATOR_SOFT_LIGHT");
  public final static cairo_operator_t CAIRO_OPERATOR_DIFFERENCE = new cairo_operator_t("CAIRO_OPERATOR_DIFFERENCE");
  public final static cairo_operator_t CAIRO_OPERATOR_EXCLUSION = new cairo_operator_t("CAIRO_OPERATOR_EXCLUSION");
  public final static cairo_operator_t CAIRO_OPERATOR_HSL_HUE = new cairo_operator_t("CAIRO_OPERATOR_HSL_HUE");
  public final static cairo_operator_t CAIRO_OPERATOR_HSL_SATURATION = new cairo_operator_t("CAIRO_OPERATOR_HSL_SATURATION");
  public final static cairo_operator_t CAIRO_OPERATOR_HSL_COLOR = new cairo_operator_t("CAIRO_OPERATOR_HSL_COLOR");
  public final static cairo_operator_t CAIRO_OPERATOR_HSL_LUMINOSITY = new cairo_operator_t("CAIRO_OPERATOR_HSL_LUMINOSITY");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static cairo_operator_t swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + cairo_operator_t.class + " with value " + swigValue);
  }

  private cairo_operator_t(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private cairo_operator_t(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private cairo_operator_t(String swigName, cairo_operator_t swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static cairo_operator_t[] swigValues = { CAIRO_OPERATOR_CLEAR, CAIRO_OPERATOR_SOURCE, CAIRO_OPERATOR_OVER, CAIRO_OPERATOR_IN, CAIRO_OPERATOR_OUT, CAIRO_OPERATOR_ATOP, CAIRO_OPERATOR_DEST, CAIRO_OPERATOR_DEST_OVER, CAIRO_OPERATOR_DEST_IN, CAIRO_OPERATOR_DEST_OUT, CAIRO_OPERATOR_DEST_ATOP, CAIRO_OPERATOR_XOR, CAIRO_OPERATOR_ADD, CAIRO_OPERATOR_SATURATE, CAIRO_OPERATOR_MULTIPLY, CAIRO_OPERATOR_SCREEN, CAIRO_OPERATOR_OVERLAY, CAIRO_OPERATOR_DARKEN, CAIRO_OPERATOR_LIGHTEN, CAIRO_OPERATOR_COLOR_DODGE, CAIRO_OPERATOR_COLOR_BURN, CAIRO_OPERATOR_HARD_LIGHT, CAIRO_OPERATOR_SOFT_LIGHT, CAIRO_OPERATOR_DIFFERENCE, CAIRO_OPERATOR_EXCLUSION, CAIRO_OPERATOR_HSL_HUE, CAIRO_OPERATOR_HSL_SATURATION, CAIRO_OPERATOR_HSL_COLOR, CAIRO_OPERATOR_HSL_LUMINOSITY };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

