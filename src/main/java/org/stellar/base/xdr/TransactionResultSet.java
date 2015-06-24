// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionResultSet
//  {
//      TransactionResultPair results<5000>;
//  };

//  ===========================================================================
public class TransactionResultSet  {
  public TransactionResultSet () {}
  private TransactionResultPair[] results;
  public TransactionResultPair[] getresults() {
    return this.results;
  }
  public void setresults(TransactionResultPair[] value) {
    this.results = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionResultSet encodedTransactionResultSet) throws IOException{
    int resultssize = encodedTransactionResultSet.getresults().length;
    stream.writeInt(resultssize);
    for (int i = 0; i < resultssize; i++) {
      TransactionResultPair.encode(stream, encodedTransactionResultSet.results[i]);
    }
  }
  public static TransactionResultSet decode(XdrDataInputStream stream) throws IOException {
    TransactionResultSet decodedTransactionResultSet = new TransactionResultSet();
    int resultssize = stream.readInt();
    decodedTransactionResultSet.results = new TransactionResultPair[resultssize];
    for (int i = 0; i < resultssize; i++) {
      decodedTransactionResultSet.results[i] = TransactionResultPair.decode(stream);
    }
    return decodedTransactionResultSet;
  }
}
