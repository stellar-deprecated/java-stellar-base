// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  typedef opaque AccountID[32];

//  ===========================================================================
public class AccountID  {
  private byte[] AccountID;
  public byte[] getAccountID() {
    return this.AccountID;
  }
  public void setAccountID(byte[] value) {
    this.AccountID = value;
  }
  public static void encode(XdrDataOutputStream stream, AccountID  encodedAccountID) throws IOException {
  int AccountIDsize = encodedAccountID.AccountID.length;
  stream.write(encodedAccountID.getAccountID(), 0, AccountIDsize);
  }
  public static AccountID decode(XdrDataInputStream stream) throws IOException {
    AccountID decodedAccountID = new AccountID();
  int AccountIDsize = 32;
  decodedAccountID.AccountID = new byte[AccountIDsize];
  stream.read(decodedAccountID.AccountID, 0, AccountIDsize);
    return decodedAccountID;
  }
}
