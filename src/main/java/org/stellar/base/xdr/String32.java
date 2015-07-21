// Automatically generated on 2015-07-21T12:54:49-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  typedef string string32<32>;

//  ===========================================================================
public class String32  {
  private String string32;
  public String getstring32() {
    return this.string32;
  }
  public void setstring32(String value) {
    this.string32 = value;
  }
  public static void encode(XdrDataOutputStream stream, String32  encodedString32) throws IOException {
  stream.writeString(encodedString32.string32);
  }
  public static String32 decode(XdrDataInputStream stream) throws IOException {
    String32 decodedString32 = new String32();
  decodedString32.string32 = stream.readString();
    return decodedString32;
  }
}
