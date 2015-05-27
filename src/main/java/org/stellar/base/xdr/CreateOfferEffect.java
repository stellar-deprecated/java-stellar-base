// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum CreateOfferEffect
//  {
//      CREATE_OFFER_CREATED = 0,
//      CREATE_OFFER_UPDATED = 1,
//      CREATE_OFFER_DELETED = 2
//  };

//  ===========================================================================
public enum CreateOfferEffect  {
  CREATE_OFFER_CREATED(0),
  CREATE_OFFER_UPDATED(1),
  CREATE_OFFER_DELETED(2),
  ;
  private int mValue;

  CreateOfferEffect(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static CreateOfferEffect decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return CREATE_OFFER_CREATED;
      case 1: return CREATE_OFFER_UPDATED;
      case 2: return CREATE_OFFER_DELETED;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, CreateOfferEffect value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
