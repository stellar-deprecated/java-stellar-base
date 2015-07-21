// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct ManageOfferOp
//  {
//      Asset selling;
//      Asset buying;
//      int64 amount; // amount being sold. if set to 0, delete the offer
//      Price price;  // price of thing being sold in terms of what you are buying
//  
//      // 0=create a new offer, otherwise edit an existing offer
//      uint64 offerID;
//  };

//  ===========================================================================
public class ManageOfferOp  {
  public ManageOfferOp () {}
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
  private Uint64 offerID;
  public Uint64 getofferID() {
    return this.offerID;
  }
  public void setofferID(Uint64 value) {
    this.offerID = value;
  }
  public static void encode(XdrDataOutputStream stream, ManageOfferOp encodedManageOfferOp) throws IOException{
    Asset.encode(stream, encodedManageOfferOp.selling);
    Asset.encode(stream, encodedManageOfferOp.buying);
    Int64.encode(stream, encodedManageOfferOp.amount);
    Price.encode(stream, encodedManageOfferOp.price);
    Uint64.encode(stream, encodedManageOfferOp.offerID);
  }
  public static ManageOfferOp decode(XdrDataInputStream stream) throws IOException {
    ManageOfferOp decodedManageOfferOp = new ManageOfferOp();
    decodedManageOfferOp.selling = Asset.decode(stream);
    decodedManageOfferOp.buying = Asset.decode(stream);
    decodedManageOfferOp.amount = Int64.decode(stream);
    decodedManageOfferOp.price = Price.decode(stream);
    decodedManageOfferOp.offerID = Uint64.decode(stream);
    return decodedManageOfferOp;
  }
}
