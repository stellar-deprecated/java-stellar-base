// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct CreatePassiveOfferOp
//  {
//      Asset selling; // A
//      Asset buying;  // B
//      int64 amount;  // amount taker gets. if set to 0, delete the offer
//      Price price;   // cost of A in terms of B
//  };

//  ===========================================================================
public class CreatePassiveOfferOp  {
  public CreatePassiveOfferOp () {}
  private Asset selling;
  public Asset getselling() {
    return this.selling;
  }
  public void setselling(Asset value) {
    this.selling = value;
  }
  private Asset buying;
  public Asset getbuying() {
    return this.buying;
  }
  public void setbuying(Asset value) {
    this.buying = value;
  }
  private Int64 amount;
  public Int64 getamount() {
    return this.amount;
  }
  public void setamount(Int64 value) {
    this.amount = value;
  }
  private Price price;
  public Price getprice() {
    return this.price;
  }
  public void setprice(Price value) {
    this.price = value;
  }
  public static void encode(XdrDataOutputStream stream, CreatePassiveOfferOp encodedCreatePassiveOfferOp) throws IOException{
    Asset.encode(stream, encodedCreatePassiveOfferOp.selling);
    Asset.encode(stream, encodedCreatePassiveOfferOp.buying);
    Int64.encode(stream, encodedCreatePassiveOfferOp.amount);
    Price.encode(stream, encodedCreatePassiveOfferOp.price);
  }
  public static CreatePassiveOfferOp decode(XdrDataInputStream stream) throws IOException {
    CreatePassiveOfferOp decodedCreatePassiveOfferOp = new CreatePassiveOfferOp();
    decodedCreatePassiveOfferOp.selling = Asset.decode(stream);
    decodedCreatePassiveOfferOp.buying = Asset.decode(stream);
    decodedCreatePassiveOfferOp.amount = Int64.decode(stream);
    decodedCreatePassiveOfferOp.price = Price.decode(stream);
    return decodedCreatePassiveOfferOp;
  }
}
