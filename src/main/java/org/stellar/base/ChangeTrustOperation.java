package org.stellar.base;

import org.stellar.base.xdr.ChangeTrustOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;

public class ChangeTrustOperation extends Operation {

  private final Currency mLine;
  private final long mLimit;

  private ChangeTrustOperation(Currency line, long limit) {
    mLine = line;
    mLimit = limit;
  }

  public Currency getLine() {
    return mLine;
  }

  public long getLimit() {
    return mLimit;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    ChangeTrustOp op = new ChangeTrustOp();
    op.setline(mLine.toXdr());
    Int64 limit = new Int64();
    limit.setint64(mLimit);
    op.setlimit(limit);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.CHANGE_TRUST);
    body.setchangeTrustOp(op);
    return body;
  }

  public static class Builder {
    private final Currency mLine;
    private final long mLimit;

    private StellarKeypair mSourceAccount;

    Builder(ChangeTrustOp op) {
      mLine = Currency.fromXdr(op.getline());
      mLimit = op.getlimit().getint64();
    }

    public Builder(Currency line, long limit) {
      mLine = line;
      mLimit = limit;
    }

    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

    public ChangeTrustOperation build() {
      ChangeTrustOperation operation = new ChangeTrustOperation(mLine, mLimit);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
