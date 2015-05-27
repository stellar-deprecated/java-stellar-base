// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union PaymentResult switch (PaymentResultCode code)
//  {
//  case PAYMENT_SUCCESS:
//      void;
//  default:
//      void;
//  };

//  ===========================================================================
public class PaymentResult  {
  public PaymentResult () {}
  PaymentResultCode code;
  public PaymentResultCode getDiscriminant() {
    return this.code;
  }
  public void setDiscriminant(PaymentResultCode value) {
    this.code = value;
  }
  public static void encode(XdrDataOutputStream stream, PaymentResult encodedPaymentResult) throws IOException {
    switch (encodedPaymentResult.getDiscriminant()) {
  case PAYMENT_SUCCESS:
  break;
  default:
  break;
  }
  }
  public static PaymentResult decode(XdrDataInputStream stream) throws IOException {
    PaymentResult decodedPaymentResult = new PaymentResult();
    switch (decodedPaymentResult.getDiscriminant()) {
  case PAYMENT_SUCCESS:
  break;
  default:
  break;
  }
    return decodedPaymentResult;
  }
}
