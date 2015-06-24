// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct CreatePassiveOfferOp
//  {
//      Currency takerGets;
//      Currency takerPays;
//      int64 amount; // amount taker gets. if set to 0, delete the offer
//      Price price;  // =takerPaysAmount/takerGetsAmount
//  };

//  ===========================================================================
public class CreatePassiveOfferOp  {
  public CreatePassiveOfferOp () {}
  private Currency takerGets;
  public Currency gettakerGets() {
    return this.takerGets;
  }
  public void settakerGets(Currency value) {
    this.takerGets = value;
  }
  private Currency takerPays;
  public Currency gettakerPays() {
    return this.takerPays;
  }
  public void settakerPays(Currency value) {
    this.takerPays = value;
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
    Currency.encode(stream, encodedCreatePassiveOfferOp.takerGets);
    Currency.encode(stream, encodedCreatePassiveOfferOp.takerPays);
    Int64.encode(stream, encodedCreatePassiveOfferOp.amount);
    Price.encode(stream, encodedCreatePassiveOfferOp.price);
  }
  public static CreatePassiveOfferOp decode(XdrDataInputStream stream) throws IOException {
    CreatePassiveOfferOp decodedCreatePassiveOfferOp = new CreatePassiveOfferOp();
    decodedCreatePassiveOfferOp.takerGets = Currency.decode(stream);
    decodedCreatePassiveOfferOp.takerPays = Currency.decode(stream);
    decodedCreatePassiveOfferOp.amount = Int64.decode(stream);
    decodedCreatePassiveOfferOp.price = Price.decode(stream);
    return decodedCreatePassiveOfferOp;
  }
}
