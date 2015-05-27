// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Price
//  {
//      int32 n; // numerator
//      int32 d; // denominator
//  };

//  ===========================================================================
public class Price  {
  public Price () {}
  private Int32 n;
  public Int32 getn() {
    return this.n;
  }
  public void setn(Int32 value) {
    this.n = value;
  }
  private Int32 d;
  public Int32 getd() {
    return this.d;
  }
  public void setd(Int32 value) {
    this.d = value;
  }
  public static void encode(XdrDataOutputStream stream, Price encodedPrice) throws IOException{
    Int32.encode(stream, encodedPrice.n);
    Int32.encode(stream, encodedPrice.d);
  }
  public static Price decode(XdrDataInputStream stream) throws IOException {
    Price decodedPrice = new Price();
    decodedPrice.n = Int32.decode(stream);
    decodedPrice.d = Int32.decode(stream);
    return decodedPrice;
  }
}
