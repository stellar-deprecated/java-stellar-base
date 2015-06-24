// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct PaymentOp
//  {
//      AccountID destination; // recipient of the payment
//      Currency currency;     // what they end up with
//      int64 amount;          // amount they end up with
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
  public static void encode(XdrDataOutputStream stream, PaymentOp encodedPaymentOp) throws IOException{
    AccountID.encode(stream, encodedPaymentOp.destination);
    Currency.encode(stream, encodedPaymentOp.currency);
    Int64.encode(stream, encodedPaymentOp.amount);
  }
  public static PaymentOp decode(XdrDataInputStream stream) throws IOException {
    PaymentOp decodedPaymentOp = new PaymentOp();
    decodedPaymentOp.destination = AccountID.decode(stream);
    decodedPaymentOp.currency = Currency.decode(stream);
    decodedPaymentOp.amount = Int64.decode(stream);
    return decodedPaymentOp;
  }
}
