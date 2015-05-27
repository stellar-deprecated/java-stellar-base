// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct StellarBallot
//  {
//      uint256 nodeID;
//      Signature signature;
//      StellarBallotValue value;
//  };

//  ===========================================================================
public class StellarBallot  {
  public StellarBallot () {}
  private Uint256 nodeID;
  public Uint256 getnodeID() {
    return this.nodeID;
  }
  public void setnodeID(Uint256 value) {
    this.nodeID = value;
  }
  private Signature signature;
  public Signature getsignature() {
    return this.signature;
  }
  public void setsignature(Signature value) {
    this.signature = value;
  }
  private StellarBallotValue value;
  public StellarBallotValue getvalue() {
    return this.value;
  }
  public void setvalue(StellarBallotValue value) {
    this.value = value;
  }
  public static void encode(XdrDataOutputStream stream, StellarBallot encodedStellarBallot) throws IOException{
    Uint256.encode(stream, encodedStellarBallot.nodeID);
    Signature.encode(stream, encodedStellarBallot.signature);
    StellarBallotValue.encode(stream, encodedStellarBallot.value);
  }
  public static StellarBallot decode(XdrDataInputStream stream) throws IOException {
    StellarBallot decodedStellarBallot = new StellarBallot();
    decodedStellarBallot.nodeID = Uint256.decode(stream);
    decodedStellarBallot.signature = Signature.decode(stream);
    decodedStellarBallot.value = StellarBallotValue.decode(stream);
    return decodedStellarBallot;
  }
}
