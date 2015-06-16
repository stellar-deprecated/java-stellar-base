// Automatically generated on 2015-06-16T15:35:11-07:00
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
//      int32 fee;
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
  private Int32 fee;
  public Int32 getfee() {
    return this.fee;
  }
  public void setfee(Int32 value) {
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
  public static void encode(XdrDataOutputStream stream, Transaction encodedTransaction) throws IOException{
    AccountID.encode(stream, encodedTransaction.sourceAccount);
    Int32.encode(stream, encodedTransaction.fee);
    SequenceNumber.encode(stream, encodedTransaction.seqNum);
    if (encodedTransaction.timeBounds != null) {
    TimeBounds.encode(stream, encodedTransaction.timeBounds);
    }
    Memo.encode(stream, encodedTransaction.memo);
    int operationssize = encodedTransaction.getoperations().length;
    stream.writeInt(operationssize);
    for (int i = 0; i < operationssize; i++) {
      Operation.encode(stream, encodedTransaction.operations[i]);
    }
  }
  public static Transaction decode(XdrDataInputStream stream) throws IOException {
    Transaction decodedTransaction = new Transaction();
    decodedTransaction.sourceAccount = AccountID.decode(stream);
    decodedTransaction.fee = Int32.decode(stream);
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
    return decodedTransaction;
  }
}
