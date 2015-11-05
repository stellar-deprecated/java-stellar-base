// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TrustLineEntry
//  {
//      AccountID accountID; // account this trustline belongs to
//      Asset asset;         // type of asset (with issuer)
//      int64 balance;       // how much of this asset the user has.
//                           // Asset defines the unit for this;
//  
//      int64 limit;  // balance cannot be above this
//      uint32 flags; // see TrustLineFlags
//  
//      // reserved for future use
//      union switch (int v)
//      {
//      case 0:
//          void;
//      }
//      ext;
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
  private Asset asset;
  public Asset getasset() {
    return this.asset;
  }
  public void setasset(Asset value) {
    this.asset = value;
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
  private TrustLineEntryExt ext;
  public TrustLineEntryExt getext() {
    return this.ext;
  }
  public void setext(TrustLineEntryExt value) {
    this.ext = value;
  }
  public static void encode(XdrDataOutputStream stream, TrustLineEntry encodedTrustLineEntry) throws IOException{
    AccountID.encode(stream, encodedTrustLineEntry.accountID);
    Asset.encode(stream, encodedTrustLineEntry.asset);
    Int64.encode(stream, encodedTrustLineEntry.balance);
    Int64.encode(stream, encodedTrustLineEntry.limit);
    Uint32.encode(stream, encodedTrustLineEntry.flags);
    TrustLineEntryExt.encode(stream, encodedTrustLineEntry.ext);
  }
  public static TrustLineEntry decode(XdrDataInputStream stream) throws IOException {
    TrustLineEntry decodedTrustLineEntry = new TrustLineEntry();
    decodedTrustLineEntry.accountID = AccountID.decode(stream);
    decodedTrustLineEntry.asset = Asset.decode(stream);
    decodedTrustLineEntry.balance = Int64.decode(stream);
    decodedTrustLineEntry.limit = Int64.decode(stream);
    decodedTrustLineEntry.flags = Uint32.decode(stream);
    decodedTrustLineEntry.ext = TrustLineEntryExt.decode(stream);
    return decodedTrustLineEntry;
  }

  public static class TrustLineEntryExt {
    public TrustLineEntryExt () {}
    Integer v;
    public Integer getDiscriminant() {
      return this.v;
    }
    public void setDiscriminant(Integer value) {
      this.v = value;
    }
    public static void encode(XdrDataOutputStream stream, TrustLineEntryExt encodedTrustLineEntryExt) throws IOException {
    stream.writeInt(encodedTrustLineEntryExt.getDiscriminant().intValue());
    switch (encodedTrustLineEntryExt.getDiscriminant()) {
    case 0:
    break;
    }
    }
    public static TrustLineEntryExt decode(XdrDataInputStream stream) throws IOException {
      TrustLineEntryExt decodedTrustLineEntryExt = new TrustLineEntryExt();
      switch (decodedTrustLineEntryExt.getDiscriminant()) {
    case 0:
    break;
    }
      return decodedTrustLineEntryExt;
    }

  }
}
