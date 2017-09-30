/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public final class GUnicodeType {
  public final static GUnicodeType G_UNICODE_CONTROL = new GUnicodeType("G_UNICODE_CONTROL");
  public final static GUnicodeType G_UNICODE_FORMAT = new GUnicodeType("G_UNICODE_FORMAT");
  public final static GUnicodeType G_UNICODE_UNASSIGNED = new GUnicodeType("G_UNICODE_UNASSIGNED");
  public final static GUnicodeType G_UNICODE_PRIVATE_USE = new GUnicodeType("G_UNICODE_PRIVATE_USE");
  public final static GUnicodeType G_UNICODE_SURROGATE = new GUnicodeType("G_UNICODE_SURROGATE");
  public final static GUnicodeType G_UNICODE_LOWERCASE_LETTER = new GUnicodeType("G_UNICODE_LOWERCASE_LETTER");
  public final static GUnicodeType G_UNICODE_MODIFIER_LETTER = new GUnicodeType("G_UNICODE_MODIFIER_LETTER");
  public final static GUnicodeType G_UNICODE_OTHER_LETTER = new GUnicodeType("G_UNICODE_OTHER_LETTER");
  public final static GUnicodeType G_UNICODE_TITLECASE_LETTER = new GUnicodeType("G_UNICODE_TITLECASE_LETTER");
  public final static GUnicodeType G_UNICODE_UPPERCASE_LETTER = new GUnicodeType("G_UNICODE_UPPERCASE_LETTER");
  public final static GUnicodeType G_UNICODE_SPACING_MARK = new GUnicodeType("G_UNICODE_SPACING_MARK");
  public final static GUnicodeType G_UNICODE_ENCLOSING_MARK = new GUnicodeType("G_UNICODE_ENCLOSING_MARK");
  public final static GUnicodeType G_UNICODE_NON_SPACING_MARK = new GUnicodeType("G_UNICODE_NON_SPACING_MARK");
  public final static GUnicodeType G_UNICODE_DECIMAL_NUMBER = new GUnicodeType("G_UNICODE_DECIMAL_NUMBER");
  public final static GUnicodeType G_UNICODE_LETTER_NUMBER = new GUnicodeType("G_UNICODE_LETTER_NUMBER");
  public final static GUnicodeType G_UNICODE_OTHER_NUMBER = new GUnicodeType("G_UNICODE_OTHER_NUMBER");
  public final static GUnicodeType G_UNICODE_CONNECT_PUNCTUATION = new GUnicodeType("G_UNICODE_CONNECT_PUNCTUATION");
  public final static GUnicodeType G_UNICODE_DASH_PUNCTUATION = new GUnicodeType("G_UNICODE_DASH_PUNCTUATION");
  public final static GUnicodeType G_UNICODE_CLOSE_PUNCTUATION = new GUnicodeType("G_UNICODE_CLOSE_PUNCTUATION");
  public final static GUnicodeType G_UNICODE_FINAL_PUNCTUATION = new GUnicodeType("G_UNICODE_FINAL_PUNCTUATION");
  public final static GUnicodeType G_UNICODE_INITIAL_PUNCTUATION = new GUnicodeType("G_UNICODE_INITIAL_PUNCTUATION");
  public final static GUnicodeType G_UNICODE_OTHER_PUNCTUATION = new GUnicodeType("G_UNICODE_OTHER_PUNCTUATION");
  public final static GUnicodeType G_UNICODE_OPEN_PUNCTUATION = new GUnicodeType("G_UNICODE_OPEN_PUNCTUATION");
  public final static GUnicodeType G_UNICODE_CURRENCY_SYMBOL = new GUnicodeType("G_UNICODE_CURRENCY_SYMBOL");
  public final static GUnicodeType G_UNICODE_MODIFIER_SYMBOL = new GUnicodeType("G_UNICODE_MODIFIER_SYMBOL");
  public final static GUnicodeType G_UNICODE_MATH_SYMBOL = new GUnicodeType("G_UNICODE_MATH_SYMBOL");
  public final static GUnicodeType G_UNICODE_OTHER_SYMBOL = new GUnicodeType("G_UNICODE_OTHER_SYMBOL");
  public final static GUnicodeType G_UNICODE_LINE_SEPARATOR = new GUnicodeType("G_UNICODE_LINE_SEPARATOR");
  public final static GUnicodeType G_UNICODE_PARAGRAPH_SEPARATOR = new GUnicodeType("G_UNICODE_PARAGRAPH_SEPARATOR");
  public final static GUnicodeType G_UNICODE_SPACE_SEPARATOR = new GUnicodeType("G_UNICODE_SPACE_SEPARATOR");

  public final int swigValue() {
    return swigValue;
  }

  public String toString() {
    return swigName;
  }

  public static GUnicodeType swigToEnum(int swigValue) {
    if (swigValue < swigValues.length && swigValue >= 0 && swigValues[swigValue].swigValue == swigValue)
      return swigValues[swigValue];
    for (int i = 0; i < swigValues.length; i++)
      if (swigValues[i].swigValue == swigValue)
        return swigValues[i];
    throw new IllegalArgumentException("No enum " + GUnicodeType.class + " with value " + swigValue);
  }

  private GUnicodeType(String swigName) {
    this.swigName = swigName;
    this.swigValue = swigNext++;
  }

  private GUnicodeType(String swigName, int swigValue) {
    this.swigName = swigName;
    this.swigValue = swigValue;
    swigNext = swigValue+1;
  }

  private GUnicodeType(String swigName, GUnicodeType swigEnum) {
    this.swigName = swigName;
    this.swigValue = swigEnum.swigValue;
    swigNext = this.swigValue+1;
  }

  private static GUnicodeType[] swigValues = { G_UNICODE_CONTROL, G_UNICODE_FORMAT, G_UNICODE_UNASSIGNED, G_UNICODE_PRIVATE_USE, G_UNICODE_SURROGATE, G_UNICODE_LOWERCASE_LETTER, G_UNICODE_MODIFIER_LETTER, G_UNICODE_OTHER_LETTER, G_UNICODE_TITLECASE_LETTER, G_UNICODE_UPPERCASE_LETTER, G_UNICODE_SPACING_MARK, G_UNICODE_ENCLOSING_MARK, G_UNICODE_NON_SPACING_MARK, G_UNICODE_DECIMAL_NUMBER, G_UNICODE_LETTER_NUMBER, G_UNICODE_OTHER_NUMBER, G_UNICODE_CONNECT_PUNCTUATION, G_UNICODE_DASH_PUNCTUATION, G_UNICODE_CLOSE_PUNCTUATION, G_UNICODE_FINAL_PUNCTUATION, G_UNICODE_INITIAL_PUNCTUATION, G_UNICODE_OTHER_PUNCTUATION, G_UNICODE_OPEN_PUNCTUATION, G_UNICODE_CURRENCY_SYMBOL, G_UNICODE_MODIFIER_SYMBOL, G_UNICODE_MATH_SYMBOL, G_UNICODE_OTHER_SYMBOL, G_UNICODE_LINE_SEPARATOR, G_UNICODE_PARAGRAPH_SEPARATOR, G_UNICODE_SPACE_SEPARATOR };
  private static int swigNext = 0;
  private final int swigValue;
  private final String swigName;
}
