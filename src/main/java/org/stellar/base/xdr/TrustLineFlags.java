// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum TrustLineFlags
//  {
//      // issuer has authorized account to perform transactions with its credit
//      AUTHORIZED_FLAG = 1
//  };

//  ===========================================================================
public enum TrustLineFlags  {
  AUTHORIZED_FLAG(1),
  ;
  private int mValue;

  TrustLineFlags(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static TrustLineFlags decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 1: return AUTHORIZED_FLAG;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, TrustLineFlags value) throws IOException {
    stream.writeInt(value.getValue());
  }
}