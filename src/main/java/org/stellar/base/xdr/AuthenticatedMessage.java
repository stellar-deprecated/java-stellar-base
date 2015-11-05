// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct AuthenticatedMessage
//  {
//     uint64 sequence;
//     StellarMessage message;
//     HmacSha256Mac mac;
//  };

//  ===========================================================================
public class AuthenticatedMessage  {
  public AuthenticatedMessage () {}
  private Uint64 sequence;
  public Uint64 getsequence() {
    return this.sequence;
  }
  public void setsequence(Uint64 value) {
    this.sequence = value;
  }
  private StellarMessage message;
  public StellarMessage getmessage() {
    return this.message;
  }
  public void setmessage(StellarMessage value) {
    this.message = value;
  }
  private HmacSha256Mac mac;
  public HmacSha256Mac getmac() {
    return this.mac;
  }
  public void setmac(HmacSha256Mac value) {
    this.mac = value;
  }
  public static void encode(XdrDataOutputStream stream, AuthenticatedMessage encodedAuthenticatedMessage) throws IOException{
    Uint64.encode(stream, encodedAuthenticatedMessage.sequence);
    StellarMessage.encode(stream, encodedAuthenticatedMessage.message);
    HmacSha256Mac.encode(stream, encodedAuthenticatedMessage.mac);
  }
  public static AuthenticatedMessage decode(XdrDataInputStream stream) throws IOException {
    AuthenticatedMessage decodedAuthenticatedMessage = new AuthenticatedMessage();
    decodedAuthenticatedMessage.sequence = Uint64.decode(stream);
    decodedAuthenticatedMessage.message = StellarMessage.decode(stream);
    decodedAuthenticatedMessage.mac = HmacSha256Mac.decode(stream);
    return decodedAuthenticatedMessage;
  }
}
