// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct PathPaymentOp
//  {
//      Currency sendCurrency; // currency we pay with
//      int64 sendMax;         // the maximum amount of sendCurrency to
//                             // send (excluding fees).
//                             // The operation will fail if can't be met
//  
//      AccountID destination; // recipient of the payment
//      Currency destCurrency; // what they end up with
//      int64 destAmount;      // amount they end up with
//  
//      Currency path<5>; // additional hops it must go through to get there
//  };

//  ===========================================================================
public class PathPaymentOp  {
  public PathPaymentOp () {}
  private Currency sendCurrency;
  public Currency getsendCurrency() {
    return this.sendCurrency;
  }
  public void setsendCurrency(Currency value) {
    this.sendCurrency = value;
  }
  private Int64 sendMax;
  public Int64 getsendMax() {
    return this.sendMax;
  }
  public void setsendMax(Int64 value) {
    this.sendMax = value;
  }
  private AccountID destination;
  public AccountID getdestination() {
    return this.destination;
  }
  public void setdestination(AccountID value) {
    this.destination = value;
  }
  private Currency destCurrency;
  public Currency getdestCurrency() {
    return this.destCurrency;
  }
  public void setdestCurrency(Currency value) {
    this.destCurrency = value;
  }
  private Int64 destAmount;
  public Int64 getdestAmount() {
    return this.destAmount;
  }
  public void setdestAmount(Int64 value) {
    this.destAmount = value;
  }
  private Currency[] path;
  public Currency[] getpath() {
    return this.path;
  }
  public void setpath(Currency[] value) {
    this.path = value;
  }
  public static void encode(XdrDataOutputStream stream, PathPaymentOp encodedPathPaymentOp) throws IOException{
    Currency.encode(stream, encodedPathPaymentOp.sendCurrency);
    Int64.encode(stream, encodedPathPaymentOp.sendMax);
    AccountID.encode(stream, encodedPathPaymentOp.destination);
    Currency.encode(stream, encodedPathPaymentOp.destCurrency);
    Int64.encode(stream, encodedPathPaymentOp.destAmount);
    int pathsize = encodedPathPaymentOp.getpath().length;
    stream.writeInt(pathsize);
    for (int i = 0; i < pathsize; i++) {
      Currency.encode(stream, encodedPathPaymentOp.path[i]);
    }
  }
  public static PathPaymentOp decode(XdrDataInputStream stream) throws IOException {
    PathPaymentOp decodedPathPaymentOp = new PathPaymentOp();
    decodedPathPaymentOp.sendCurrency = Currency.decode(stream);
    decodedPathPaymentOp.sendMax = Int64.decode(stream);
    decodedPathPaymentOp.destination = AccountID.decode(stream);
    decodedPathPaymentOp.destCurrency = Currency.decode(stream);
    decodedPathPaymentOp.destAmount = Int64.decode(stream);
    int pathsize = stream.readInt();
    decodedPathPaymentOp.path = new Currency[pathsize];
    for (int i = 0; i < pathsize; i++) {
      decodedPathPaymentOp.path[i] = Currency.decode(stream);
    }
    return decodedPathPaymentOp;
  }
}
