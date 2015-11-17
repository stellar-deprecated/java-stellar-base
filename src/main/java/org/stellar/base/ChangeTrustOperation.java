package org.stellar.base;

import org.stellar.base.xdr.ChangeTrustOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#change-trust" target="_blank">ChangeTrust</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html" target="_blank">List of Operations</a>
 */
public class ChangeTrustOperation extends Operation {

  private final Asset mAsset;
  private final long mLimit;

  private ChangeTrustOperation(Asset asset, long limit) {
    mAsset = asset;
    mLimit = limit;
  }

  /**
   * The asset of the trustline. For example, if a gateway extends a trustline of up to 200 USD to a user, the line is USD.
   * @return
   */
  public Asset getAsset() {
    return mAsset;
  }

  /**
   * The limit of the trustline. For example, if a gateway extends a trustline of up to 200 USD to a user, the limit is 200.
   * @return
   */
  public long getLimit() {
    return mLimit;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    ChangeTrustOp op = new ChangeTrustOp();
    op.setLine(mAsset.toXdr());
    Int64 limit = new Int64();
    limit.setInt64(mLimit);
    op.setLimit(limit);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.CHANGE_TRUST);
    body.setChangeTrustOp(op);
    return body;
  }

  /**
   * Builds ChangeTrust operation.
   * @see ChangeTrustOperation
   */
  public static class Builder {
    private final Asset mAsset;
    private final long mLimit;

    private Keypair mSourceAccount;

    Builder(ChangeTrustOp op) {
      mAsset = Asset.fromXdr(op.getLine());
      mLimit = op.getLimit().getInt64();
    }

    /**
     * Creates a new ChangeTrust builder.
     * @param asset The asset of the trustline. For example, if a gateway extends a trustline of up to 200 USD to a user, the line is USD.
     * @param limit The limit of the trustline. For example, if a gateway extends a trustline of up to 200 USD to a user, the limit is 200.
     */
    public Builder(Asset asset, long limit) {
      mAsset = asset;
      mLimit = limit;
    }

    /**
     * Set source account of this operation
     * @param sourceAccount Source account
     * @return Builder object so you can chain methods.
     */
    public Builder setSourceAccount(Keypair sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /**
     * Builds an operation
     * @return
     */
    public ChangeTrustOperation build() {
      ChangeTrustOperation operation = new ChangeTrustOperation(mAsset, mLimit);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
