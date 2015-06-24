// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  typedef opaque NodeID[32];

//  ===========================================================================
public class NodeID  {
  private byte[] NodeID;
  public byte[] getNodeID() {
    return this.NodeID;
  }
  public void setNodeID(byte[] value) {
    this.NodeID = value;
  }
  public static void encode(XdrDataOutputStream stream, NodeID  encodedNodeID) throws IOException {
  int NodeIDsize = encodedNodeID.NodeID.length;
  stream.write(encodedNodeID.getNodeID(), 0, NodeIDsize);
  }
  public static NodeID decode(XdrDataInputStream stream) throws IOException {
    NodeID decodedNodeID = new NodeID();
  int NodeIDsize = 32;
  decodedNodeID.NodeID = new byte[NodeIDsize];
  stream.read(decodedNodeID.NodeID, 0, NodeIDsize);
    return decodedNodeID;
  }
}
