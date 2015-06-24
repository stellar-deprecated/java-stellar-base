// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct StellarValue
//  {
//      uint32 ledgerVersion;
//      Hash txSetHash;
//      uint64 closeTime;
//      uint32 baseFee;
//  };

//  ===========================================================================
public class StellarValue  {
  public StellarValue () {}
  private Uint32 ledgerVersion;
  public Uint32 getledgerVersion() {
    return this.ledgerVersion;
  }
  public void setledgerVersion(Uint32 value) {
    this.ledgerVersion = value;
  }
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
  private Uint32 baseFee;
  public Uint32 getbaseFee() {
    return this.baseFee;
  }
  public void setbaseFee(Uint32 value) {
    this.baseFee = value;
  }
  public static void encode(XdrDataOutputStream stream, StellarValue encodedStellarValue) throws IOException{
    Uint32.encode(stream, encodedStellarValue.ledgerVersion);
    Hash.encode(stream, encodedStellarValue.txSetHash);
    Uint64.encode(stream, encodedStellarValue.closeTime);
    Uint32.encode(stream, encodedStellarValue.baseFee);
  }
  public static StellarValue decode(XdrDataInputStream stream) throws IOException {
    StellarValue decodedStellarValue = new StellarValue();
    decodedStellarValue.ledgerVersion = Uint32.decode(stream);
    decodedStellarValue.txSetHash = Hash.decode(stream);
    decodedStellarValue.closeTime = Uint64.decode(stream);
    decodedStellarValue.baseFee = Uint32.decode(stream);
    return decodedStellarValue;
  }
}
