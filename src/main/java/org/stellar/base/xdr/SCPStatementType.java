// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum SCPStatementType
//  {
//      PREPARING = 0,
//      PREPARED = 1,
//      COMMITTING = 2,
//      COMMITTED = 3
//  };

//  ===========================================================================
public enum SCPStatementType  {
  PREPARING(0),
  PREPARED(1),
  COMMITTING(2),
  COMMITTED(3),
  ;
  private int mValue;

  SCPStatementType(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static SCPStatementType decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return PREPARING;
      case 1: return PREPARED;
      case 2: return COMMITTING;
      case 3: return COMMITTED;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, SCPStatementType value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
