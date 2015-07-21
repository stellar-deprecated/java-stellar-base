// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union PathPaymentResult switch (PathPaymentResultCode code)
//  {
//  case PATH_PAYMENT_SUCCESS:
//      struct
//      {
//          ClaimOfferAtom offers<>;
//          SimplePaymentResult last;
//      } success;
//  default:
//      void;
//  };

//  ===========================================================================
public class PathPaymentResult  {
  public PathPaymentResult () {}
  PathPaymentResultCode code;
  public PathPaymentResultCode getDiscriminant() {
    return this.code;
  }
  public void setDiscriminant(PathPaymentResultCode value) {
    this.code = value;
  }
  private PathPaymentResultSuccess success;
  public PathPaymentResultSuccess getsuccess() {
    return this.success;
  }
  public void setsuccess(PathPaymentResultSuccess value) {
    this.success = value;
  }
  public static void encode(XdrDataOutputStream stream, PathPaymentResult encodedPathPaymentResult) throws IOException {
  stream.writeInt(encodedPathPaymentResult.getDiscriminant().getValue());
  switch (encodedPathPaymentResult.getDiscriminant()) {
  case PATH_PAYMENT_SUCCESS:
  PathPaymentResultSuccess.encode(stream, encodedPathPaymentResult.success);
  break;
  default:
  break;
  }
  }
  public static PathPaymentResult decode(XdrDataInputStream stream) throws IOException {
    PathPaymentResult decodedPathPaymentResult = new PathPaymentResult();
    switch (decodedPathPaymentResult.getDiscriminant()) {
  case PATH_PAYMENT_SUCCESS:
  decodedPathPaymentResult.success = PathPaymentResultSuccess.decode(stream);
  break;
  default:
  break;
  }
    return decodedPathPaymentResult;
  }

  public static class PathPaymentResultSuccess {
    public PathPaymentResultSuccess () {}
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
    public static void encode(XdrDataOutputStream stream, PathPaymentResultSuccess encodedPathPaymentResultSuccess) throws IOException{
      int offerssize = encodedPathPaymentResultSuccess.getoffers().length;
      stream.writeInt(offerssize);
      for (int i = 0; i < offerssize; i++) {
        ClaimOfferAtom.encode(stream, encodedPathPaymentResultSuccess.offers[i]);
      }
      SimplePaymentResult.encode(stream, encodedPathPaymentResultSuccess.last);
    }
    public static PathPaymentResultSuccess decode(XdrDataInputStream stream) throws IOException {
      PathPaymentResultSuccess decodedPathPaymentResultSuccess = new PathPaymentResultSuccess();
      int offerssize = stream.readInt();
      decodedPathPaymentResultSuccess.offers = new ClaimOfferAtom[offerssize];
      for (int i = 0; i < offerssize; i++) {
        decodedPathPaymentResultSuccess.offers[i] = ClaimOfferAtom.decode(stream);
      }
      decodedPathPaymentResultSuccess.last = SimplePaymentResult.decode(stream);
      return decodedPathPaymentResultSuccess;
    }

  }
}
