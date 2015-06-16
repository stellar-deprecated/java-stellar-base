// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct inflationPayout // or use PaymentResultAtom to limit types?
//  {
//      AccountID destination;
//      int64 amount;
//  };

//  ===========================================================================
public class InflationPayout  {
  public InflationPayout () {}
  private AccountID destination;
  public AccountID getdestination() {
    return this.destination;
  }
  public void setdestination(AccountID value) {
    this.destination = value;
  }
  private Int64 amount;
  public Int64 getamount() {
    return this.amount;
  }
  public void setamount(Int64 value) {
    this.amount = value;
  }
  public static void encode(XdrDataOutputStream stream, InflationPayout encodedInflationPayout) throws IOException{
    AccountID.encode(stream, encodedInflationPayout.destination);
    Int64.encode(stream, encodedInflationPayout.amount);
  }
  public static InflationPayout decode(XdrDataInputStream stream) throws IOException {
    InflationPayout decodedInflationPayout = new InflationPayout();
    decodedInflationPayout.destination = AccountID.decode(stream);
    decodedInflationPayout.amount = Int64.decode(stream);
    return decodedInflationPayout;
  }
}
