/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GTestSubprocessFlags {
  public final static GTestSubprocessFlags G_TEST_SUBPROCESS_INHERIT_STDIN = new GTestSubprocessFlags("G_TEST_SUBPROCESS_INHERIT_STDIN", pangoJNI.G_TEST_SUBPROCESS_INHERIT_STDIN_get());
  public final static GTestSubprocessFlags G_TEST_SUBPROCESS_INHERIT_STDOUT = new GTestSubprocessFlags("G_TEST_SUBPROCESS_INHERIT_STDOUT", pangoJNI.G_TEST_SUBPROCESS_INHERIT_STDOUT_get());
  public final static GTestSubprocessFlags G_TEST_SUBPROCESS_INHERIT_STDERR = new GTestSubprocessFlags("G_TEST_SUBPROCESS_INHERIT_STDERR", pangoJNI.G_TEST_SUBPROCESS_INHERIT_STDERR_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GTestSubprocessFlags swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GTestSubprocessFlags.class + " with value " + swigValue);
  }

  private GTestSubprocessFlags(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GTestSubprocessFlags(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GTestSubprocessFlags(String swigName, GTestSubprocessFlags swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GTestSubprocessFlags[] swigValues = { G_TEST_SUBPROCESS_INHERIT_STDIN, G_TEST_SUBPROCESS_INHERIT_STDOUT, G_TEST_SUBPROCESS_INHERIT_STDERR };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

