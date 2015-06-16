// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct SCPEnvelope
//  {
//      SCPStatement statement;
//      Signature signature;
//  };

//  ===========================================================================
public class SCPEnvelope  {
  public SCPEnvelope () {}
  private SCPStatement statement;
  public SCPStatement getstatement() {
    return this.statement;
  }
  public void setstatement(SCPStatement value) {
    this.statement = value;
  }
  private Signature signature;
  public Signature getsignature() {
    return this.signature;
  }
  public void setsignature(Signature value) {
    this.signature = value;
  }
  public static void encode(XdrDataOutputStream stream, SCPEnvelope encodedSCPEnvelope) throws IOException{
    SCPStatement.encode(stream, encodedSCPEnvelope.statement);
    Signature.encode(stream, encodedSCPEnvelope.signature);
  }
  public static SCPEnvelope decode(XdrDataInputStream stream) throws IOException {
    SCPEnvelope decodedSCPEnvelope = new SCPEnvelope();
    decodedSCPEnvelope.statement = SCPStatement.decode(stream);
    decodedSCPEnvelope.signature = Signature.decode(stream);
    return decodedSCPEnvelope;
  }
}
