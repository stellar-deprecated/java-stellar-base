// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  typedef opaque uint512[64];

//  ===========================================================================
public class Uint512  {
  private byte[] uint512;
  public byte[] getuint512() {
    return this.uint512;
  }
  public void setuint512(byte[] value) {
    this.uint512 = value;
  }
  public static void encode(XdrDataOutputStream stream, Uint512  encodedUint512) throws IOException {
  int uint512size = encodedUint512.uint512.length;
  stream.write(encodedUint512.getuint512(), 0, uint512size);
  }
  public static Uint512 decode(XdrDataInputStream stream) throws IOException {
    Uint512 decodedUint512 = new Uint512();
  int uint512size = 64;
  decodedUint512.uint512 = new byte[uint512size];
  stream.read(decodedUint512.uint512, 0, uint512size);
    return decodedUint512;
  }
}
