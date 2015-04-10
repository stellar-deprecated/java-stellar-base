// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionHistoryResultEntry
//  {
//      uint32 ledgerSeq;
//      TransactionResultSet txResultSet;
//  };

//  ===========================================================================
public class TransactionHistoryResultEntry  {
  public TransactionHistoryResultEntry () {}
  private Uint32 ledgerSeq;
  public Uint32 getledgerSeq() {
    return this.ledgerSeq;
  }
  public void setledgerSeq(Uint32 value) {
    this.ledgerSeq = value;
  }
  private TransactionResultSet txResultSet;
  public TransactionResultSet gettxResultSet() {
    return this.txResultSet;
  }
  public void settxResultSet(TransactionResultSet value) {
    this.txResultSet = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionHistoryResultEntry encodedTransactionHistoryResultEntry) throws IOException{
    Uint32.encode(stream, encodedTransactionHistoryResultEntry.ledgerSeq);
    TransactionResultSet.encode(stream, encodedTransactionHistoryResultEntry.txResultSet);
  }
  public static TransactionHistoryResultEntry decode(XdrDataInputStream stream) throws IOException {
    TransactionHistoryResultEntry decodedTransactionHistoryResultEntry = new TransactionHistoryResultEntry();
    decodedTransactionHistoryResultEntry.ledgerSeq = Uint32.decode(stream);
    decodedTransactionHistoryResultEntry.txResultSet = TransactionResultSet.decode(stream);
    return decodedTransactionHistoryResultEntry;
  }
}
