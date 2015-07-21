// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  enum AccountMergeResultCode
//  {
//      // codes considered as "success" for the operation
//      ACCOUNT_MERGE_SUCCESS = 0,
//      // codes considered as "failure" for the operation
//      ACCOUNT_MERGE_MALFORMED = -1,  // can't merge onto itself
//      ACCOUNT_MERGE_NO_ACCOUNT = -2, // destination does not exist
//      ACCOUNT_MERGE_HAS_CREDIT = -3, // account has active trust lines
//      ACCOUNT_MERGE_CREDIT_HELD = -4 // an issuer cannot be merged if used
//  };

//  ===========================================================================
public enum AccountMergeResultCode  {
  ACCOUNT_MERGE_SUCCESS(0),
  ACCOUNT_MERGE_MALFORMED(-1),
  ACCOUNT_MERGE_NO_ACCOUNT(-2),
  ACCOUNT_MERGE_HAS_CREDIT(-3),
  ACCOUNT_MERGE_CREDIT_HELD(-4),
  ;
  private int mValue;

  AccountMergeResultCode(int value) {
      mValue = value;
  }

  public int getValue() {
      return mValue;
  }

  static AccountMergeResultCode decode(XdrDataInputStream stream) throws IOException {
    int value = stream.readInt();
    switch (value) {
      case 0: return ACCOUNT_MERGE_SUCCESS;
      case -1: return ACCOUNT_MERGE_MALFORMED;
      case -2: return ACCOUNT_MERGE_NO_ACCOUNT;
      case -3: return ACCOUNT_MERGE_HAS_CREDIT;
      case -4: return ACCOUNT_MERGE_CREDIT_HELD;
      default:
        throw new RuntimeException("Unknown enum value: " + value);
    }
  }

  static void encode(XdrDataOutputStream stream, AccountMergeResultCode value) throws IOException {
    stream.writeInt(value.getValue());
  }
}
