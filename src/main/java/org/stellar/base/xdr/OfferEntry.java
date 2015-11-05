// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct OfferEntry
//  {
//      AccountID sellerID;
//      uint64 offerID;
//      Asset selling; // A
//      Asset buying;  // B
//      int64 amount;  // amount of A
//  
//      /* price for this offer:
//          price of A in terms of B
//          price=AmountB/AmountA=priceNumerator/priceDenominator
//          price is after fees
//      */
//      Price price;
//      uint32 flags; // see OfferEntryFlags
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
public class OfferEntry  {
  public OfferEntry () {}
  private AccountID sellerID;
  public AccountID getsellerID() {
    return this.sellerID;
  }
  public void setsellerID(AccountID value) {
    this.sellerID = value;
  }
  private Uint64 offerID;
  public Uint64 getofferID() {
    return this.offerID;
  }
  public void setofferID(Uint64 value) {
    this.offerID = value;
  }
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
  private Uint32 flags;
  public Uint32 getflags() {
    return this.flags;
  }
  public void setflags(Uint32 value) {
    this.flags = value;
  }
  private OfferEntryExt ext;
  public OfferEntryExt getext() {
    return this.ext;
  }
  public void setext(OfferEntryExt value) {
    this.ext = value;
  }
  public static void encode(XdrDataOutputStream stream, OfferEntry encodedOfferEntry) throws IOException{
    AccountID.encode(stream, encodedOfferEntry.sellerID);
    Uint64.encode(stream, encodedOfferEntry.offerID);
    Asset.encode(stream, encodedOfferEntry.selling);
    Asset.encode(stream, encodedOfferEntry.buying);
    Int64.encode(stream, encodedOfferEntry.amount);
    Price.encode(stream, encodedOfferEntry.price);
    Uint32.encode(stream, encodedOfferEntry.flags);
    OfferEntryExt.encode(stream, encodedOfferEntry.ext);
  }
  public static OfferEntry decode(XdrDataInputStream stream) throws IOException {
    OfferEntry decodedOfferEntry = new OfferEntry();
    decodedOfferEntry.sellerID = AccountID.decode(stream);
    decodedOfferEntry.offerID = Uint64.decode(stream);
    decodedOfferEntry.selling = Asset.decode(stream);
    decodedOfferEntry.buying = Asset.decode(stream);
    decodedOfferEntry.amount = Int64.decode(stream);
    decodedOfferEntry.price = Price.decode(stream);
    decodedOfferEntry.flags = Uint32.decode(stream);
    decodedOfferEntry.ext = OfferEntryExt.decode(stream);
    return decodedOfferEntry;
  }

  public static class OfferEntryExt {
    public OfferEntryExt () {}
    Integer v;
    public Integer getDiscriminant() {
      return this.v;
    }
    public void setDiscriminant(Integer value) {
      this.v = value;
    }
    public static void encode(XdrDataOutputStream stream, OfferEntryExt encodedOfferEntryExt) throws IOException {
    stream.writeInt(encodedOfferEntryExt.getDiscriminant().intValue());
    switch (encodedOfferEntryExt.getDiscriminant()) {
    case 0:
    break;
    }
    }
    public static OfferEntryExt decode(XdrDataInputStream stream) throws IOException {
      OfferEntryExt decodedOfferEntryExt = new OfferEntryExt();
      switch (decodedOfferEntryExt.getDiscriminant()) {
    case 0:
    break;
    }
      return decodedOfferEntryExt;
    }

  }
}
