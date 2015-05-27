// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum CurrencyType
//  {
//      CURRENCY_TYPE_NATIVE = 0,
//      CURRENCY_TYPE_ALPHANUM = 1
//  };

//  ===========================================================================
public enum CurrencyType  {
  CURRENCY_TYPE_NATIVE(0),
  CURRENCY_TYPE_ALPHANUM(1),
  ;
  private int mValue;

  CurrencyType(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static CurrencyType decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return CURRENCY_TYPE_NATIVE;
      case 1: return CURRENCY_TYPE_ALPHANUM;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, CurrencyType value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
