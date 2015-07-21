// Automatically generated on 2015-07-21T12:54:49-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct StellarValue
//  {
//      Hash txSetHash;   // transaction set to apply to previous ledger
//      uint64 closeTime; // network close time
//  
//      // upgrades to apply to the previous ledger (usually empty)
//      // this is a vector of encoded 'LedgerUpgrade' so that nodes can drop
//      // unknown steps during consensus if needed.
//      // see notes below on 'LedgerUpgrade' for more detail
//      UpgradeType upgrades<4>;
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
public class StellarValue  {
  public StellarValue () {}
  private Hash txSetHash;
  public Hash gettxSetHash() {
    return this.txSetHash;
  }
  public void settxSetHash(Hash value) {
    this.txSetHash = value;
  }
  private Uint64 closeTime;
  public Uint64 getcloseTime() {
    return this.closeTime;
  }
  public void setcloseTime(Uint64 value) {
    this.closeTime = value;
  }
  private UpgradeType[] upgrades;
  public UpgradeType[] getupgrades() {
    return this.upgrades;
  }
  public void setupgrades(UpgradeType[] value) {
    this.upgrades = value;
  }
  private StellarValueExt ext;
  public StellarValueExt getext() {
    return this.ext;
  }
  public void setext(StellarValueExt value) {
    this.ext = value;
  }
  public static void encode(XdrDataOutputStream stream, StellarValue encodedStellarValue) throws IOException{
    Hash.encode(stream, encodedStellarValue.txSetHash);
    Uint64.encode(stream, encodedStellarValue.closeTime);
    int upgradessize = encodedStellarValue.getupgrades().length;
    stream.writeInt(upgradessize);
    for (int i = 0; i < upgradessize; i++) {
      UpgradeType.encode(stream, encodedStellarValue.upgrades[i]);
    }
    StellarValueExt.encode(stream, encodedStellarValue.ext);
  }
  public static StellarValue decode(XdrDataInputStream stream) throws IOException {
    StellarValue decodedStellarValue = new StellarValue();
    decodedStellarValue.txSetHash = Hash.decode(stream);
    decodedStellarValue.closeTime = Uint64.decode(stream);
    int upgradessize = stream.readInt();
    decodedStellarValue.upgrades = new UpgradeType[upgradessize];
    for (int i = 0; i < upgradessize; i++) {
      decodedStellarValue.upgrades[i] = UpgradeType.decode(stream);
    }
    decodedStellarValue.ext = StellarValueExt.decode(stream);
    return decodedStellarValue;
  }

  public static class StellarValueExt {
    public StellarValueExt () {}
    Integer v;
    public Integer getDiscriminant() {
      return this.v;
    }
    public void setDiscriminant(Integer value) {
      this.v = value;
    }
    public static void encode(XdrDataOutputStream stream, StellarValueExt encodedStellarValueExt) throws IOException {
    stream.writeInt(encodedStellarValueExt.getDiscriminant().intValue());
    switch (encodedStellarValueExt.getDiscriminant()) {
    case 0:
    break;
    }
    }
    public static StellarValueExt decode(XdrDataInputStream stream) throws IOException {
      StellarValueExt decodedStellarValueExt = new StellarValueExt();
      switch (decodedStellarValueExt.getDiscriminant()) {
    case 0:
    break;
    }
      return decodedStellarValueExt;
    }

  }
}