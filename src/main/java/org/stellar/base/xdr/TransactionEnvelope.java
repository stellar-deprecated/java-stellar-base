// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TransactionEnvelope
//  {
//      Transaction tx;
//      DecoratedSignature signatures<20>;
//  };

//  ===========================================================================
public class TransactionEnvelope  {
  public TransactionEnvelope () {}
  private Transaction tx;
  public Transaction gettx() {
    return this.tx;
  }
  public void settx(Transaction value) {
    this.tx = value;
  }
  private DecoratedSignature[] signatures;
  public DecoratedSignature[] getsignatures() {
    return this.signatures;
  }
  public void setsignatures(DecoratedSignature[] value) {
    this.signatures = value;
  }
  public static void encode(XdrDataOutputStream stream, TransactionEnvelope encodedTransactionEnvelope) throws IOException{
    Transaction.encode(stream, encodedTransactionEnvelope.tx);
    int signaturessize = encodedTransactionEnvelope.getsignatures().length;
    stream.writeInt(signaturessize);
    for (int i = 0; i < signaturessize; i++) {
      DecoratedSignature.encode(stream, encodedTransactionEnvelope.signatures[i]);
    }
  }
  public static TransactionEnvelope decode(XdrDataInputStream stream) throws IOException {
    TransactionEnvelope decodedTransactionEnvelope = new TransactionEnvelope();
    decodedTransactionEnvelope.tx = Transaction.decode(stream);
    int signaturessize = stream.readInt();
    decodedTransactionEnvelope.signatures = new DecoratedSignature[signaturessize];
    for (int i = 0; i < signaturessize; i++) {
      decodedTransactionEnvelope.signatures[i] = DecoratedSignature.decode(stream);
    }
    return decodedTransactionEnvelope;
  }
}
