package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.PaymentOp;

public class PaymentOperation extends Operation {

  private final StellarKeypair mDestination;
  private final Asset mAsset;
  private final long mAmount;

  private PaymentOperation(StellarKeypair destination, Asset asset, long amount) {
    mDestination = destination;
    mAsset = asset;
    mAmount = amount;
  }

  public StellarKeypair getDestination() {
    return mDestination;
  }

  public Asset getAsset() {
    return mAsset;
  }

  public long getAmount() {
    return mAmount;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    PaymentOp op = new PaymentOp();

    // destination
    AccountID destination = new AccountID();
    destination.setAccountID(mDestination.getXdrPublicKey());
    op.setdestination(destination);
    // asset
    op.setasset(mAsset.toXdr());
    // amount
    Int64 amount = new Int64();
    amount.setint64(mAmount);
    op.setamount(amount);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.PAYMENT);
    body.setpaymentOp(op);
    return body;
  }

  static class Builder {
    private final StellarKeypair mDestination;
    private final Asset mAsset;
    private final long mAmount;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new PaymentOperation builder from a PaymentOp XDR.
     * @param op {@link PaymentOp}
     */
    Builder(PaymentOp op) {
      mDestination = StellarKeypair.fromXdrPublicKey(op.getdestination().getAccountID());
      mAsset = Asset.fromXdr(op.getasset());
      mAmount = op.getamount().getint64().longValue();
    }

    /**
     * Creates a new PaymentOperation builder.
     * @param destination The destination keypair (uses only the public key).
     * @param asset The asset to send.
     * @param amount The amount to send.
     * @param
     */
    public Builder(StellarKeypair destination, Asset asset, long amount) {
      mDestination = destination;
      mAsset = asset;
      mAmount = amount;
    }

    /**
     * Sets the source account for this operation.
     * @param account The operation's source account.
     * @return
     */
    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

    public PaymentOperation build() {
      PaymentOperation operation = new PaymentOperation(mDestination, mAsset, mAmount);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
