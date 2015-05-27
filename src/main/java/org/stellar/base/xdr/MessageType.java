// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum MessageType
//  {
//      ERROR_MSG = 0,
//      HELLO = 1,
//      DONT_HAVE = 2,
//  
//      GET_PEERS = 3, // gets a list of peers this guy knows about
//      PEERS = 4,
//  
//      GET_TX_SET = 5, // gets a particular txset by hash
//      TX_SET = 6,
//  
//      TRANSACTION = 7, // pass on a tx you have heard about
//  
//      // SCP
//      GET_SCP_QUORUMSET = 8,
//      SCP_QUORUMSET = 9,
//      SCP_MESSAGE = 10
//  };

//  ===========================================================================
public enum MessageType  {
  ERROR_MSG(0),
  HELLO(1),
  DONT_HAVE(2),
  GET_PEERS(3),
  PEERS(4),
  GET_TX_SET(5),
  TX_SET(6),
  TRANSACTION(7),
  GET_SCP_QUORUMSET(8),
  SCP_QUORUMSET(9),
  SCP_MESSAGE(10),
  ;
  private int mValue;

  MessageType(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static MessageType decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return ERROR_MSG;
      case 1: return HELLO;
      case 2: return DONT_HAVE;
      case 3: return GET_PEERS;
      case 4: return PEERS;
      case 5: return GET_TX_SET;
      case 6: return TX_SET;
      case 7: return TRANSACTION;
      case 8: return GET_SCP_QUORUMSET;
      case 9: return SCP_QUORUMSET;
      case 10: return SCP_MESSAGE;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, MessageType value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
