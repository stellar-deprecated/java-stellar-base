// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Hello
//  {
//      uint32 ledgerVersion;
//      uint32 overlayVersion;
//      Hash networkID;
//      string versionStr<100>;
//      int listeningPort;
//      NodeID peerID;
//      AuthCert cert;
//      uint256 nonce;
//  };

//  ===========================================================================
public class Hello  {
  public Hello () {}
  private Uint32 ledgerVersion;
  public Uint32 getledgerVersion() {
    return this.ledgerVersion;
  }
  public void setledgerVersion(Uint32 value) {
    this.ledgerVersion = value;
  }
  private Uint32 overlayVersion;
  public Uint32 getoverlayVersion() {
    return this.overlayVersion;
  }
  public void setoverlayVersion(Uint32 value) {
    this.overlayVersion = value;
  }
  private Hash networkID;
  public Hash getnetworkID() {
    return this.networkID;
  }
  public void setnetworkID(Hash value) {
    this.networkID = value;
  }
  private String versionStr;
  public String getversionStr() {
    return this.versionStr;
  }
  public void setversionStr(String value) {
    this.versionStr = value;
  }
  private Integer listeningPort;
  public Integer getlisteningPort() {
    return this.listeningPort;
  }
  public void setlisteningPort(Integer value) {
    this.listeningPort = value;
  }
  private NodeID peerID;
  public NodeID getpeerID() {
    return this.peerID;
  }
  public void setpeerID(NodeID value) {
    this.peerID = value;
  }
  private AuthCert cert;
  public AuthCert getcert() {
    return this.cert;
  }
  public void setcert(AuthCert value) {
    this.cert = value;
  }
  private Uint256 nonce;
  public Uint256 getnonce() {
    return this.nonce;
  }
  public void setnonce(Uint256 value) {
    this.nonce = value;
  }
  public static void encode(XdrDataOutputStream stream, Hello encodedHello) throws IOException{
    Uint32.encode(stream, encodedHello.ledgerVersion);
    Uint32.encode(stream, encodedHello.overlayVersion);
    Hash.encode(stream, encodedHello.networkID);
    stream.writeString(encodedHello.versionStr);
    stream.writeInt(encodedHello.listeningPort);
    NodeID.encode(stream, encodedHello.peerID);
    AuthCert.encode(stream, encodedHello.cert);
    Uint256.encode(stream, encodedHello.nonce);
  }
  public static Hello decode(XdrDataInputStream stream) throws IOException {
    Hello decodedHello = new Hello();
    decodedHello.ledgerVersion = Uint32.decode(stream);
    decodedHello.overlayVersion = Uint32.decode(stream);
    decodedHello.networkID = Hash.decode(stream);
    decodedHello.versionStr = stream.readString();
    decodedHello.listeningPort = stream.readInt();
    decodedHello.peerID = NodeID.decode(stream);
    decodedHello.cert = AuthCert.decode(stream);
    decodedHello.nonce = Uint256.decode(stream);
    return decodedHello;
  }
}
