// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionSet
//  {
//      Hash previousLedgerHash;
//      TransactionEnvelope txs<5000>;
//  };

//  ===========================================================================
public class TransactionSet  {
  public TransactionSet () {}
  private Hash previousLedgerHash;
  public Hash getpreviousLedgerHash() {
    return this.previousLedgerHash;
  }
  public void setpreviousLedgerHash(Hash value) {
    this.previousLedgerHash = value;
  }
  private TransactionEnvelope[] txs;
  public TransactionEnvelope[] gettxs() {
    return this.txs;
  }
  public void settxs(TransactionEnvelope[] value) {
    this.txs = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionSet encodedTransactionSet) throws IOException{
    Hash.encode(stream, encodedTransactionSet.previousLedgerHash);
    int txssize = encodedTransactionSet.gettxs().length;
    stream.writeInt(txssize);
    for (int i = 0; i < txssize; i++) {
      TransactionEnvelope.encode(stream, encodedTransactionSet.txs[i]);
    }
  }
  public static TransactionSet decode(XdrDataInputStream stream) throws IOException {
    TransactionSet decodedTransactionSet = new TransactionSet();
    decodedTransactionSet.previousLedgerHash = Hash.decode(stream);
    int txssize = stream.readInt();
    decodedTransactionSet.txs = new TransactionEnvelope[txssize];
    for (int i = 0; i < txssize; i++) {
      decodedTransactionSet.txs[i] = TransactionEnvelope.decode(stream);
    }
    return decodedTransactionSet;
  }
}
