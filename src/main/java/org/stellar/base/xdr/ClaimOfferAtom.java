// Automatically generated on 2015-05-27T10:24:45-07:00
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
//      // should we also include the amount that the owner gets in return?
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
  public static void encode(XdrDataOutputStream stream, ClaimOfferAtom encodedClaimOfferAtom) throws IOException{
    AccountID.encode(stream, encodedClaimOfferAtom.offerOwner);
    Uint64.encode(stream, encodedClaimOfferAtom.offerID);
    Currency.encode(stream, encodedClaimOfferAtom.currencyClaimed);
    Int64.encode(stream, encodedClaimOfferAtom.amountClaimed);
  }
  public static ClaimOfferAtom decode(XdrDataInputStream stream) throws IOException {
    ClaimOfferAtom decodedClaimOfferAtom = new ClaimOfferAtom();
    decodedClaimOfferAtom.offerOwner = AccountID.decode(stream);
    decodedClaimOfferAtom.offerID = Uint64.decode(stream);
    decodedClaimOfferAtom.currencyClaimed = Currency.decode(stream);
    decodedClaimOfferAtom.amountClaimed = Int64.decode(stream);
    return decodedClaimOfferAtom;
  }
}
