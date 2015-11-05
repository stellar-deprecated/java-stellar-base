// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct PeerAddress
//  {
//      union switch (IPAddrType type)
//      {
//      case IPv4:
//          opaque ipv4[4];
//      case IPv6:
//          opaque ipv6[16];
//      } ip;
//      uint32 port;
//      uint32 numFailures;
//  };

//  ===========================================================================
public class PeerAddress  {
  public PeerAddress () {}
  private PeerAddressIp ip;
  public PeerAddressIp getip() {
    return this.ip;
  }
  public void setip(PeerAddressIp value) {
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
    PeerAddressIp.encode(stream, encodedPeerAddress.ip);
    Uint32.encode(stream, encodedPeerAddress.port);
    Uint32.encode(stream, encodedPeerAddress.numFailures);
  }
  public static PeerAddress decode(XdrDataInputStream stream) throws IOException {
    PeerAddress decodedPeerAddress = new PeerAddress();
    decodedPeerAddress.ip = PeerAddressIp.decode(stream);
    decodedPeerAddress.port = Uint32.decode(stream);
    decodedPeerAddress.numFailures = Uint32.decode(stream);
    return decodedPeerAddress;
  }

  public static class PeerAddressIp {
    public PeerAddressIp () {}
    IPAddrType type;
    public IPAddrType getDiscriminant() {
      return this.type;
    }
    public void setDiscriminant(IPAddrType value) {
      this.type = value;
    }
    private byte[] ipv4;
    public byte[] getipv4() {
      return this.ipv4;
    }
    public void setipv4(byte[] value) {
      this.ipv4 = value;
    }
    private byte[] ipv6;
    public byte[] getipv6() {
      return this.ipv6;
    }
    public void setipv6(byte[] value) {
      this.ipv6 = value;
    }
    public static void encode(XdrDataOutputStream stream, PeerAddressIp encodedPeerAddressIp) throws IOException {
    stream.writeInt(encodedPeerAddressIp.getDiscriminant().getValue());
    switch (encodedPeerAddressIp.getDiscriminant()) {
    case IPv4:
    int ipv4size = encodedPeerAddressIp.ipv4.length;
    stream.write(encodedPeerAddressIp.getipv4(), 0, ipv4size);
    break;
    case IPv6:
    int ipv6size = encodedPeerAddressIp.ipv6.length;
    stream.write(encodedPeerAddressIp.getipv6(), 0, ipv6size);
    break;
    }
    }
    public static PeerAddressIp decode(XdrDataInputStream stream) throws IOException {
      PeerAddressIp decodedPeerAddressIp = new PeerAddressIp();
      switch (decodedPeerAddressIp.getDiscriminant()) {
    case IPv4:
    int ipv4size = 4;
    decodedPeerAddressIp.ipv4 = new byte[ipv4size];
    stream.read(decodedPeerAddressIp.ipv4, 0, ipv4size);
    break;
    case IPv6:
    int ipv6size = 16;
    decodedPeerAddressIp.ipv6 = new byte[ipv6size];
    stream.read(decodedPeerAddressIp.ipv6, 0, ipv6size);
    break;
    }
      return decodedPeerAddressIp;
    }

  }
}
