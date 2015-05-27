// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum CreateOfferResultCode
//  {
//      // codes considered as "success" for the operation
//      CREATE_OFFER_SUCCESS = 0,
//  
//      // codes considered as "failure" for the operation
//      CREATE_OFFER_MALFORMED = -1,      // generated offer would be invalid
//      CREATE_OFFER_NO_TRUST = -2,       // can't hold what it's buying
//      CREATE_OFFER_NOT_AUTHORIZED = -3, // not authorized to sell or buy
//      CREATE_OFFER_LINE_FULL = -4,      // can't receive more of what it's buying
//      CREATE_OFFER_UNDERFUNDED = -5,    // doesn't hold what it's trying to sell
//      CREATE_OFFER_CROSS_SELF = -6,     // would cross an offer from the same user
//  
//      // update errors
//      CREATE_OFFER_NOT_FOUND = -7, // offerID does not match an existing offer
//      CREATE_OFFER_MISMATCH = -8,  // currencies don't match offer
//  
//      CREATE_OFFER_LOW_RESERVE = -9 // not enough funds to create a new Offer
//  };

//  ===========================================================================
public enum CreateOfferResultCode  {
  CREATE_OFFER_SUCCESS(0),
  CREATE_OFFER_MALFORMED(-1),
  CREATE_OFFER_NO_TRUST(-2),
  CREATE_OFFER_NOT_AUTHORIZED(-3),
  CREATE_OFFER_LINE_FULL(-4),
  CREATE_OFFER_UNDERFUNDED(-5),
  CREATE_OFFER_CROSS_SELF(-6),
  CREATE_OFFER_NOT_FOUND(-7),
  CREATE_OFFER_MISMATCH(-8),
  CREATE_OFFER_LOW_RESERVE(-9),
  ;
  private int mValue;

  CreateOfferResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static CreateOfferResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return CREATE_OFFER_SUCCESS;
      case -1: return CREATE_OFFER_MALFORMED;
      case -2: return CREATE_OFFER_NO_TRUST;
      case -3: return CREATE_OFFER_NOT_AUTHORIZED;
      case -4: return CREATE_OFFER_LINE_FULL;
      case -5: return CREATE_OFFER_UNDERFUNDED;
      case -6: return CREATE_OFFER_CROSS_SELF;
      case -7: return CREATE_OFFER_NOT_FOUND;
      case -8: return CREATE_OFFER_MISMATCH;
      case -9: return CREATE_OFFER_LOW_RESERVE;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, CreateOfferResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
