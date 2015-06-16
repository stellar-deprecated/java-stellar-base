// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  typedef int int32;

//  ===========================================================================
public class Int32  {
  private Integer int32;
  public Integer getint32() {
    return this.int32;
  }
  public void setint32(Integer value) {
    this.int32 = value;
  }
  public static void encode(XdrDataOutputStream stream, Int32  encodedInt32) throws IOException {
  stream.writeInt(encodedInt32.int32);
  }
  public static Int32 decode(XdrDataInputStream stream) throws IOException {
    Int32 decodedInt32 = new Int32();
  decodedInt32.int32 = stream.readInt();
    return decodedInt32;
  }
}
