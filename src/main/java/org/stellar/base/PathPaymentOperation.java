package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.PathPaymentOp;

public class PathPaymentOperation extends Operation {

  private final Currency mSendCurrency;
  private final long mSendMax;
  private final StellarKeypair mDestination;
  private final Currency mDestCurrency;
  private final long mDestAmount;
  private final Currency[] mPath;

  private PathPaymentOperation(Currency sendCurrency, long sendMax, StellarKeypair destination,
      Currency destCurrency, long destAmount, Currency[] path) {
    mSendCurrency = sendCurrency;
    mSendMax = sendMax;
    mDestination = destination;
    mDestCurrency = destCurrency;
    mDestAmount = destAmount;
    mPath = path;
  }

  public Currency getSendCurrency() {
    return mSendCurrency;
  }

  public long getSendMax() {
    return mSendMax;
  }

  public StellarKeypair getDestination() {
    return mDestination;
  }

  public Currency getDestCurrency() {
    return mDestCurrency;
  }

  public long getDestAmount() {
    return mDestAmount;
  }

  public Currency[] getPath() {
    return mPath;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    PathPaymentOp op = new PathPaymentOp();
    op.setsendCurrency(mSendCurrency.toXdr());
    Int64 sendMax = new Int64();
    sendMax.setint64(mSendMax);
    op.setsendMax(sendMax);
    AccountID destination = new AccountID();
    destination.setAccountID(mDestination.getPublicKey());
    op.setdestination(destination);
    op.setdestCurrency(mDestCurrency.toXdr());
    Int64 destAmount = new Int64();
    destAmount.setint64(mDestAmount);
    op.setdestAmount(destAmount);
    org.stellar.base.xdr.Currency[] path = new org.stellar.base.xdr.Currency[mPath.length];
    for (int i = 0; i < mPath.length; i++) {
      path[i] = mPath[i].toXdr();
    }
    op.setpath(path);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.PATH_PAYMENT);
    body.setpathPaymentOp(op);
    return body;
  }

  public static class Builder {
    private final Currency mSendCurrency;
    private final long mSendMax;
    private final StellarKeypair mDestination;
    private final Currency mDestCurrency;
    private final long mDestAmount;
    private final Currency[] mPath;

    private StellarKeypair mSourceAccount;

    Builder(PathPaymentOp op) {
      mSendCurrency = Currency.fromXdr(op.getsendCurrency());
      mSendMax = op.getsendMax().getint64();
      mDestination = StellarKeypair.fromPublicKey(op.getdestination().getAccountID());
      mDestCurrency = Currency.fromXdr(op.getdestCurrency());
      mDestAmount = op.getdestAmount().getint64();
      mPath = new Currency[op.getpath().length];
      for (int i = 0; i < op.getpath().length; i++) {
        mPath[i] = Currency.fromXdr(op.getpath()[i]);
      }
    }

    public Builder(Currency sendCurrency, long sendMax, StellarKeypair destination,
        Currency destCurrency, long destAmount, Currency[] path) {
      mSendCurrency = sendCurrency;
      mSendMax = sendMax;
      mDestination = destination;
      mDestCurrency = destCurrency;
      mDestAmount = destAmount;
      mPath = path;
    }

    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

    public PathPaymentOperation build() {
      PathPaymentOperation operation = new PathPaymentOperation(mSendCurrency, mSendMax, mDestination,
          mDestCurrency, mDestAmount, mPath);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
