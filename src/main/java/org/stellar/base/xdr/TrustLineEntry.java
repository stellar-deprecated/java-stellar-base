// Automatically generated on 2015-06-16T15:35:11-07:00
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
//      int64 limit;  // balance cannot be above this
//      uint32 flags; // see TrustLineFlags
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
  private Uint32 flags;
  public Uint32 getflags() {
    return this.flags;
  }
  public void setflags(Uint32 value) {
    this.flags = value;
  }
  public static void encode(XdrDataOutputStream stream, TrustLineEntry encodedTrustLineEntry) throws IOException{
    AccountID.encode(stream, encodedTrustLineEntry.accountID);
    Currency.encode(stream, encodedTrustLineEntry.currency);
    Int64.encode(stream, encodedTrustLineEntry.balance);
    Int64.encode(stream, encodedTrustLineEntry.limit);
    Uint32.encode(stream, encodedTrustLineEntry.flags);
  }
  public static TrustLineEntry decode(XdrDataInputStream stream) throws IOException {
    TrustLineEntry decodedTrustLineEntry = new TrustLineEntry();
    decodedTrustLineEntry.accountID = AccountID.decode(stream);
    decodedTrustLineEntry.currency = Currency.decode(stream);
    decodedTrustLineEntry.balance = Int64.decode(stream);
    decodedTrustLineEntry.limit = Int64.decode(stream);
    decodedTrustLineEntry.flags = Uint32.decode(stream);
    return decodedTrustLineEntry;
  }
}
