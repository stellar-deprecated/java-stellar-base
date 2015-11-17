package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.PathPaymentOp;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#path-payment" target="_blank">PathPayment</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html" target="_blank">List of Operations</a>
 */
public class PathPaymentOperation extends Operation {

  private final Asset mSendAsset;
  private final long mSendMax;
  private final Keypair mDestination;
  private final Asset mDestAsset;
  private final long mDestAmount;
  private final Asset[] mPath;

  private PathPaymentOperation(Asset sendAsset, long sendMax, Keypair destination,
      Asset destAsset, long destAmount, Asset[] path) {
    mSendAsset = sendAsset;
    mSendMax = sendMax;
    mDestination = destination;
    mDestAsset = destAsset;
    mDestAmount = destAmount;
    mPath = path;
  }

  /**
   * The asset deducted from the sender's account.
   * @return
   */
  public Asset getSendAsset() {
    return mSendAsset;
  }

  /**
   * The maximum amount of send asset to deduct (excluding fees)
   * @return
   */
  public long getSendMax() {
    return mSendMax;
  }

  /**
   * Account that receives the payment.
   * @return
   */
  public Keypair getDestination() {
    return mDestination;
  }

  /**
   * The asset the destination account receives.
   * @return
   */
  public Asset getDestAsset() {
    return mDestAsset;
  }

  /**
   * The amount of destination asset the destination account receives.
   * @return
   */
  public long getDestAmount() {
    return mDestAmount;
  }

  /**
   * The assets (other than send asset and destination asset) involved in the offers the path takes. For example, if you can only find a path from USD to EUR through XLM and BTC, the path would be USD -> XLM -> BTC -> EUR and the path would contain XLM and BTC.
   * @return
   */
  public Asset[] getPath() {
    return mPath;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    PathPaymentOp op = new PathPaymentOp();

    // sendAsset
    op.setSendAsset(mSendAsset.toXdr());
    // sendMax
    Int64 sendMax = new Int64();
    sendMax.setInt64(mSendMax);
    op.setSendMax(sendMax);
    // destination
    AccountID destination = new AccountID();
    destination.setAccountID(mDestination.getXdrPublicKey());
    op.setDestination(destination);
    // destAsset
    op.setDestAsset(mDestAsset.toXdr());
    // destAmount
    Int64 destAmount = new Int64();
    destAmount.setInt64(mDestAmount);
    op.setDestAmount(destAmount);
    // path
    org.stellar.base.xdr.Asset[] path = new org.stellar.base.xdr.Asset[mPath.length];
    for (int i = 0; i < mPath.length; i++) {
      path[i] = mPath[i].toXdr();
    }
    op.setPath(path);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.PATH_PAYMENT);
    body.setPathPaymentOp(op);
    return body;
  }

  /**
   * Builds PathPayment operation.
   * @see PathPaymentOperation
   */
  public static class Builder {
    private final Asset mSendAsset;
    private final long mSendMax;
    private final Keypair mDestination;
    private final Asset mDestAsset;
    private final long mDestAmount;
    private final Asset[] mPath;

    private Keypair mSourceAccount;

    Builder(PathPaymentOp op) {
      mSendAsset = Asset.fromXdr(op.getSendAsset());
      mSendMax = op.getSendMax().getInt64();
      mDestination = Keypair.fromXdrPublicKey(op.getDestination().getAccountID());
      mDestAsset = Asset.fromXdr(op.getDestAsset());
      mDestAmount = op.getDestAmount().getInt64();
      mPath = new Asset[op.getPath().length];
      for (int i = 0; i < op.getPath().length; i++) {
        mPath[i] = Asset.fromXdr(op.getPath()[i]);
      }
    }

    /**
     * Creates a new PathPaymentOperation builder.
     * @param sendAsset The asset deducted from the sender's account.
     * @param sendMax The asset deducted from the sender's account.
     * @param destination Payment destination
     * @param destAsset The asset the destination account receives.
     * @param destAmount The amount of destination asset the destination account receives.
     * @param path The assets (other than send asset and destination asset) involved in the offers the path takes. For example, if you can only find a path from USD to EUR through XLM and BTC, the path would be USD -> XLM -> BTC -> EUR and the path field would contain XLM and BTC.
     */
    public Builder(Asset sendAsset, long sendMax, Keypair destination,
        Asset destAsset, long destAmount, Asset[] path) {
      mSendAsset = sendAsset;
      mSendMax = sendMax;
      mDestination = destination;
      mDestAsset = destAsset;
      mDestAmount = destAmount;
      mPath = path;
    }

    /**
     * Sets the source account for this operation.
     * @param sourceAccount The operation's source account.
     * @return
     */
    public Builder setSourceAccount(Keypair sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /**
     * Builds an operation
     * @return
     */
    public PathPaymentOperation build() {
      PathPaymentOperation operation = new PathPaymentOperation(mSendAsset, mSendMax, mDestination,
          mDestAsset, mDestAmount, mPath);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
