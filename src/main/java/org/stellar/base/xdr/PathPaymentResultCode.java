// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum PathPaymentResultCode
//  {
//      // codes considered as "success" for the operation
//      PATH_PAYMENT_SUCCESS = 0, // success
//  
//      // codes considered as "failure" for the operation
//      PATH_PAYMENT_MALFORMED = -1,      // bad input
//      PATH_PAYMENT_UNDERFUNDED = -2,    // not enough funds in source account
//      PATH_PAYMENT_NO_DESTINATION = -3, // destination account does not exist
//      PATH_PAYMENT_NO_TRUST = -4, // destination missing a trust line for currency
//      PATH_PAYMENT_NOT_AUTHORIZED =
//          -5,                      // destination not authorized to hold currency
//      PATH_PAYMENT_LINE_FULL = -6, // destination would go above their limit
//      PATH_PAYMENT_TOO_FEW_OFFERS = -7, // not enough offers to satisfy path
//      PATH_PAYMENT_OVER_SENDMAX = -8    // could not satisfy sendmax
//  };

//  ===========================================================================
public enum PathPaymentResultCode  {
  PATH_PAYMENT_SUCCESS(0),
  PATH_PAYMENT_MALFORMED(-1),
  PATH_PAYMENT_UNDERFUNDED(-2),
  PATH_PAYMENT_NO_DESTINATION(-3),
  PATH_PAYMENT_NO_TRUST(-4),
  PATH_PAYMENT_NOT_AUTHORIZED(-5),
  PATH_PAYMENT_LINE_FULL(-6),
  PATH_PAYMENT_TOO_FEW_OFFERS(-7),
  PATH_PAYMENT_OVER_SENDMAX(-8),
  ;
  private int mValue;

  PathPaymentResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static PathPaymentResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return PATH_PAYMENT_SUCCESS;
      case -1: return PATH_PAYMENT_MALFORMED;
      case -2: return PATH_PAYMENT_UNDERFUNDED;
      case -3: return PATH_PAYMENT_NO_DESTINATION;
      case -4: return PATH_PAYMENT_NO_TRUST;
      case -5: return PATH_PAYMENT_NOT_AUTHORIZED;
      case -6: return PATH_PAYMENT_LINE_FULL;
      case -7: return PATH_PAYMENT_TOO_FEW_OFFERS;
      case -8: return PATH_PAYMENT_OVER_SENDMAX;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, PathPaymentResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
