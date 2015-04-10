// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum SetOptionsResultCode
//  {
//      // codes considered as "success" for the operation
//      SET_OPTIONS_SUCCESS = 0,
//      // codes considered as "failure" for the operation
//      SET_OPTIONS_LOW_RESERVE = -1,      // not enough funds to add a signer
//      SET_OPTIONS_TOO_MANY_SIGNERS = -2, // max number of signers already reached
//      SET_OPTIONS_BAD_FLAGS = -3,        // invalid combination of clear/set flags
//      SET_OPTIONS_INVALID_INFLATION = -4 // inflation account does not exist
//  };

//  ===========================================================================
public enum SetOptionsResultCode  {
  SET_OPTIONS_SUCCESS(0),
  SET_OPTIONS_LOW_RESERVE(-1),
  SET_OPTIONS_TOO_MANY_SIGNERS(-2),
  SET_OPTIONS_BAD_FLAGS(-3),
  SET_OPTIONS_INVALID_INFLATION(-4),
  ;
  private int mValue;

  SetOptionsResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static SetOptionsResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return SET_OPTIONS_SUCCESS;
      case -1: return SET_OPTIONS_LOW_RESERVE;
      case -2: return SET_OPTIONS_TOO_MANY_SIGNERS;
      case -3: return SET_OPTIONS_BAD_FLAGS;
      case -4: return SET_OPTIONS_INVALID_INFLATION;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, SetOptionsResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
