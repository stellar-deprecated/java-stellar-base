// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Signer
//  {
//      uint256 pubKey;
//      uint32 weight; // really only need 1byte
//  };

//  ===========================================================================
public class Signer  {
  public Signer () {}
  private Uint256 pubKey;
  public Uint256 getpubKey() {
    return this.pubKey;
  }
  public void setpubKey(Uint256 value) {
    this.pubKey = value;
  }
  private Uint32 weight;
  public Uint32 getweight() {
    return this.weight;
  }
  public void setweight(Uint32 value) {
    this.weight = value;
  }
  public static void encode(XdrDataOutputStream stream, Signer encodedSigner) throws IOException{
    Uint256.encode(stream, encodedSigner.pubKey);
    Uint32.encode(stream, encodedSigner.weight);
  }
  public static Signer decode(XdrDataInputStream stream) throws IOException {
    Signer decodedSigner = new Signer();
    decodedSigner.pubKey = Uint256.decode(stream);
    decodedSigner.weight = Uint32.decode(stream);
    return decodedSigner;
  }
}
