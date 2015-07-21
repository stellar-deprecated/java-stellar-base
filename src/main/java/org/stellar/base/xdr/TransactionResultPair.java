// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionResultPair
//  {
//      Hash transactionHash;
//      TransactionResult result; // result for the transaction
//  };

//  ===========================================================================
public class TransactionResultPair  {
  public TransactionResultPair () {}
  private Hash transactionHash;
  public Hash gettransactionHash() {
    return this.transactionHash;
  }
  public void settransactionHash(Hash value) {
    this.transactionHash = value;
  }
  private TransactionResult result;
  public TransactionResult getresult() {
    return this.result;
  }
  public void setresult(TransactionResult value) {
    this.result = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionResultPair encodedTransactionResultPair) throws IOException{
    Hash.encode(stream, encodedTransactionResultPair.transactionHash);
    TransactionResult.encode(stream, encodedTransactionResultPair.result);
  }
  public static TransactionResultPair decode(XdrDataInputStream stream) throws IOException {
    TransactionResultPair decodedTransactionResultPair = new TransactionResultPair();
    decodedTransactionResultPair.transactionHash = Hash.decode(stream);
    decodedTransactionResultPair.result = TransactionResult.decode(stream);
    return decodedTransactionResultPair;
  }
}
