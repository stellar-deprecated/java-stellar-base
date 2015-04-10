// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct OfferEntry
//  {
//      AccountID accountID;
//      uint64 offerID;
//      Currency takerGets; // A
//      Currency takerPays; // B
//      int64 amount;       // amount of A
//  
//      /* price for this offer:
//          price of A in terms of B
//          price=AmountB/AmountA=priceNumerator/priceDenominator
//          price is after fees
//      */
//      Price price;
//  };

//  ===========================================================================
public class OfferEntry  {
  public OfferEntry () {}
  private AccountID accountID;
  public AccountID getaccountID() {
    return this.accountID;
  }
  public void setaccountID(AccountID value) {
    this.accountID = value;
  }
  private Uint64 offerID;
  public Uint64 getofferID() {
    return this.offerID;
  }
  public void setofferID(Uint64 value) {
    this.offerID = value;
  }
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
  public static void encode(XdrDataOutputStream stream, OfferEntry encodedOfferEntry) throws IOException{
    AccountID.encode(stream, encodedOfferEntry.accountID);
    Uint64.encode(stream, encodedOfferEntry.offerID);
    Currency.encode(stream, encodedOfferEntry.takerGets);
    Currency.encode(stream, encodedOfferEntry.takerPays);
    Int64.encode(stream, encodedOfferEntry.amount);
    Price.encode(stream, encodedOfferEntry.price);
  }
  public static OfferEntry decode(XdrDataInputStream stream) throws IOException {
    OfferEntry decodedOfferEntry = new OfferEntry();
    decodedOfferEntry.accountID = AccountID.decode(stream);
    decodedOfferEntry.offerID = Uint64.decode(stream);
    decodedOfferEntry.takerGets = Currency.decode(stream);
    decodedOfferEntry.takerPays = Currency.decode(stream);
    decodedOfferEntry.amount = Int64.decode(stream);
    decodedOfferEntry.price = Price.decode(stream);
    return decodedOfferEntry;
  }
}
