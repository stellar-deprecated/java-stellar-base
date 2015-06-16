// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct DecoratedSignature
//  {
//      opaque hint[4];    // first 4 bytes of the public key, used as a hint
//      uint512 signature; // actual signature
//  };

//  ===========================================================================
public class DecoratedSignature  {
  public DecoratedSignature () {}
  private byte[] hint;
  public byte[] gethint() {
    return this.hint;
  }
  public void sethint(byte[] value) {
    this.hint = value;
  }
  private Uint512 signature;
  public Uint512 getsignature() {
    return this.signature;
  }
  public void setsignature(Uint512 value) {
    this.signature = value;
  }
  public static void encode(XdrDataOutputStream stream, DecoratedSignature encodedDecoratedSignature) throws IOException{
    int hintsize = encodedDecoratedSignature.hint.length;
    stream.write(encodedDecoratedSignature.gethint(), 0, hintsize);
    Uint512.encode(stream, encodedDecoratedSignature.signature);
  }
  public static DecoratedSignature decode(XdrDataInputStream stream) throws IOException {
    DecoratedSignature decodedDecoratedSignature = new DecoratedSignature();
    int hintsize = 4;
    decodedDecoratedSignature.hint = new byte[hintsize];
    stream.read(decodedDecoratedSignature.hint, 0, hintsize);
    decodedDecoratedSignature.signature = Uint512.decode(stream);
    return decodedDecoratedSignature;
  }
}
