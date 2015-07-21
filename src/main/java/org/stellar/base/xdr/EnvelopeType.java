// Automatically generated on 2015-07-21T12:54:49-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum EnvelopeType
//  {
//      ENVELOPE_TYPE_SCP = 1,
//      ENVELOPE_TYPE_TX = 2
//  };

//  ===========================================================================
public enum EnvelopeType  {
  ENVELOPE_TYPE_SCP(1),
  ENVELOPE_TYPE_TX(2),
  ;
  private int mValue;

  EnvelopeType(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static EnvelopeType decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 1: return ENVELOPE_TYPE_SCP;
      case 2: return ENVELOPE_TYPE_TX;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, EnvelopeType value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
