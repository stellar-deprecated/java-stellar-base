// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct PeerAddress
//  {
//      opaque ip[4];
//      uint32 port;
//      uint32 numFailures;
//  };

//  ===========================================================================
public class PeerAddress  {
  public PeerAddress () {}
  private byte[] ip;
  public byte[] getip() {
    return this.ip;
  }
  public void setip(byte[] value) {
    this.ip = value;
  }
  private Uint32 port;
  public Uint32 getport() {
    return this.port;
  }
  public void setport(Uint32 value) {
    this.port = value;
  }
  private Uint32 numFailures;
  public Uint32 getnumFailures() {
    return this.numFailures;
  }
  public void setnumFailures(Uint32 value) {
    this.numFailures = value;
  }
  public static void encode(XdrDataOutputStream stream, PeerAddress encodedPeerAddress) throws IOException{
    int ipsize = encodedPeerAddress.ip.length;
    stream.write(encodedPeerAddress.getip(), 0, ipsize);
    Uint32.encode(stream, encodedPeerAddress.port);
    Uint32.encode(stream, encodedPeerAddress.numFailures);
  }
  public static PeerAddress decode(XdrDataInputStream stream) throws IOException {
    PeerAddress decodedPeerAddress = new PeerAddress();
    int ipsize = 4;
    decodedPeerAddress.ip = new byte[ipsize];
    stream.read(decodedPeerAddress.ip, 0, ipsize);
    decodedPeerAddress.port = Uint32.decode(stream);
    decodedPeerAddress.numFailures = Uint32.decode(stream);
    return decodedPeerAddress;
  }
}
