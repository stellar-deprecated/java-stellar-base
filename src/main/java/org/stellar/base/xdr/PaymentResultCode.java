// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum PaymentResultCode
//  {
//      // codes considered as "success" for the operation
//      PAYMENT_SUCCESS = 0,       // simple payment success
//      PAYMENT_SUCCESS_MULTI = 1, // multi-path payment success
//  
//      // codes considered as "failure" for the operation
//      PAYMENT_MALFORMED = -1,      // bad input
//      PAYMENT_UNDERFUNDED = -2,    // not enough funds in source account
//      PAYMENT_NO_DESTINATION = -3, // destination account does not exist
//      PAYMENT_NO_TRUST = -4, // destination missing a trust line for currency
//      PAYMENT_NOT_AUTHORIZED = -5, // destination not authorized to hold currency
//      PAYMENT_LINE_FULL = -6,      // destination would go above their limit
//      PAYMENT_TOO_FEW_OFFERS = -7, // not enough offers to satisfy path payment
//      PAYMENT_OVER_SENDMAX = -8,   // multi-path payment could not satisfy sendmax
//      PAYMENT_LOW_RESERVE = -9 // would create an account below the min reserve
//  };

//  ===========================================================================
public enum PaymentResultCode  {
  PAYMENT_SUCCESS(0),
  PAYMENT_SUCCESS_MULTI(1),
  PAYMENT_MALFORMED(-1),
  PAYMENT_UNDERFUNDED(-2),
  PAYMENT_NO_DESTINATION(-3),
  PAYMENT_NO_TRUST(-4),
  PAYMENT_NOT_AUTHORIZED(-5),
  PAYMENT_LINE_FULL(-6),
  PAYMENT_TOO_FEW_OFFERS(-7),
  PAYMENT_OVER_SENDMAX(-8),
  PAYMENT_LOW_RESERVE(-9),
  ;
  private int mValue;

  PaymentResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static PaymentResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return PAYMENT_SUCCESS;
      case 1: return PAYMENT_SUCCESS_MULTI;
      case -1: return PAYMENT_MALFORMED;
      case -2: return PAYMENT_UNDERFUNDED;
      case -3: return PAYMENT_NO_DESTINATION;
      case -4: return PAYMENT_NO_TRUST;
      case -5: return PAYMENT_NOT_AUTHORIZED;
      case -6: return PAYMENT_LINE_FULL;
      case -7: return PAYMENT_TOO_FEW_OFFERS;
      case -8: return PAYMENT_OVER_SENDMAX;
      case -9: return PAYMENT_LOW_RESERVE;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, PaymentResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
