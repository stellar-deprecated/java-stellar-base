// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct PaymentOp
//  {
//      AccountID destination; // recipient of the payment
//      Currency currency;     // what they end up with
//      int64 amount;          // amount they end up with
//  
//      opaque memo<32>;
//      opaque sourceMemo<32>; // used to return a payment
//  
//      // payment over path
//      Currency path<5>; // what hops it must go through to get there
//      int64 sendMax; // the maximum amount of the source currency (==path[0]) to
//                     // send (excluding fees).
//                     // The operation will fail if can't be met
//  };

//  ===========================================================================
public class PaymentOp  {
  public PaymentOp () {}
  private AccountID destination;
  public AccountID getdestination() {
    return this.destination;
  }
  public void setdestination(AccountID value) {
    this.destination = value;
  }
  private Currency currency;
  public Currency getcurrency() {
    return this.currency;
  }
  public void setcurrency(Currency value) {
    this.currency = value;
  }
  private Int64 amount;
  public Int64 getamount() {
    return this.amount;
  }
  public void setamount(Int64 value) {
    this.amount = value;
  }
  private byte[] memo;
  public byte[] getmemo() {
    return this.memo;
  }
  public void setmemo(byte[] value) {
    this.memo = value;
  }
  private byte[] sourceMemo;
  public byte[] getsourceMemo() {
    return this.sourceMemo;
  }
  public void setsourceMemo(byte[] value) {
    this.sourceMemo = value;
  }
  private Currency[] path;
  public Currency[] getpath() {
    return this.path;
  }
  public void setpath(Currency[] value) {
    this.path = value;
  }
  private Int64 sendMax;
  public Int64 getsendMax() {
    return this.sendMax;
  }
  public void setsendMax(Int64 value) {
    this.sendMax = value;
  }
  public static void encode(XdrDataOutputStream stream, PaymentOp encodedPaymentOp) throws IOException{
    AccountID.encode(stream, encodedPaymentOp.destination);
    Currency.encode(stream, encodedPaymentOp.currency);
    Int64.encode(stream, encodedPaymentOp.amount);
    int memosize = encodedPaymentOp.memo.length;
    stream.writeInt(memosize);
    stream.write(encodedPaymentOp.getmemo(), 0, memosize);
    int sourceMemosize = encodedPaymentOp.sourceMemo.length;
    stream.writeInt(sourceMemosize);
    stream.write(encodedPaymentOp.getsourceMemo(), 0, sourceMemosize);
    int pathsize = encodedPaymentOp.getpath().length;
    stream.writeInt(pathsize);
    for (int i = 0; i < pathsize; i++) {
      Currency.encode(stream, encodedPaymentOp.path[i]);
    }
    Int64.encode(stream, encodedPaymentOp.sendMax);
  }
  public static PaymentOp decode(XdrDataInputStream stream) throws IOException {
    PaymentOp decodedPaymentOp = new PaymentOp();
    decodedPaymentOp.destination = AccountID.decode(stream);
    decodedPaymentOp.currency = Currency.decode(stream);
    decodedPaymentOp.amount = Int64.decode(stream);
    int memosize = stream.readInt();
    decodedPaymentOp.memo = new byte[memosize];
    stream.read(decodedPaymentOp.memo, 0, memosize);
    int sourceMemosize = stream.readInt();
    decodedPaymentOp.sourceMemo = new byte[sourceMemosize];
    stream.read(decodedPaymentOp.sourceMemo, 0, sourceMemosize);
    int pathsize = stream.readInt();
    decodedPaymentOp.path = new Currency[pathsize];
    for (int i = 0; i < pathsize; i++) {
      decodedPaymentOp.path[i] = Currency.decode(stream);
    }
    decodedPaymentOp.sendMax = Int64.decode(stream);
    return decodedPaymentOp;
  }
}
