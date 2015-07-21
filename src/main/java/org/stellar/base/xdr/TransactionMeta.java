// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union TransactionMeta switch (int v)
//  {
//  case 0:
//      struct
//      {
//          LedgerEntryChanges changes;
//          OperationMeta operations<>;
//      } v0;
//  };

//  ===========================================================================
public class TransactionMeta  {
  public TransactionMeta () {}
  Integer v;
  public Integer getDiscriminant() {
    return this.v;
  }
  public void setDiscriminant(Integer value) {
    this.v = value;
  }
  private TransactionMetaV0 v0;
  public TransactionMetaV0 getv0() {
    return this.v0;
  }
  public void setv0(TransactionMetaV0 value) {
    this.v0 = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionMeta encodedTransactionMeta) throws IOException {
  stream.writeInt(encodedTransactionMeta.getDiscriminant().intValue());
  switch (encodedTransactionMeta.getDiscriminant()) {
  case 0:
  TransactionMetaV0.encode(stream, encodedTransactionMeta.v0);
  break;
  }
  }
  public static TransactionMeta decode(XdrDataInputStream stream) throws IOException {
    TransactionMeta decodedTransactionMeta = new TransactionMeta();
    switch (decodedTransactionMeta.getDiscriminant()) {
  case 0:
  decodedTransactionMeta.v0 = TransactionMetaV0.decode(stream);
  break;
  }
    return decodedTransactionMeta;
  }

  public static class TransactionMetaV0 {
    public TransactionMetaV0 () {}
    private LedgerEntryChanges changes;
    public LedgerEntryChanges getchanges() {
      return this.changes;
    }
    public void setchanges(LedgerEntryChanges value) {
      this.changes = value;
    }
    private OperationMeta[] operations;
    public OperationMeta[] getoperations() {
      return this.operations;
    }
    public void setoperations(OperationMeta[] value) {
      this.operations = value;
    }
    public static void encode(XdrDataOutputStream stream, TransactionMetaV0 encodedTransactionMetaV0) throws IOException{
      LedgerEntryChanges.encode(stream, encodedTransactionMetaV0.changes);
      int operationssize = encodedTransactionMetaV0.getoperations().length;
      stream.writeInt(operationssize);
      for (int i = 0; i < operationssize; i++) {
        OperationMeta.encode(stream, encodedTransactionMetaV0.operations[i]);
      }
    }
    public static TransactionMetaV0 decode(XdrDataInputStream stream) throws IOException {
      TransactionMetaV0 decodedTransactionMetaV0 = new TransactionMetaV0();
      decodedTransactionMetaV0.changes = LedgerEntryChanges.decode(stream);
      int operationssize = stream.readInt();
      decodedTransactionMetaV0.operations = new OperationMeta[operationssize];
      for (int i = 0; i < operationssize; i++) {
        decodedTransactionMetaV0.operations[i] = OperationMeta.decode(stream);
      }
      return decodedTransactionMetaV0;
    }

  }
}
