/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GLogLevelFlags {
  public final static GLogLevelFlags G_LOG_FLAG_RECURSION = new GLogLevelFlags("G_LOG_FLAG_RECURSION", pangoJNI.G_LOG_FLAG_RECURSION_get());
  public final static GLogLevelFlags G_LOG_FLAG_FATAL = new GLogLevelFlags("G_LOG_FLAG_FATAL", pangoJNI.G_LOG_FLAG_FATAL_get());
  public final static GLogLevelFlags G_LOG_LEVEL_ERROR = new GLogLevelFlags("G_LOG_LEVEL_ERROR", pangoJNI.G_LOG_LEVEL_ERROR_get());
  public final static GLogLevelFlags G_LOG_LEVEL_CRITICAL = new GLogLevelFlags("G_LOG_LEVEL_CRITICAL", pangoJNI.G_LOG_LEVEL_CRITICAL_get());
  public final static GLogLevelFlags G_LOG_LEVEL_WARNING = new GLogLevelFlags("G_LOG_LEVEL_WARNING", pangoJNI.G_LOG_LEVEL_WARNING_get());
  public final static GLogLevelFlags G_LOG_LEVEL_MESSAGE = new GLogLevelFlags("G_LOG_LEVEL_MESSAGE", pangoJNI.G_LOG_LEVEL_MESSAGE_get());
  public final static GLogLevelFlags G_LOG_LEVEL_INFO = new GLogLevelFlags("G_LOG_LEVEL_INFO", pangoJNI.G_LOG_LEVEL_INFO_get());
  public final static GLogLevelFlags G_LOG_LEVEL_DEBUG = new GLogLevelFlags("G_LOG_LEVEL_DEBUG", pangoJNI.G_LOG_LEVEL_DEBUG_get());
  public final static GLogLevelFlags G_LOG_LEVEL_MASK = new GLogLevelFlags("G_LOG_LEVEL_MASK", pangoJNI.G_LOG_LEVEL_MASK_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GLogLevelFlags swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GLogLevelFlags.class + " with value " + swigValue);
  }

  private GLogLevelFlags(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GLogLevelFlags(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GLogLevelFlags(String swigName, GLogLevelFlags swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GLogLevelFlags[] swigValues = { G_LOG_FLAG_RECURSION, G_LOG_FLAG_FATAL, G_LOG_LEVEL_ERROR, G_LOG_LEVEL_CRITICAL, G_LOG_LEVEL_WARNING, G_LOG_LEVEL_MESSAGE, G_LOG_LEVEL_INFO, G_LOG_LEVEL_DEBUG, G_LOG_LEVEL_MASK };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

