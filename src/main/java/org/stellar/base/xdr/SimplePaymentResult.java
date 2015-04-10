// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct SimplePaymentResult
//  {
//      AccountID destination;
//      Currency currency;
//      int64 amount;
//  };

//  ===========================================================================
public class SimplePaymentResult  {
  public SimplePaymentResult () {}
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
  public static void encode(XdrDataOutputStream stream, SimplePaymentResult encodedSimplePaymentResult) throws IOException{
    AccountID.encode(stream, encodedSimplePaymentResult.destination);
    Currency.encode(stream, encodedSimplePaymentResult.currency);
    Int64.encode(stream, encodedSimplePaymentResult.amount);
  }
  public static SimplePaymentResult decode(XdrDataInputStream stream) throws IOException {
    SimplePaymentResult decodedSimplePaymentResult = new SimplePaymentResult();
    decodedSimplePaymentResult.destination = AccountID.decode(stream);
    decodedSimplePaymentResult.currency = Currency.decode(stream);
    decodedSimplePaymentResult.amount = Int64.decode(stream);
    return decodedSimplePaymentResult;
  }
}
