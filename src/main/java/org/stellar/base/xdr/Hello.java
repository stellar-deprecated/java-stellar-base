// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Hello
//  {
//      int protocolVersion;
//      string versionStr<100>;
//      int listeningPort;
//      opaque peerID[32];
//  };

//  ===========================================================================
public class Hello  {
  public Hello () {}
  private Integer protocolVersion;
  public Integer getprotocolVersion() {
    return this.protocolVersion;
  }
  public void setprotocolVersion(Integer value) {
    this.protocolVersion = value;
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
  private byte[] peerID;
  public byte[] getpeerID() {
    return this.peerID;
  }
  public void setpeerID(byte[] value) {
    this.peerID = value;
  }
  public static void encode(XdrDataOutputStream stream, Hello encodedHello) throws IOException{
    stream.writeInt(encodedHello.protocolVersion);
    stream.writeString(encodedHello.versionStr);
    stream.writeInt(encodedHello.listeningPort);
    int peerIDsize = encodedHello.peerID.length;
    stream.write(encodedHello.getpeerID(), 0, peerIDsize);
  }
  public static Hello decode(XdrDataInputStream stream) throws IOException {
    Hello decodedHello = new Hello();
    decodedHello.protocolVersion = stream.readInt();
    decodedHello.versionStr = stream.readString();
    decodedHello.listeningPort = stream.readInt();
    int peerIDsize = 32;
    decodedHello.peerID = new byte[peerIDsize];
    stream.read(decodedHello.peerID, 0, peerIDsize);
    return decodedHello;
  }
}
