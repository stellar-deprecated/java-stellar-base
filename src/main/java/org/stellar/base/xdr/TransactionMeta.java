// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionMeta
//  {
//      BucketEntry entries<>;
//  };

//  ===========================================================================
public class TransactionMeta  {
  public TransactionMeta () {}
  private BucketEntry[] entries;
  public BucketEntry[] getentries() {
    return this.entries;
  }
  public void setentries(BucketEntry[] value) {
    this.entries = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionMeta encodedTransactionMeta) throws IOException{
    int entriessize = encodedTransactionMeta.getentries().length;
    stream.writeInt(entriessize);
    for (int i = 0; i < entriessize; i++) {
      BucketEntry.encode(stream, encodedTransactionMeta.entries[i]);
    }
  }
  public static TransactionMeta decode(XdrDataInputStream stream) throws IOException {
    TransactionMeta decodedTransactionMeta = new TransactionMeta();
    int entriessize = stream.readInt();
    decodedTransactionMeta.entries = new BucketEntry[entriessize];
    for (int i = 0; i < entriessize; i++) {
      decodedTransactionMeta.entries[i] = BucketEntry.decode(stream);
    }
    return decodedTransactionMeta;
  }
}
