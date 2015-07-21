// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct CreateAccountOp
//  {
//      AccountID destination; // account to create
//      int64 startingBalance; // amount they end up with
//  };

//  ===========================================================================
public class CreateAccountOp  {
  public CreateAccountOp () {}
  private AccountID destination;
  public AccountID getdestination() {
    return this.destination;
  }
  public void setdestination(AccountID value) {
    this.destination = value;
  }
  private Int64 startingBalance;
  public Int64 getstartingBalance() {
    return this.startingBalance;
  }
  public void setstartingBalance(Int64 value) {
    this.startingBalance = value;
  }
  public static void encode(XdrDataOutputStream stream, CreateAccountOp encodedCreateAccountOp) throws IOException{
    AccountID.encode(stream, encodedCreateAccountOp.destination);
    Int64.encode(stream, encodedCreateAccountOp.startingBalance);
  }
  public static CreateAccountOp decode(XdrDataInputStream stream) throws IOException {
    CreateAccountOp decodedCreateAccountOp = new CreateAccountOp();
    decodedCreateAccountOp.destination = AccountID.decode(stream);
    decodedCreateAccountOp.startingBalance = Int64.decode(stream);
    return decodedCreateAccountOp;
  }
}
