// Automatically generated on 2015-07-21T12:54:50-07:00
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
//      // amount and asset taken from the owner
//      Asset assetClaimed;
//      int64 amountClaimed;
//  
//      // amount and assetsent to the owner
//      Asset assetSend;
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
  private Asset assetClaimed;
  public Asset getassetClaimed() {
    return this.assetClaimed;
  }
  public void setassetClaimed(Asset value) {
    this.assetClaimed = value;
  }
  private Int64 amountClaimed;
  public Int64 getamountClaimed() {
    return this.amountClaimed;
  }
  public void setamountClaimed(Int64 value) {
    this.amountClaimed = value;
  }
  private Asset assetSend;
  public Asset getassetSend() {
    return this.assetSend;
  }
  public void setassetSend(Asset value) {
    this.assetSend = value;
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
    Asset.encode(stream, encodedClaimOfferAtom.assetClaimed);
    Int64.encode(stream, encodedClaimOfferAtom.amountClaimed);
    Asset.encode(stream, encodedClaimOfferAtom.assetSend);
    Int64.encode(stream, encodedClaimOfferAtom.amountSend);
  }
  public static ClaimOfferAtom decode(XdrDataInputStream stream) throws IOException {
    ClaimOfferAtom decodedClaimOfferAtom = new ClaimOfferAtom();
    decodedClaimOfferAtom.offerOwner = AccountID.decode(stream);
    decodedClaimOfferAtom.offerID = Uint64.decode(stream);
    decodedClaimOfferAtom.assetClaimed = Asset.decode(stream);
    decodedClaimOfferAtom.amountClaimed = Int64.decode(stream);
    decodedClaimOfferAtom.assetSend = Asset.decode(stream);
    decodedClaimOfferAtom.amountSend = Int64.decode(stream);
    return decodedClaimOfferAtom;
  }
}
