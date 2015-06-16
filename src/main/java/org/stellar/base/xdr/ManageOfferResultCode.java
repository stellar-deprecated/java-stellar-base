// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum ManageOfferResultCode
//  {
//      // codes considered as "success" for the operation
//      MANAGE_OFFER_SUCCESS = 0,
//  
//      // codes considered as "failure" for the operation
//      MANAGE_OFFER_MALFORMED = -1,      // generated offer would be invalid
//      MANAGE_OFFER_NO_TRUST = -2,       // can't hold what it's buying
//      MANAGE_OFFER_NOT_AUTHORIZED = -3, // not authorized to sell or buy
//      MANAGE_OFFER_LINE_FULL = -4,      // can't receive more of what it's buying
//      MANAGE_OFFER_UNDERFUNDED = -5,    // doesn't hold what it's trying to sell
//      MANAGE_OFFER_CROSS_SELF = -6,     // would cross an offer from the same user
//  
//      // update errors
//      MANAGE_OFFER_NOT_FOUND = -7, // offerID does not match an existing offer
//      MANAGE_OFFER_MISMATCH = -8,  // currencies don't match offer
//  
//      MANAGE_OFFER_LOW_RESERVE = -9 // not enough funds to create a new Offer
//  };

//  ===========================================================================
public enum ManageOfferResultCode  {
  MANAGE_OFFER_SUCCESS(0),
  MANAGE_OFFER_MALFORMED(-1),
  MANAGE_OFFER_NO_TRUST(-2),
  MANAGE_OFFER_NOT_AUTHORIZED(-3),
  MANAGE_OFFER_LINE_FULL(-4),
  MANAGE_OFFER_UNDERFUNDED(-5),
  MANAGE_OFFER_CROSS_SELF(-6),
  MANAGE_OFFER_NOT_FOUND(-7),
  MANAGE_OFFER_MISMATCH(-8),
  MANAGE_OFFER_LOW_RESERVE(-9),
  ;
  private int mValue;

  ManageOfferResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static ManageOfferResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return MANAGE_OFFER_SUCCESS;
      case -1: return MANAGE_OFFER_MALFORMED;
      case -2: return MANAGE_OFFER_NO_TRUST;
      case -3: return MANAGE_OFFER_NOT_AUTHORIZED;
      case -4: return MANAGE_OFFER_LINE_FULL;
      case -5: return MANAGE_OFFER_UNDERFUNDED;
      case -6: return MANAGE_OFFER_CROSS_SELF;
      case -7: return MANAGE_OFFER_NOT_FOUND;
      case -8: return MANAGE_OFFER_MISMATCH;
      case -9: return MANAGE_OFFER_LOW_RESERVE;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, ManageOfferResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
