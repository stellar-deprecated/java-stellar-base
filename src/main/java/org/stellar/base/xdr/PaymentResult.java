// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union PaymentResult switch (PaymentResultCode code)
//  {
//  case PAYMENT_SUCCESS:
//      void;
//  case PAYMENT_SUCCESS_MULTI:
//      PaymentSuccessMultiResult multi;
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
  private PaymentSuccessMultiResult multi;
  public PaymentSuccessMultiResult getmulti() {
    return this.multi;
  }
  public void setmulti(PaymentSuccessMultiResult value) {
    this.multi = value;
  }
  public static void encode(XdrDataOutputStream stream, PaymentResult encodedPaymentResult) throws IOException {
    switch (encodedPaymentResult.getDiscriminant()) {
  case PAYMENT_SUCCESS:
  break;
  case PAYMENT_SUCCESS_MULTI:
  PaymentSuccessMultiResult.encode(stream, encodedPaymentResult.multi);
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
  case PAYMENT_SUCCESS_MULTI:
  decodedPaymentResult.multi = PaymentSuccessMultiResult.decode(stream);
  break;
  default:
  break;
  }
    return decodedPaymentResult;
  }
}
