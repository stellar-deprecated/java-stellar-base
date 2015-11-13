package org.stellar.base;

import org.apache.commons.codec.binary.Base64;
import org.stellar.base.xdr.EnvelopeType;
import org.stellar.base.xdr.DecoratedSignature;
import org.stellar.base.xdr.PublicKey;
import org.stellar.base.xdr.Signature;
import org.stellar.base.xdr.SignatureHint;
import org.stellar.base.xdr.XdrDataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Transaction {
  private final int BASE_FEE = 100;

  private final int mFee;
  private final Account mSourceAccount;
  private final long mSequenceNumber;
  private final Operation[] mOperations;
  private final org.stellar.base.xdr.Memo mMemo;
  private List<DecoratedSignature> mSignatures;

  Transaction(Account sourceAccount, long sequenceNumber, Operation[] operations, org.stellar.base.xdr.Memo memo) {
    if (operations.length == 0) {
      throw new RuntimeException("At least one operation required.");
    }

    mSourceAccount = sourceAccount;
    mSequenceNumber = sequenceNumber;
    mOperations = operations;
    mFee = operations.length * BASE_FEE;
    mSignatures = new ArrayList<DecoratedSignature>();
    mMemo = memo != null ? memo : Memo.none();
  }

  public void sign(StellarKeypair signer) throws IOException {
    byte[] txHash = this.hash();
    byte[] signatureBytes = signer.sign(txHash);

    Signature signature = new Signature();
    signature.setSignature(signatureBytes);

    ByteArrayOutputStream publicKeyBytesStream = new ByteArrayOutputStream();
    XdrDataOutputStream xdrOutputStream = new XdrDataOutputStream(publicKeyBytesStream);
    PublicKey.encode(xdrOutputStream, signer.getXdrPublicKey());
    byte[] publicKeyBytes = publicKeyBytesStream.toByteArray();
    byte[] signatureHintBytes = Arrays.copyOfRange(publicKeyBytes, publicKeyBytes.length - 4, publicKeyBytes.length);

    SignatureHint signatureHint = new SignatureHint();
    signatureHint.setSignatureHint(signatureHintBytes);

    DecoratedSignature decoratedSignature = new DecoratedSignature();
    decoratedSignature.setHint(signatureHint);
    decoratedSignature.setSignature(signature);

    mSignatures.add(decoratedSignature);
  }

  public byte[] hash() {
    return Util.hash(this.signatureBase());
  }

  public byte[] signatureBase() {
    try {
      ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
      // Hashed NetworkID
      outputStream.write(Network.current().getNetworkId());
      // Envelope Type - 4 bytes
      outputStream.write(ByteBuffer.allocate(4).putInt(EnvelopeType.ENVELOPE_TYPE_TX.getValue()).array());
      // Transaction XDR bytes
      ByteArrayOutputStream txOutputStream = new ByteArrayOutputStream();
      XdrDataOutputStream xdrOutputStream = new XdrDataOutputStream(txOutputStream);
      org.stellar.base.xdr.Transaction.encode(xdrOutputStream, this.toXdr());
      outputStream.write(txOutputStream.toByteArray());

      return outputStream.toByteArray();
    } catch (IOException exception) {
      return null;
    }
  }

  public org.stellar.base.xdr.Transaction toXdr() {
    // fee
    org.stellar.base.xdr.Uint32 fee = new org.stellar.base.xdr.Uint32();
    fee.setUint32(mFee);
    // sequenceNumber
    org.stellar.base.xdr.Uint64 sequenceNumberUint = new org.stellar.base.xdr.Uint64();
    sequenceNumberUint.setUint64(mSequenceNumber);
    org.stellar.base.xdr.SequenceNumber sequenceNumber = new org.stellar.base.xdr.SequenceNumber();
    sequenceNumber.setSequenceNumber(sequenceNumberUint);
    // sourceAccount
    org.stellar.base.xdr.AccountID sourceAccount = new org.stellar.base.xdr.AccountID();
    sourceAccount.setAccountID(mSourceAccount.getKeypair().getXdrPublicKey());
    // operations
    org.stellar.base.xdr.Operation[] operations = new org.stellar.base.xdr.Operation[mOperations.length];
    for (int i = 0; i < mOperations.length; i++) {
      operations[i] = mOperations[i].toXdr();
    }
    // ext
    org.stellar.base.xdr.Transaction.TransactionExt ext = new org.stellar.base.xdr.Transaction.TransactionExt();
    ext.setDiscriminant(0);

    org.stellar.base.xdr.Transaction transaction = new org.stellar.base.xdr.Transaction();
    transaction.setFee(fee);
    transaction.setSeqNum(sequenceNumber);
    transaction.setSourceAccount(sourceAccount);
    transaction.setOperations(operations);
    transaction.setMemo(mMemo);
    transaction.setExt(ext);
    return transaction;
  }

  public org.stellar.base.xdr.TransactionEnvelope toEnvelopeXdr() {
    if (mSignatures.size() == 0) {
      throw new RuntimeException("Transaction must be signed by at least one signer. Use transaction.sign().");
    }

    org.stellar.base.xdr.TransactionEnvelope xdr = new org.stellar.base.xdr.TransactionEnvelope();
    org.stellar.base.xdr.Transaction transaction = this.toXdr();
    xdr.setTx(transaction);

    DecoratedSignature[] signatures = new DecoratedSignature[mSignatures.size()];
    signatures = mSignatures.toArray(signatures);
    xdr.setSignatures(signatures);
    return xdr;
  }

  public String toBase64EnvelopeXdr() throws IOException {
    org.stellar.base.xdr.TransactionEnvelope envelope = this.toEnvelopeXdr();
    ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    XdrDataOutputStream xdrOutputStream = new XdrDataOutputStream(outputStream);
    org.stellar.base.xdr.TransactionEnvelope.encode(xdrOutputStream, envelope);
    Base64 base64Codec = new Base64();
    return base64Codec.encodeAsString(outputStream.toByteArray());
  }

  /**
   * Builder pattern to create new transactions.
   */
  public static class Builder {
    private final Account mSourceAccount;
    private org.stellar.base.xdr.Memo mMemo;
    List<Operation> mOperations;

    /**
     * Construct a new transaction builder.
     * @param sourceAccount The source account for this transaction. This account is the account
     * who will use a sequence number. When build() is called, the account object's sequence number
     * will be incremented.
     */
    public Builder(Account sourceAccount) {
      mSourceAccount = sourceAccount;
      mOperations = new ArrayList<Operation>();
    }

    public Builder addOperation(Operation operation) {
      mOperations.add(operation);
      return this;
    }

    public Builder addMemo(org.stellar.base.xdr.Memo memo) {
      if (mMemo != null) {
        throw new RuntimeException("Memo has been already added.");
      }
      mMemo = memo;
      return this;
    }

    public Transaction build() {
      mSourceAccount.incrementSequenceNumber();
      Operation[] operations = new Operation[mOperations.size()];
      operations = mOperations.toArray(operations);
      return new Transaction(mSourceAccount, mSourceAccount.getSequenceNumber(), operations, mMemo);
    }
  }
}
