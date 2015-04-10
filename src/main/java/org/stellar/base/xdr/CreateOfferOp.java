// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct CreateOfferOp
//  {
//      Currency takerGets;
//      Currency takerPays;
//      int64 amount; // amount taker gets. if set to 0, delete the offer
//      Price price;  // =takerPaysAmount/takerGetsAmount
//  
//      // 0=create a new offer, otherwise edit an existing offer
//      uint64 offerID;
//  };

//  ===========================================================================
public class CreateOfferOp  {
  public CreateOfferOp () {}
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
  private Uint64 offerID;
  public Uint64 getofferID() {
    return this.offerID;
  }
  public void setofferID(Uint64 value) {
    this.offerID = value;
  }
  public static void encode(XdrDataOutputStream stream, CreateOfferOp encodedCreateOfferOp) throws IOException{
    Currency.encode(stream, encodedCreateOfferOp.takerGets);
    Currency.encode(stream, encodedCreateOfferOp.takerPays);
    Int64.encode(stream, encodedCreateOfferOp.amount);
    Price.encode(stream, encodedCreateOfferOp.price);
    Uint64.encode(stream, encodedCreateOfferOp.offerID);
  }
  public static CreateOfferOp decode(XdrDataInputStream stream) throws IOException {
    CreateOfferOp decodedCreateOfferOp = new CreateOfferOp();
    decodedCreateOfferOp.takerGets = Currency.decode(stream);
    decodedCreateOfferOp.takerPays = Currency.decode(stream);
    decodedCreateOfferOp.amount = Int64.decode(stream);
    decodedCreateOfferOp.price = Price.decode(stream);
    decodedCreateOfferOp.offerID = Uint64.decode(stream);
    return decodedCreateOfferOp;
  }
}
