// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum AllowTrustResultCode
//  {
//      // codes considered as "success" for the operation
//      ALLOW_TRUST_SUCCESS = 0,
//      // codes considered as "failure" for the operation
//      ALLOW_TRUST_MALFORMED = -1,         // currency is not ISO4217
//      ALLOW_TRUST_NO_TRUST_LINE = -2,     // trustor does not have a trustline
//      ALLOW_TRUST_TRUST_NOT_REQUIRED = -3 // source account does not require trust
//  };

//  ===========================================================================
public enum AllowTrustResultCode  {
  ALLOW_TRUST_SUCCESS(0),
  ALLOW_TRUST_MALFORMED(-1),
  ALLOW_TRUST_NO_TRUST_LINE(-2),
  ALLOW_TRUST_TRUST_NOT_REQUIRED(-3),
  ;
  private int mValue;

  AllowTrustResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static AllowTrustResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return ALLOW_TRUST_SUCCESS;
      case -1: return ALLOW_TRUST_MALFORMED;
      case -2: return ALLOW_TRUST_NO_TRUST_LINE;
      case -3: return ALLOW_TRUST_TRUST_NOT_REQUIRED;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, AllowTrustResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
