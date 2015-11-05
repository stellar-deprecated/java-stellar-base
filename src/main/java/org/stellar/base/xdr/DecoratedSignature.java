// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct DecoratedSignature
//  {
//      SignatureHint hint;  // last 4 bytes of the public key, used as a hint
//      Signature signature; // actual signature
//  };

//  ===========================================================================
public class DecoratedSignature  {
  public DecoratedSignature () {}
  private SignatureHint hint;
  public SignatureHint gethint() {
    return this.hint;
  }
  public void sethint(SignatureHint value) {
    this.hint = value;
  }
  private Signature signature;
  public Signature getsignature() {
    return this.signature;
  }
  public void setsignature(Signature value) {
    this.signature = value;
  }
  public static void encode(XdrDataOutputStream stream, DecoratedSignature encodedDecoratedSignature) throws IOException{
    SignatureHint.encode(stream, encodedDecoratedSignature.hint);
    Signature.encode(stream, encodedDecoratedSignature.signature);
  }
  public static DecoratedSignature decode(XdrDataInputStream stream) throws IOException {
    DecoratedSignature decodedDecoratedSignature = new DecoratedSignature();
    decodedDecoratedSignature.hint = SignatureHint.decode(stream);
    decodedDecoratedSignature.signature = Signature.decode(stream);
    return decodedDecoratedSignature;
  }
}
