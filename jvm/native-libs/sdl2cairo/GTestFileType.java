/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GTestFileType {
  public final static GTestFileType G_TEST_DIST = new GTestFileType("G_TEST_DIST");
  public final static GTestFileType G_TEST_BUILT = new GTestFileType("G_TEST_BUILT");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GTestFileType swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GTestFileType.class + " with value " + swigValue);
  }

  private GTestFileType(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GTestFileType(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GTestFileType(String swigName, GTestFileType swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GTestFileType[] swigValues = { G_TEST_DIST, G_TEST_BUILT };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

