// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct PaymentSuccessMultiResult
//  {
//      ClaimOfferAtom offers<>;
//      SimplePaymentResult last;
//  };

//  ===========================================================================
public class PaymentSuccessMultiResult  {
  public PaymentSuccessMultiResult () {}
  private ClaimOfferAtom[] offers;
  public ClaimOfferAtom[] getoffers() {
    return this.offers;
  }
  public void setoffers(ClaimOfferAtom[] value) {
    this.offers = value;
  }
  private SimplePaymentResult last;
  public SimplePaymentResult getlast() {
    return this.last;
  }
  public void setlast(SimplePaymentResult value) {
    this.last = value;
  }
  public static void encode(XdrDataOutputStream stream, PaymentSuccessMultiResult encodedPaymentSuccessMultiResult) throws IOException{
    int offerssize = encodedPaymentSuccessMultiResult.getoffers().length;
    stream.writeInt(offerssize);
    for (int i = 0; i < offerssize; i++) {
      ClaimOfferAtom.encode(stream, encodedPaymentSuccessMultiResult.offers[i]);
    }
    SimplePaymentResult.encode(stream, encodedPaymentSuccessMultiResult.last);
  }
  public static PaymentSuccessMultiResult decode(XdrDataInputStream stream) throws IOException {
    PaymentSuccessMultiResult decodedPaymentSuccessMultiResult = new PaymentSuccessMultiResult();
    int offerssize = stream.readInt();
    decodedPaymentSuccessMultiResult.offers = new ClaimOfferAtom[offerssize];
    for (int i = 0; i < offerssize; i++) {
      decodedPaymentSuccessMultiResult.offers[i] = ClaimOfferAtom.decode(stream);
    }
    decodedPaymentSuccessMultiResult.last = SimplePaymentResult.decode(stream);
    return decodedPaymentSuccessMultiResult;
  }
}
