// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct ManageOfferOp
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
public class ManageOfferOp  {
  public ManageOfferOp () {}
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
  public static void encode(XdrDataOutputStream stream, ManageOfferOp encodedManageOfferOp) throws IOException{
    Currency.encode(stream, encodedManageOfferOp.takerGets);
    Currency.encode(stream, encodedManageOfferOp.takerPays);
    Int64.encode(stream, encodedManageOfferOp.amount);
    Price.encode(stream, encodedManageOfferOp.price);
    Uint64.encode(stream, encodedManageOfferOp.offerID);
  }
  public static ManageOfferOp decode(XdrDataInputStream stream) throws IOException {
    ManageOfferOp decodedManageOfferOp = new ManageOfferOp();
    decodedManageOfferOp.takerGets = Currency.decode(stream);
    decodedManageOfferOp.takerPays = Currency.decode(stream);
    decodedManageOfferOp.amount = Int64.decode(stream);
    decodedManageOfferOp.price = Price.decode(stream);
    decodedManageOfferOp.offerID = Uint64.decode(stream);
    return decodedManageOfferOp;
  }
}
