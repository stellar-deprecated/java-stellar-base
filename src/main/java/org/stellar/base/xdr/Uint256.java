// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  typedef opaque uint256[32];

//  ===========================================================================
public class Uint256  {
  private byte[] uint256;
  public byte[] getuint256() {
    return this.uint256;
  }
  public void setuint256(byte[] value) {
    this.uint256 = value;
  }
  public static void encode(XdrDataOutputStream stream, Uint256  encodedUint256) throws IOException {
  int uint256size = encodedUint256.uint256.length;
  stream.write(encodedUint256.getuint256(), 0, uint256size);
  }
  public static Uint256 decode(XdrDataInputStream stream) throws IOException {
    Uint256 decodedUint256 = new Uint256();
  int uint256size = 32;
  decodedUint256.uint256 = new byte[uint256size];
  stream.read(decodedUint256.uint256, 0, uint256size);
    return decodedUint256;
  }
}
