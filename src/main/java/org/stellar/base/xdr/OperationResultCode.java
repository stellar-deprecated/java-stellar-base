// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum OperationResultCode
//  {
//      opINNER = 0, // inner object result is valid
//  
//      opBAD_AUTH = -1,  // not enough signatures to perform operation
//      opNO_ACCOUNT = -2 // source account was not found
//  };

//  ===========================================================================
public enum OperationResultCode  {
  opINNER(0),
  opBAD_AUTH(-1),
  opNO_ACCOUNT(-2),
  ;
  private int mValue;

  OperationResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static OperationResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return opINNER;
      case -1: return opBAD_AUTH;
      case -2: return opNO_ACCOUNT;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, OperationResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}