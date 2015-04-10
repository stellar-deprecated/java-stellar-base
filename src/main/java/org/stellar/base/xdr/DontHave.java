// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct DontHave
//  {
//      MessageType type;
//      uint256 reqHash;
//  };

//  ===========================================================================
public class DontHave  {
  public DontHave () {}
  private MessageType type;
  public MessageType gettype() {
    return this.type;
  }
  public void settype(MessageType value) {
    this.type = value;
  }
  private Uint256 reqHash;
  public Uint256 getreqHash() {
    return this.reqHash;
  }
  public void setreqHash(Uint256 value) {
    this.reqHash = value;
  }
  public static void encode(XdrDataOutputStream stream, DontHave encodedDontHave) throws IOException{
    MessageType.encode(stream, encodedDontHave.type);
    Uint256.encode(stream, encodedDontHave.reqHash);
  }
  public static DontHave decode(XdrDataInputStream stream) throws IOException {
    DontHave decodedDontHave = new DontHave();
    decodedDontHave.type = MessageType.decode(stream);
    decodedDontHave.reqHash = Uint256.decode(stream);
    return decodedDontHave;
  }
}
