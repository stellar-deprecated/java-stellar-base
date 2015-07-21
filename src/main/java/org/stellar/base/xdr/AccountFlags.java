// Automatically generated on 2015-07-21T12:54:49-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum AccountFlags
//  { // masks for each flag
//  
//      // if set, TrustLines are created with authorized set to "false"
//      // requiring the issuer to set it for each TrustLine
//      AUTH_REQUIRED_FLAG = 0x1,
//      // if set, the authorized flag in TrustLines can be cleared
//      // otherwise, authorization cannot be revoked
//      AUTH_REVOCABLE_FLAG = 0x2
//  };

//  ===========================================================================
public enum AccountFlags  {
  AUTH_REQUIRED_FLAG(1),
  AUTH_REVOCABLE_FLAG(2),
  ;
  private int mValue;

  AccountFlags(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static AccountFlags decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 1: return AUTH_REQUIRED_FLAG;
      case 2: return AUTH_REVOCABLE_FLAG;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, AccountFlags value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
