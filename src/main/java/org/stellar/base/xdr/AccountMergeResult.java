// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union AccountMergeResult switch (AccountMergeResultCode code)
//  {
//  case ACCOUNT_MERGE_SUCCESS:
//      void;
//  default:
//      void;
//  };

//  ===========================================================================
public class AccountMergeResult  {
  public AccountMergeResult () {}
  AccountMergeResultCode code;
  public AccountMergeResultCode getDiscriminant() {
    return this.code;
  }
  public void setDiscriminant(AccountMergeResultCode value) {
    this.code = value;
  }
  public static void encode(XdrDataOutputStream stream, AccountMergeResult encodedAccountMergeResult) throws IOException {
    stream.writeInt(encodedAccountMergeResult.getDiscriminant().getValue());
    switch (encodedAccountMergeResult.getDiscriminant()) {
  case ACCOUNT_MERGE_SUCCESS:
  break;
  default:
  break;
  }
  }
  public static AccountMergeResult decode(XdrDataInputStream stream) throws IOException {
    AccountMergeResult decodedAccountMergeResult = new AccountMergeResult();
    switch (decodedAccountMergeResult.getDiscriminant()) {
  case ACCOUNT_MERGE_SUCCESS:
  break;
  default:
  break;
  }
    return decodedAccountMergeResult;
  }
}
