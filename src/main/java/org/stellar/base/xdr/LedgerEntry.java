// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union LedgerEntry switch (LedgerEntryType type)
//  {
//  case ACCOUNT:
//      AccountEntry account;
//  
//  case TRUSTLINE:
//      TrustLineEntry trustLine;
//  
//  case OFFER:
//      OfferEntry offer;
//  };

//  ===========================================================================
public class LedgerEntry  {
  public LedgerEntry () {}
  LedgerEntryType type;
  public LedgerEntryType getDiscriminant() {
    return this.type;
  }
  public void setDiscriminant(LedgerEntryType value) {
    this.type = value;
  }
  private AccountEntry account;
  public AccountEntry getaccount() {
    return this.account;
  }
  public void setaccount(AccountEntry value) {
    this.account = value;
  }
  private TrustLineEntry trustLine;
  public TrustLineEntry gettrustLine() {
    return this.trustLine;
  }
  public void settrustLine(TrustLineEntry value) {
    this.trustLine = value;
  }
  private OfferEntry offer;
  public OfferEntry getoffer() {
    return this.offer;
  }
  public void setoffer(OfferEntry value) {
    this.offer = value;
  }
  public static void encode(XdrDataOutputStream stream, LedgerEntry encodedLedgerEntry) throws IOException {
    switch (encodedLedgerEntry.getDiscriminant()) {
  case ACCOUNT:
  AccountEntry.encode(stream, encodedLedgerEntry.account);
  break;
  case TRUSTLINE:
  TrustLineEntry.encode(stream, encodedLedgerEntry.trustLine);
  break;
  case OFFER:
  OfferEntry.encode(stream, encodedLedgerEntry.offer);
  break;
  }
  }
  public static LedgerEntry decode(XdrDataInputStream stream) throws IOException {
    LedgerEntry decodedLedgerEntry = new LedgerEntry();
    switch (decodedLedgerEntry.getDiscriminant()) {
  case ACCOUNT:
  decodedLedgerEntry.account = AccountEntry.decode(stream);
  break;
  case TRUSTLINE:
  decodedLedgerEntry.trustLine = TrustLineEntry.decode(stream);
  break;
  case OFFER:
  decodedLedgerEntry.offer = OfferEntry.decode(stream);
  break;
  }
    return decodedLedgerEntry;
  }
}
