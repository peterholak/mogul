/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GRegexCompileFlags {
  public final static GRegexCompileFlags G_REGEX_CASELESS = new GRegexCompileFlags("G_REGEX_CASELESS", pangoJNI.G_REGEX_CASELESS_get());
  public final static GRegexCompileFlags G_REGEX_MULTILINE = new GRegexCompileFlags("G_REGEX_MULTILINE", pangoJNI.G_REGEX_MULTILINE_get());
  public final static GRegexCompileFlags G_REGEX_DOTALL = new GRegexCompileFlags("G_REGEX_DOTALL", pangoJNI.G_REGEX_DOTALL_get());
  public final static GRegexCompileFlags G_REGEX_EXTENDED = new GRegexCompileFlags("G_REGEX_EXTENDED", pangoJNI.G_REGEX_EXTENDED_get());
  public final static GRegexCompileFlags G_REGEX_ANCHORED = new GRegexCompileFlags("G_REGEX_ANCHORED", pangoJNI.G_REGEX_ANCHORED_get());
  public final static GRegexCompileFlags G_REGEX_DOLLAR_ENDONLY = new GRegexCompileFlags("G_REGEX_DOLLAR_ENDONLY", pangoJNI.G_REGEX_DOLLAR_ENDONLY_get());
  public final static GRegexCompileFlags G_REGEX_UNGREEDY = new GRegexCompileFlags("G_REGEX_UNGREEDY", pangoJNI.G_REGEX_UNGREEDY_get());
  public final static GRegexCompileFlags G_REGEX_RAW = new GRegexCompileFlags("G_REGEX_RAW", pangoJNI.G_REGEX_RAW_get());
  public final static GRegexCompileFlags G_REGEX_NO_AUTO_CAPTURE = new GRegexCompileFlags("G_REGEX_NO_AUTO_CAPTURE", pangoJNI.G_REGEX_NO_AUTO_CAPTURE_get());
  public final static GRegexCompileFlags G_REGEX_OPTIMIZE = new GRegexCompileFlags("G_REGEX_OPTIMIZE", pangoJNI.G_REGEX_OPTIMIZE_get());
  public final static GRegexCompileFlags G_REGEX_FIRSTLINE = new GRegexCompileFlags("G_REGEX_FIRSTLINE", pangoJNI.G_REGEX_FIRSTLINE_get());
  public final static GRegexCompileFlags G_REGEX_DUPNAMES = new GRegexCompileFlags("G_REGEX_DUPNAMES", pangoJNI.G_REGEX_DUPNAMES_get());
  public final static GRegexCompileFlags G_REGEX_NEWLINE_CR = new GRegexCompileFlags("G_REGEX_NEWLINE_CR", pangoJNI.G_REGEX_NEWLINE_CR_get());
  public final static GRegexCompileFlags G_REGEX_NEWLINE_LF = new GRegexCompileFlags("G_REGEX_NEWLINE_LF", pangoJNI.G_REGEX_NEWLINE_LF_get());
  public final static GRegexCompileFlags G_REGEX_NEWLINE_CRLF = new GRegexCompileFlags("G_REGEX_NEWLINE_CRLF", pangoJNI.G_REGEX_NEWLINE_CRLF_get());
  public final static GRegexCompileFlags G_REGEX_NEWLINE_ANYCRLF = new GRegexCompileFlags("G_REGEX_NEWLINE_ANYCRLF", pangoJNI.G_REGEX_NEWLINE_ANYCRLF_get());
  public final static GRegexCompileFlags G_REGEX_BSR_ANYCRLF = new GRegexCompileFlags("G_REGEX_BSR_ANYCRLF", pangoJNI.G_REGEX_BSR_ANYCRLF_get());
  public final static GRegexCompileFlags G_REGEX_JAVASCRIPT_COMPAT = new GRegexCompileFlags("G_REGEX_JAVASCRIPT_COMPAT", pangoJNI.G_REGEX_JAVASCRIPT_COMPAT_get());

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GRegexCompileFlags swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GRegexCompileFlags.class + " with value " + swigValue);
  }

  private GRegexCompileFlags(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GRegexCompileFlags(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GRegexCompileFlags(String swigName, GRegexCompileFlags swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GRegexCompileFlags[] swigValues = { G_REGEX_CASELESS, G_REGEX_MULTILINE, G_REGEX_DOTALL, G_REGEX_EXTENDED, G_REGEX_ANCHORED, G_REGEX_DOLLAR_ENDONLY, G_REGEX_UNGREEDY, G_REGEX_RAW, G_REGEX_NO_AUTO_CAPTURE, G_REGEX_OPTIMIZE, G_REGEX_FIRSTLINE, G_REGEX_DUPNAMES, G_REGEX_NEWLINE_CR, G_REGEX_NEWLINE_LF, G_REGEX_NEWLINE_CRLF, G_REGEX_NEWLINE_ANYCRLF, G_REGEX_BSR_ANYCRLF, G_REGEX_JAVASCRIPT_COMPAT };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}

