// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TrustLineEntry
//  {
//      AccountID accountID; // account this trustline belongs to
//      Currency currency;   // currency (with issuer)
//      int64 balance;       // how much of this currency the user has.
//                           // Currency defines the unit for this;
//  
//      int64 limit;     // balance cannot be above this
//      bool authorized; // issuer has authorized account to hold its credit
//  };

//  ===========================================================================
public class TrustLineEntry  {
  public TrustLineEntry () {}
  private AccountID accountID;
  public AccountID getaccountID() {
    return this.accountID;
  }
  public void setaccountID(AccountID value) {
    this.accountID = value;
  }
  private Currency currency;
  public Currency getcurrency() {
    return this.currency;
  }
  public void setcurrency(Currency value) {
    this.currency = value;
  }
  private Int64 balance;
  public Int64 getbalance() {
    return this.balance;
  }
  public void setbalance(Int64 value) {
    this.balance = value;
  }
  private Int64 limit;
  public Int64 getlimit() {
    return this.limit;
  }
  public void setlimit(Int64 value) {
    this.limit = value;
  }
  private Boolean authorized;
  public Boolean getauthorized() {
    return this.authorized;
  }
  public void setauthorized(Boolean value) {
    this.authorized = value;
  }
  public static void encode(XdrDataOutputStream stream, TrustLineEntry encodedTrustLineEntry) throws IOException{
    AccountID.encode(stream, encodedTrustLineEntry.accountID);
    Currency.encode(stream, encodedTrustLineEntry.currency);
    Int64.encode(stream, encodedTrustLineEntry.balance);
    Int64.encode(stream, encodedTrustLineEntry.limit);
    stream.writeBoolean(encodedTrustLineEntry.authorized);
  }
  public static TrustLineEntry decode(XdrDataInputStream stream) throws IOException {
    TrustLineEntry decodedTrustLineEntry = new TrustLineEntry();
    decodedTrustLineEntry.accountID = AccountID.decode(stream);
    decodedTrustLineEntry.currency = Currency.decode(stream);
    decodedTrustLineEntry.balance = Int64.decode(stream);
    decodedTrustLineEntry.limit = Int64.decode(stream);
    decodedTrustLineEntry.authorized = stream.readBoolean();
    return decodedTrustLineEntry;
  }
}
