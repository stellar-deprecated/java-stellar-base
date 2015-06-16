// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionHistoryEntry
//  {
//      uint32 ledgerSeq;
//      TransactionSet txSet;
//  };

//  ===========================================================================
public class TransactionHistoryEntry  {
  public TransactionHistoryEntry () {}
  private Uint32 ledgerSeq;
  public Uint32 getledgerSeq() {
    return this.ledgerSeq;
  }
  public void setledgerSeq(Uint32 value) {
    this.ledgerSeq = value;
  }
  private TransactionSet txSet;
  public TransactionSet gettxSet() {
    return this.txSet;
  }
  public void settxSet(TransactionSet value) {
    this.txSet = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionHistoryEntry encodedTransactionHistoryEntry) throws IOException{
    Uint32.encode(stream, encodedTransactionHistoryEntry.ledgerSeq);
    TransactionSet.encode(stream, encodedTransactionHistoryEntry.txSet);
  }
  public static TransactionHistoryEntry decode(XdrDataInputStream stream) throws IOException {
    TransactionHistoryEntry decodedTransactionHistoryEntry = new TransactionHistoryEntry();
    decodedTransactionHistoryEntry.ledgerSeq = Uint32.decode(stream);
    decodedTransactionHistoryEntry.txSet = TransactionSet.decode(stream);
    return decodedTransactionHistoryEntry;
  }
}
