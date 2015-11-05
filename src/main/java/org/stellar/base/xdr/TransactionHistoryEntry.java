// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionHistoryEntry
//  {
//      uint32 ledgerSeq;
//      TransactionSet txSet;
//  
//      // reserved for future use
//      union switch (int v)
//      {
//      case 0:
//          void;
//      }
//      ext;
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
  private TransactionHistoryEntryExt ext;
  public TransactionHistoryEntryExt getext() {
    return this.ext;
  }
  public void setext(TransactionHistoryEntryExt value) {
    this.ext = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionHistoryEntry encodedTransactionHistoryEntry) throws IOException{
    Uint32.encode(stream, encodedTransactionHistoryEntry.ledgerSeq);
    TransactionSet.encode(stream, encodedTransactionHistoryEntry.txSet);
    TransactionHistoryEntryExt.encode(stream, encodedTransactionHistoryEntry.ext);
  }
  public static TransactionHistoryEntry decode(XdrDataInputStream stream) throws IOException {
    TransactionHistoryEntry decodedTransactionHistoryEntry = new TransactionHistoryEntry();
    decodedTransactionHistoryEntry.ledgerSeq = Uint32.decode(stream);
    decodedTransactionHistoryEntry.txSet = TransactionSet.decode(stream);
    decodedTransactionHistoryEntry.ext = TransactionHistoryEntryExt.decode(stream);
    return decodedTransactionHistoryEntry;
  }

  public static class TransactionHistoryEntryExt {
    public TransactionHistoryEntryExt () {}
    Integer v;
    public Integer getDiscriminant() {
      return this.v;
    }
    public void setDiscriminant(Integer value) {
      this.v = value;
    }
    public static void encode(XdrDataOutputStream stream, TransactionHistoryEntryExt encodedTransactionHistoryEntryExt) throws IOException {
    stream.writeInt(encodedTransactionHistoryEntryExt.getDiscriminant().intValue());
    switch (encodedTransactionHistoryEntryExt.getDiscriminant()) {
    case 0:
    break;
    }
    }
    public static TransactionHistoryEntryExt decode(XdrDataInputStream stream) throws IOException {
      TransactionHistoryEntryExt decodedTransactionHistoryEntryExt = new TransactionHistoryEntryExt();
      switch (decodedTransactionHistoryEntryExt.getDiscriminant()) {
    case 0:
    break;
    }
      return decodedTransactionHistoryEntryExt;
    }

  }
}
