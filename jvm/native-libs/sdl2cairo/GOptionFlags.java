/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GOptionFlags {
  public final static GOptionFlags G_OPTION_FLAG_NONE = new GOptionFlags("G_OPTION_FLAG_NONE", pangoJNI.G_OPTION_FLAG_NONE_get());
  public final static GOptionFlags G_OPTION_FLAG_HIDDEN = new GOptionFlags("G_OPTION_FLAG_HIDDEN", pangoJNI.G_OPTION_FLAG_HIDDEN_get());
  public final static GOptionFlags G_OPTION_FLAG_IN_MAIN = new GOptionFlags("G_OPTION_FLAG_IN_MAIN", pangoJNI.G_OPTION_FLAG_IN_MAIN_get());
  public final static GOptionFlags G_OPTION_FLAG_REVERSE = new GOptionFlags("G_OPTION_FLAG_REVERSE", pangoJNI.G_OPTION_FLAG_REVERSE_get());
  public final static GOptionFlags G_OPTION_FLAG_NO_ARG = new GOptionFlags("G_OPTION_FLAG_NO_ARG", pangoJNI.G_OPTION_FLAG_NO_ARG_get());
  public final static GOptionFlags G_OPTION_FLAG_FILENAME = new GOptionFlags("G_OPTION_FLAG_FILENAME", pangoJNI.G_OPTION_FLAG_FILENAME_get());
  public final static GOptionFlags G_OPTION_FLAG_OPTIONAL_ARG = new GOptionFlags("G_OPTION_FLAG_OPTIONAL_ARG", pangoJNI.G_OPTION_FLAG_OPTIONAL_ARG_get());
  public final static GOptionFlags G_OPTION_FLAG_NOALIAS = new GOptionFlags("G_OPTION_FLAG_NOALIAS", pangoJNI.G_OPTION_FLAG_NOALIAS_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GOptionFlags swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GOptionFlags.class + " with value " + swigValue);
  }

  private GOptionFlags(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GOptionFlags(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GOptionFlags(String swigName, GOptionFlags swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GOptionFlags[] swigValues = { G_OPTION_FLAG_NONE, G_OPTION_FLAG_HIDDEN, G_OPTION_FLAG_IN_MAIN, G_OPTION_FLAG_REVERSE, G_OPTION_FLAG_NO_ARG, G_OPTION_FLAG_FILENAME, G_OPTION_FLAG_OPTIONAL_ARG, G_OPTION_FLAG_NOALIAS };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

