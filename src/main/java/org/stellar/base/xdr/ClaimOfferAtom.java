// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct ClaimOfferAtom
//  {
//      // emited to identify the offer
//      AccountID offerOwner; // Account that owns the offer
//      uint64 offerID;
//  
//      // amount and currency taken from the owner
//      Currency currencyClaimed;
//      int64 amountClaimed;
//  
//      // amount and currencysent to the owner
//      Currency currencySend;
//      int64 amountSend;
//  };

//  ===========================================================================
public class ClaimOfferAtom  {
  public ClaimOfferAtom () {}
  private AccountID offerOwner;
  public AccountID getofferOwner() {
    return this.offerOwner;
  }
  public void setofferOwner(AccountID value) {
    this.offerOwner = value;
  }
  private Uint64 offerID;
  public Uint64 getofferID() {
    return this.offerID;
  }
  public void setofferID(Uint64 value) {
    this.offerID = value;
  }
  private Currency currencyClaimed;
  public Currency getcurrencyClaimed() {
    return this.currencyClaimed;
  }
  public void setcurrencyClaimed(Currency value) {
    this.currencyClaimed = value;
  }
  private Int64 amountClaimed;
  public Int64 getamountClaimed() {
    return this.amountClaimed;
  }
  public void setamountClaimed(Int64 value) {
    this.amountClaimed = value;
  }
  private Currency currencySend;
  public Currency getcurrencySend() {
    return this.currencySend;
  }
  public void setcurrencySend(Currency value) {
    this.currencySend = value;
  }
  private Int64 amountSend;
  public Int64 getamountSend() {
    return this.amountSend;
  }
  public void setamountSend(Int64 value) {
    this.amountSend = value;
  }
  public static void encode(XdrDataOutputStream stream, ClaimOfferAtom encodedClaimOfferAtom) throws IOException{
    AccountID.encode(stream, encodedClaimOfferAtom.offerOwner);
    Uint64.encode(stream, encodedClaimOfferAtom.offerID);
    Currency.encode(stream, encodedClaimOfferAtom.currencyClaimed);
    Int64.encode(stream, encodedClaimOfferAtom.amountClaimed);
    Currency.encode(stream, encodedClaimOfferAtom.currencySend);
    Int64.encode(stream, encodedClaimOfferAtom.amountSend);
  }
  public static ClaimOfferAtom decode(XdrDataInputStream stream) throws IOException {
    ClaimOfferAtom decodedClaimOfferAtom = new ClaimOfferAtom();
    decodedClaimOfferAtom.offerOwner = AccountID.decode(stream);
    decodedClaimOfferAtom.offerID = Uint64.decode(stream);
    decodedClaimOfferAtom.currencyClaimed = Currency.decode(stream);
    decodedClaimOfferAtom.amountClaimed = Int64.decode(stream);
    decodedClaimOfferAtom.currencySend = Currency.decode(stream);
    decodedClaimOfferAtom.amountSend = Int64.decode(stream);
    return decodedClaimOfferAtom;
  }
}
