// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionMeta
//  {
//      LedgerEntryChange changes<>;
//  };

//  ===========================================================================
public class TransactionMeta  {
  public TransactionMeta () {}
  private LedgerEntryChange[] changes;
  public LedgerEntryChange[] getchanges() {
    return this.changes;
  }
  public void setchanges(LedgerEntryChange[] value) {
    this.changes = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionMeta encodedTransactionMeta) throws IOException{
    int changessize = encodedTransactionMeta.getchanges().length;
    stream.writeInt(changessize);
    for (int i = 0; i < changessize; i++) {
      LedgerEntryChange.encode(stream, encodedTransactionMeta.changes[i]);
    }
  }
  public static TransactionMeta decode(XdrDataInputStream stream) throws IOException {
    TransactionMeta decodedTransactionMeta = new TransactionMeta();
    int changessize = stream.readInt();
    decodedTransactionMeta.changes = new LedgerEntryChange[changessize];
    for (int i = 0; i < changessize; i++) {
      decodedTransactionMeta.changes[i] = LedgerEntryChange.decode(stream);
    }
    return decodedTransactionMeta;
  }
}
