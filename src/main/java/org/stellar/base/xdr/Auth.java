// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Auth
//  {
//      // Empty message, just to confirm
//      // establishment of MAC keys.
//      int unused;
//  };

//  ===========================================================================
public class Auth  {
  public Auth () {}
  private Integer unused;
  public Integer getunused() {
    return this.unused;
  }
  public void setunused(Integer value) {
    this.unused = value;
  }
  public static void encode(XdrDataOutputStream stream, Auth encodedAuth) throws IOException{
    stream.writeInt(encodedAuth.unused);
  }
  public static Auth decode(XdrDataInputStream stream) throws IOException {
    Auth decodedAuth = new Auth();
    decodedAuth.unused = stream.readInt();
    return decodedAuth;
  }
}
