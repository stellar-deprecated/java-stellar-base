package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.PaymentOp;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#payment">Payment</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html">List of Operations</a>
 */
public class PaymentOperation extends Operation {

  private final StellarKeypair mDestination;
  private final Asset mAsset;
  private final long mAmount;

  private PaymentOperation(StellarKeypair destination, Asset asset, long amount) {
    mDestination = destination;
    mAsset = asset;
    mAmount = amount;
  }

  /**
   * Account that receives the payment.
   * @return
   */
  public StellarKeypair getDestination() {
    return mDestination;
  }

  /**
   * Asset to send to the destination account.
   * @return
   */
  public Asset getAsset() {
    return mAsset;
  }

  /**
   * Amount of the asset to send.
   * @return
   */
  public long getAmount() {
    return mAmount;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    PaymentOp op = new PaymentOp();

    // destination
    AccountID destination = new AccountID();
    destination.setAccountID(mDestination.getXdrPublicKey());
    op.setDestination(destination);
    // asset
    op.setAsset(mAsset.toXdr());
    // amount
    Int64 amount = new Int64();
    amount.setInt64(mAmount);
    op.setAmount(amount);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.PAYMENT);
    body.setPaymentOp(op);
    return body;
  }

  /**
   * Builds Payment operation.
   * @see PathPaymentOperation
   */
  public static class Builder {
    private final StellarKeypair mDestination;
    private final Asset mAsset;
    private final long mAmount;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new PaymentOperation builder from a PaymentOp XDR.
     * @param op {@link PaymentOp}
     */
    Builder(PaymentOp op) {
      mDestination = StellarKeypair.fromXdrPublicKey(op.getDestination().getAccountID());
      mAsset = Asset.fromXdr(op.getAsset());
      mAmount = op.getAmount().getInt64().longValue();
    }

    /**
     * Creates a new PaymentOperation builder.
     * @param destination The destination keypair (uses only the public key).
     * @param asset The asset to send.
     * @param amount The amount to send.
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

    /**
     * Builds an operation
     * @return
     */
    public PaymentOperation build() {
      PaymentOperation operation = new PaymentOperation(mDestination, mAsset, mAmount);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
