// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Transaction
//  {
//      // account used to run the transaction
//      AccountID sourceAccount;
//  
//      // maximum fee this transaction can collect
//      // the transaction is aborted if the fee is higher
//      int32 maxFee;
//  
//      // sequence number to consume in the account
//      SequenceNumber seqNum;
//  
//      // validity range (inclusive) for the ledger sequence number
//      uint32 minLedger;
//      uint32 maxLedger;
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
  private Int32 maxFee;
  public Int32 getmaxFee() {
    return this.maxFee;
  }
  public void setmaxFee(Int32 value) {
    this.maxFee = value;
  }
  private SequenceNumber seqNum;
  public SequenceNumber getseqNum() {
    return this.seqNum;
  }
  public void setseqNum(SequenceNumber value) {
    this.seqNum = value;
  }
  private Uint32 minLedger;
  public Uint32 getminLedger() {
    return this.minLedger;
  }
  public void setminLedger(Uint32 value) {
    this.minLedger = value;
  }
  private Uint32 maxLedger;
  public Uint32 getmaxLedger() {
    return this.maxLedger;
  }
  public void setmaxLedger(Uint32 value) {
    this.maxLedger = value;
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
    Int32.encode(stream, encodedTransaction.maxFee);
    SequenceNumber.encode(stream, encodedTransaction.seqNum);
    Uint32.encode(stream, encodedTransaction.minLedger);
    Uint32.encode(stream, encodedTransaction.maxLedger);
    int operationssize = encodedTransaction.getoperations().length;
    stream.writeInt(operationssize);
    for (int i = 0; i < operationssize; i++) {
      Operation.encode(stream, encodedTransaction.operations[i]);
    }
  }
  public static Transaction decode(XdrDataInputStream stream) throws IOException {
    Transaction decodedTransaction = new Transaction();
    decodedTransaction.sourceAccount = AccountID.decode(stream);
    decodedTransaction.maxFee = Int32.decode(stream);
    decodedTransaction.seqNum = SequenceNumber.decode(stream);
    decodedTransaction.minLedger = Uint32.decode(stream);
    decodedTransaction.maxLedger = Uint32.decode(stream);
    int operationssize = stream.readInt();
    decodedTransaction.operations = new Operation[operationssize];
    for (int i = 0; i < operationssize; i++) {
      decodedTransaction.operations[i] = Operation.decode(stream);
    }
    return decodedTransaction;
  }
}
