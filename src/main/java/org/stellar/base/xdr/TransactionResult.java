// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionResult
//  {
//      int64 feeCharged; // actual fee charged for the transaction
//  
//      union switch (TransactionResultCode code)
//      {
//      case txSUCCESS:
//      case txFAILED:
//          OperationResult results<>;
//      default:
//          void;
//      }
//      result;
//  };

//  ===========================================================================
public class TransactionResult  {
  public TransactionResult () {}
  private Int64 feeCharged;
  public Int64 getfeeCharged() {
    return this.feeCharged;
  }
  public void setfeeCharged(Int64 value) {
    this.feeCharged = value;
  }
  private TransactionResultResult result;
  public TransactionResultResult getresult() {
    return this.result;
  }
  public void setresult(TransactionResultResult value) {
    this.result = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionResult encodedTransactionResult) throws IOException{
    Int64.encode(stream, encodedTransactionResult.feeCharged);
    TransactionResultResult.encode(stream, encodedTransactionResult.result);
  }
  public static TransactionResult decode(XdrDataInputStream stream) throws IOException {
    TransactionResult decodedTransactionResult = new TransactionResult();
    decodedTransactionResult.feeCharged = Int64.decode(stream);
    decodedTransactionResult.result = TransactionResultResult.decode(stream);
    return decodedTransactionResult;
  }

  public static class TransactionResultResult {
    public TransactionResultResult () {}
    TransactionResultCode code;
    public TransactionResultCode getDiscriminant() {
      return this.code;
    }
    public void setDiscriminant(TransactionResultCode value) {
      this.code = value;
    }
    private OperationResult[] results;
    public OperationResult[] getresults() {
      return this.results;
    }
    public void setresults(OperationResult[] value) {
      this.results = value;
    }
    public static void encode(XdrDataOutputStream stream, TransactionResultResult encodedTransactionResultResult) throws IOException {
      switch (encodedTransactionResultResult.getDiscriminant()) {
    case txSUCCESS:
    case txFAILED:
    int resultssize = encodedTransactionResultResult.getresults().length;
    stream.writeInt(resultssize);
    for (int i = 0; i < resultssize; i++) {
      OperationResult.encode(stream, encodedTransactionResultResult.results[i]);
    }
    break;
    default:
    break;
    }
    }
    public static TransactionResultResult decode(XdrDataInputStream stream) throws IOException {
      TransactionResultResult decodedTransactionResultResult = new TransactionResultResult();
      switch (decodedTransactionResultResult.getDiscriminant()) {
    case txSUCCESS:
    case txFAILED:
    int resultssize = stream.readInt();
    decodedTransactionResultResult.results = new OperationResult[resultssize];
    for (int i = 0; i < resultssize; i++) {
      decodedTransactionResultResult.results[i] = OperationResult.decode(stream);
    }
    break;
    default:
    break;
    }
      return decodedTransactionResultResult;
    }

  }
}
