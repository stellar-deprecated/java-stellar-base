// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum OperationType
//  {
//      PAYMENT = 0,
//      CREATE_OFFER = 1,
//      SET_OPTIONS = 2,
//      CHANGE_TRUST = 3,
//      ALLOW_TRUST = 4,
//      ACCOUNT_MERGE = 5,
//      INFLATION = 6
//  };

//  ===========================================================================
public enum OperationType  {
  PAYMENT(0),
  CREATE_OFFER(1),
  SET_OPTIONS(2),
  CHANGE_TRUST(3),
  ALLOW_TRUST(4),
  ACCOUNT_MERGE(5),
  INFLATION(6),
  ;
  private int mValue;

  OperationType(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static OperationType decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return PAYMENT;
      case 1: return CREATE_OFFER;
      case 2: return SET_OPTIONS;
      case 3: return CHANGE_TRUST;
      case 4: return ALLOW_TRUST;
      case 5: return ACCOUNT_MERGE;
      case 6: return INFLATION;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, OperationType value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
