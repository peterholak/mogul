/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GHookFlagMask {
  public final static GHookFlagMask G_HOOK_FLAG_ACTIVE = new GHookFlagMask("G_HOOK_FLAG_ACTIVE", pangoJNI.G_HOOK_FLAG_ACTIVE_get());
  public final static GHookFlagMask G_HOOK_FLAG_IN_CALL = new GHookFlagMask("G_HOOK_FLAG_IN_CALL", pangoJNI.G_HOOK_FLAG_IN_CALL_get());
  public final static GHookFlagMask G_HOOK_FLAG_MASK = new GHookFlagMask("G_HOOK_FLAG_MASK", pangoJNI.G_HOOK_FLAG_MASK_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GHookFlagMask swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GHookFlagMask.class + " with value " + swigValue);
  }

  private GHookFlagMask(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GHookFlagMask(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GHookFlagMask(String swigName, GHookFlagMask swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GHookFlagMask[] swigValues = { G_HOOK_FLAG_ACTIVE, G_HOOK_FLAG_IN_CALL, G_HOOK_FLAG_MASK };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

