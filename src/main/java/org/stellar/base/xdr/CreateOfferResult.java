// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union CreateOfferResult switch (CreateOfferResultCode code)
//  {
//  case CREATE_OFFER_SUCCESS:
//      CreateOfferSuccessResult success;
//  default:
//      void;
//  };

//  ===========================================================================
public class CreateOfferResult  {
  public CreateOfferResult () {}
  CreateOfferResultCode code;
  public CreateOfferResultCode getDiscriminant() {
    return this.code;
  }
  public void setDiscriminant(CreateOfferResultCode value) {
    this.code = value;
  }
  private CreateOfferSuccessResult success;
  public CreateOfferSuccessResult getsuccess() {
    return this.success;
  }
  public void setsuccess(CreateOfferSuccessResult value) {
    this.success = value;
  }
  public static void encode(XdrDataOutputStream stream, CreateOfferResult encodedCreateOfferResult) throws IOException {
    switch (encodedCreateOfferResult.getDiscriminant()) {
  case CREATE_OFFER_SUCCESS:
  CreateOfferSuccessResult.encode(stream, encodedCreateOfferResult.success);
  break;
  default:
  break;
  }
  }
  public static CreateOfferResult decode(XdrDataInputStream stream) throws IOException {
    CreateOfferResult decodedCreateOfferResult = new CreateOfferResult();
    switch (decodedCreateOfferResult.getDiscriminant()) {
  case CREATE_OFFER_SUCCESS:
  decodedCreateOfferResult.success = CreateOfferSuccessResult.decode(stream);
  break;
  default:
  break;
  }
    return decodedCreateOfferResult;
  }
}
