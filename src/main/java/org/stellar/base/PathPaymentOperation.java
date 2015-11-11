package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.PathPaymentOp;

public class PathPaymentOperation extends Operation {

  private final Asset mSendAsset;
  private final long mSendMax;
  private final StellarKeypair mDestination;
  private final Asset mDestAsset;
  private final long mDestAmount;
  private final Asset[] mPath;

  private PathPaymentOperation(Asset sendAsset, long sendMax, StellarKeypair destination,
      Asset destAsset, long destAmount, Asset[] path) {
    mSendAsset = sendAsset;
    mSendMax = sendMax;
    mDestination = destination;
    mDestAsset = destAsset;
    mDestAmount = destAmount;
    mPath = path;
  }

  public Asset getSendAsset() {
    return mSendAsset;
  }

  public long getSendMax() {
    return mSendMax;
  }

  public StellarKeypair getDestination() {
    return mDestination;
  }

  public Asset getDestAsset() {
    return mDestAsset;
  }

  public long getDestAmount() {
    return mDestAmount;
  }

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

  public static class Builder {
    private final Asset mSendAsset;
    private final long mSendMax;
    private final StellarKeypair mDestination;
    private final Asset mDestAsset;
    private final long mDestAmount;
    private final Asset[] mPath;

    private StellarKeypair mSourceAccount;

    Builder(PathPaymentOp op) {
      mSendAsset = Asset.fromXdr(op.getSendAsset());
      mSendMax = op.getSendMax().getInt64();
      mDestination = StellarKeypair.fromXdrPublicKey(op.getDestination().getAccountID());
      mDestAsset = Asset.fromXdr(op.getDestAsset());
      mDestAmount = op.getDestAmount().getInt64();
      mPath = new Asset[op.getPath().length];
      for (int i = 0; i < op.getPath().length; i++) {
        mPath[i] = Asset.fromXdr(op.getPath()[i]);
      }
    }

    public Builder(Asset sendAsset, long sendMax, StellarKeypair destination,
        Asset destAsset, long destAmount, Asset[] path) {
      mSendAsset = sendAsset;
      mSendMax = sendMax;
      mDestination = destination;
      mDestAsset = destAsset;
      mDestAmount = destAmount;
      mPath = path;
    }

    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

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
