/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 3.0.12
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package sdl2cairo;

public class _GScannerConfig {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected _GScannerConfig(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(_GScannerConfig obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pangoJNI.delete__GScannerConfig(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setCset_skip_characters(String value) {
    pangoJNI._GScannerConfig_cset_skip_characters_set(swigCPtr, this, value);
  }

  public String getCset_skip_characters() {
    return pangoJNI._GScannerConfig_cset_skip_characters_get(swigCPtr, this);
  }

  public void setCset_identifier_first(String value) {
    pangoJNI._GScannerConfig_cset_identifier_first_set(swigCPtr, this, value);
  }

  public String getCset_identifier_first() {
    return pangoJNI._GScannerConfig_cset_identifier_first_get(swigCPtr, this);
  }

  public void setCset_identifier_nth(String value) {
    pangoJNI._GScannerConfig_cset_identifier_nth_set(swigCPtr, this, value);
  }

  public String getCset_identifier_nth() {
    return pangoJNI._GScannerConfig_cset_identifier_nth_get(swigCPtr, this);
  }

  public void setCpair_comment_single(String value) {
    pangoJNI._GScannerConfig_cpair_comment_single_set(swigCPtr, this, value);
  }

  public String getCpair_comment_single() {
    return pangoJNI._GScannerConfig_cpair_comment_single_get(swigCPtr, this);
  }

  public void setCase_sensitive(long value) {
    pangoJNI._GScannerConfig_case_sensitive_set(swigCPtr, this, value);
  }

  public long getCase_sensitive() {
    return pangoJNI._GScannerConfig_case_sensitive_get(swigCPtr, this);
  }

  public void setSkip_comment_multi(long value) {
    pangoJNI._GScannerConfig_skip_comment_multi_set(swigCPtr, this, value);
  }

  public long getSkip_comment_multi() {
    return pangoJNI._GScannerConfig_skip_comment_multi_get(swigCPtr, this);
  }

  public void setSkip_comment_single(long value) {
    pangoJNI._GScannerConfig_skip_comment_single_set(swigCPtr, this, value);
  }

  public long getSkip_comment_single() {
    return pangoJNI._GScannerConfig_skip_comment_single_get(swigCPtr, this);
  }

  public void setScan_comment_multi(long value) {
    pangoJNI._GScannerConfig_scan_comment_multi_set(swigCPtr, this, value);
  }

  public long getScan_comment_multi() {
    return pangoJNI._GScannerConfig_scan_comment_multi_get(swigCPtr, this);
  }

  public void setScan_identifier(long value) {
    pangoJNI._GScannerConfig_scan_identifier_set(swigCPtr, this, value);
  }

  public long getScan_identifier() {
    return pangoJNI._GScannerConfig_scan_identifier_get(swigCPtr, this);
  }

  public void setScan_identifier_1char(long value) {
    pangoJNI._GScannerConfig_scan_identifier_1char_set(swigCPtr, this, value);
  }

  public long getScan_identifier_1char() {
    return pangoJNI._GScannerConfig_scan_identifier_1char_get(swigCPtr, this);
  }

  public void setScan_identifier_NULL(long value) {
    pangoJNI._GScannerConfig_scan_identifier_NULL_set(swigCPtr, this, value);
  }

  public long getScan_identifier_NULL() {
    return pangoJNI._GScannerConfig_scan_identifier_NULL_get(swigCPtr, this);
  }

  public void setScan_symbols(long value) {
    pangoJNI._GScannerConfig_scan_symbols_set(swigCPtr, this, value);
  }

  public long getScan_symbols() {
    return pangoJNI._GScannerConfig_scan_symbols_get(swigCPtr, this);
  }

  public void setScan_binary(long value) {
    pangoJNI._GScannerConfig_scan_binary_set(swigCPtr, this, value);
  }

  public long getScan_binary() {
    return pangoJNI._GScannerConfig_scan_binary_get(swigCPtr, this);
  }

  public void setScan_octal(long value) {
    pangoJNI._GScannerConfig_scan_octal_set(swigCPtr, this, value);
  }

  public long getScan_octal() {
    return pangoJNI._GScannerConfig_scan_octal_get(swigCPtr, this);
  }

  public void setScan_float(long value) {
    pangoJNI._GScannerConfig_scan_float_set(swigCPtr, this, value);
  }

  public long getScan_float() {
    return pangoJNI._GScannerConfig_scan_float_get(swigCPtr, this);
  }

  public void setScan_hex(long value) {
    pangoJNI._GScannerConfig_scan_hex_set(swigCPtr, this, value);
  }

  public long getScan_hex() {
    return pangoJNI._GScannerConfig_scan_hex_get(swigCPtr, this);
  }

  public void setScan_hex_dollar(long value) {
    pangoJNI._GScannerConfig_scan_hex_dollar_set(swigCPtr, this, value);
  }

  public long getScan_hex_dollar() {
    return pangoJNI._GScannerConfig_scan_hex_dollar_get(swigCPtr, this);
  }

  public void setScan_string_sq(long value) {
    pangoJNI._GScannerConfig_scan_string_sq_set(swigCPtr, this, value);
  }

  public long getScan_string_sq() {
    return pangoJNI._GScannerConfig_scan_string_sq_get(swigCPtr, this);
  }

  public void setScan_string_dq(long value) {
    pangoJNI._GScannerConfig_scan_string_dq_set(swigCPtr, this, value);
  }

  public long getScan_string_dq() {
    return pangoJNI._GScannerConfig_scan_string_dq_get(swigCPtr, this);
  }

  public void setNumbers_2_int(long value) {
    pangoJNI._GScannerConfig_numbers_2_int_set(swigCPtr, this, value);
  }

  public long getNumbers_2_int() {
    return pangoJNI._GScannerConfig_numbers_2_int_get(swigCPtr, this);
  }

  public void setInt_2_float(long value) {
    pangoJNI._GScannerConfig_int_2_float_set(swigCPtr, this, value);
  }

  public long getInt_2_float() {
    return pangoJNI._GScannerConfig_int_2_float_get(swigCPtr, this);
  }

  public void setIdentifier_2_string(long value) {
    pangoJNI._GScannerConfig_identifier_2_string_set(swigCPtr, this, value);
  }

  public long getIdentifier_2_string() {
    return pangoJNI._GScannerConfig_identifier_2_string_get(swigCPtr, this);
  }

  public void setChar_2_token(long value) {
    pangoJNI._GScannerConfig_char_2_token_set(swigCPtr, this, value);
  }

  public long getChar_2_token() {
    return pangoJNI._GScannerConfig_char_2_token_get(swigCPtr, this);
  }

  public void setSymbol_2_token(long value) {
    pangoJNI._GScannerConfig_symbol_2_token_set(swigCPtr, this, value);
  }

  public long getSymbol_2_token() {
    return pangoJNI._GScannerConfig_symbol_2_token_get(swigCPtr, this);
  }

  public void setScope_0_fallback(long value) {
    pangoJNI._GScannerConfig_scope_0_fallback_set(swigCPtr, this, value);
  }

  public long getScope_0_fallback() {
    return pangoJNI._GScannerConfig_scope_0_fallback_get(swigCPtr, this);
  }

  public void setStore_int64(long value) {
    pangoJNI._GScannerConfig_store_int64_set(swigCPtr, this, value);
  }

  public long getStore_int64() {
    return pangoJNI._GScannerConfig_store_int64_get(swigCPtr, this);
  }

  public void setPadding_dummy(long value) {
    pangoJNI._GScannerConfig_padding_dummy_set(swigCPtr, this, value);
  }

  public long getPadding_dummy() {
    return pangoJNI._GScannerConfig_padding_dummy_get(swigCPtr, this);
  }

  public _GScannerConfig() {
    this(pangoJNI.new__GScannerConfig(), true);
  }

}