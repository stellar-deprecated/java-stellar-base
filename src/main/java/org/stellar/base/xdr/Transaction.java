// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Transaction
//  {
//      // account used to run the transaction
//      AccountID sourceAccount;
//  
//      // the fee the sourceAccount will pay
//      uint32 fee;
//  
//      // sequence number to consume in the account
//      SequenceNumber seqNum;
//  
//      // validity range (inclusive) for the last ledger close time
//      TimeBounds* timeBounds;
//  
//      Memo memo;
//  
//      Operation operations<100>;
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
public class Transaction  {
  public Transaction () {}
  private AccountID sourceAccount;
  public AccountID getsourceAccount() {
    return this.sourceAccount;
  }
  public void setsourceAccount(AccountID value) {
    this.sourceAccount = value;
  }
  private Uint32 fee;
  public Uint32 getfee() {
    return this.fee;
  }
  public void setfee(Uint32 value) {
    this.fee = value;
  }
  private SequenceNumber seqNum;
  public SequenceNumber getseqNum() {
    return this.seqNum;
  }
  public void setseqNum(SequenceNumber value) {
    this.seqNum = value;
  }
  private TimeBounds timeBounds;
  public TimeBounds gettimeBounds() {
    return this.timeBounds;
  }
  public void settimeBounds(TimeBounds value) {
    this.timeBounds = value;
  }
  private Memo memo;
  public Memo getmemo() {
    return this.memo;
  }
  public void setmemo(Memo value) {
    this.memo = value;
  }
  private Operation[] operations;
  public Operation[] getoperations() {
    return this.operations;
  }
  public void setoperations(Operation[] value) {
    this.operations = value;
  }
  private TransactionExt ext;
  public TransactionExt getext() {
    return this.ext;
  }
  public void setext(TransactionExt value) {
    this.ext = value;
  }
  public static void encode(XdrDataOutputStream stream, Transaction encodedTransaction) throws IOException{
    AccountID.encode(stream, encodedTransaction.sourceAccount);
    Uint32.encode(stream, encodedTransaction.fee);
    SequenceNumber.encode(stream, encodedTransaction.seqNum);
    if (encodedTransaction.timeBounds != null) {
    stream.writeInt(1);
    TimeBounds.encode(stream, encodedTransaction.timeBounds);
    } else {
    stream.writeInt(0);
    }
    Memo.encode(stream, encodedTransaction.memo);
    int operationssize = encodedTransaction.getoperations().length;
    stream.writeInt(operationssize);
    for (int i = 0; i < operationssize; i++) {
      Operation.encode(stream, encodedTransaction.operations[i]);
    }
    TransactionExt.encode(stream, encodedTransaction.ext);
  }
  public static Transaction decode(XdrDataInputStream stream) throws IOException {
    Transaction decodedTransaction = new Transaction();
    decodedTransaction.sourceAccount = AccountID.decode(stream);
    decodedTransaction.fee = Uint32.decode(stream);
    decodedTransaction.seqNum = SequenceNumber.decode(stream);
    int timeBoundsPresent = stream.readInt();
    if (timeBoundsPresent != 0) {
    decodedTransaction.timeBounds = TimeBounds.decode(stream);
    }
    decodedTransaction.memo = Memo.decode(stream);
    int operationssize = stream.readInt();
    decodedTransaction.operations = new Operation[operationssize];
    for (int i = 0; i < operationssize; i++) {
      decodedTransaction.operations[i] = Operation.decode(stream);
    }
    decodedTransaction.ext = TransactionExt.decode(stream);
    return decodedTransaction;
  }

  public static class TransactionExt {
    public TransactionExt () {}
    Integer v;
    public Integer getDiscriminant() {
      return this.v;
    }
    public void setDiscriminant(Integer value) {
      this.v = value;
    }
    public static void encode(XdrDataOutputStream stream, TransactionExt encodedTransactionExt) throws IOException {
    stream.writeInt(encodedTransactionExt.getDiscriminant().intValue());
    switch (encodedTransactionExt.getDiscriminant()) {
    case 0:
    break;
    }
    }
    public static TransactionExt decode(XdrDataInputStream stream) throws IOException {
      TransactionExt decodedTransactionExt = new TransactionExt();
      switch (decodedTransactionExt.getDiscriminant()) {
    case 0:
    break;
    }
      return decodedTransactionExt;
    }

  }
}
