// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum OperationType
//  {
//      CREATE_ACCOUNT = 0,
//      PAYMENT = 1,
//      PATH_PAYMENT = 2,
//      CREATE_OFFER = 3,
//      SET_OPTIONS = 4,
//      CHANGE_TRUST = 5,
//      ALLOW_TRUST = 6,
//      ACCOUNT_MERGE = 7,
//      INFLATION = 8
//  };

//  ===========================================================================
public enum OperationType  {
  CREATE_ACCOUNT(0),
  PAYMENT(1),
  PATH_PAYMENT(2),
  CREATE_OFFER(3),
  SET_OPTIONS(4),
  CHANGE_TRUST(5),
  ALLOW_TRUST(6),
  ACCOUNT_MERGE(7),
  INFLATION(8),
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
      case 0: return CREATE_ACCOUNT;
      case 1: return PAYMENT;
      case 2: return PATH_PAYMENT;
      case 3: return CREATE_OFFER;
      case 4: return SET_OPTIONS;
      case 5: return CHANGE_TRUST;
      case 6: return ALLOW_TRUST;
      case 7: return ACCOUNT_MERGE;
      case 8: return INFLATION;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, OperationType value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
