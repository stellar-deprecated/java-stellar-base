// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum CurrencyType
//  {
//      NATIVE = 0,
//      ISO4217 = 1
//  };

//  ===========================================================================
public enum CurrencyType  {
  NATIVE(0),
  ISO4217(1),
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
      case 0: return NATIVE;
      case 1: return ISO4217;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, CurrencyType value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
