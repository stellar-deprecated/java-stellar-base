// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct StellarBallotValue
//  {
//      Hash txSetHash;
//      uint64 closeTime;
//      uint32 baseFee;
//  };

//  ===========================================================================
public class StellarBallotValue  {
  public StellarBallotValue () {}
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
  public static void encode(XdrDataOutputStream stream, StellarBallotValue encodedStellarBallotValue) throws IOException{
    Hash.encode(stream, encodedStellarBallotValue.txSetHash);
    Uint64.encode(stream, encodedStellarBallotValue.closeTime);
    Uint32.encode(stream, encodedStellarBallotValue.baseFee);
  }
  public static StellarBallotValue decode(XdrDataInputStream stream) throws IOException {
    StellarBallotValue decodedStellarBallotValue = new StellarBallotValue();
    decodedStellarBallotValue.txSetHash = Hash.decode(stream);
    decodedStellarBallotValue.closeTime = Uint64.decode(stream);
    decodedStellarBallotValue.baseFee = Uint32.decode(stream);
    return decodedStellarBallotValue;
  }
}
