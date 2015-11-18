package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.AllowTrustOp;
import org.stellar.base.xdr.AssetType;
import org.stellar.base.xdr.OperationType;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#allow-trust" target="_blank">AllowTrust</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html" target="_blank">List of Operations</a>
 */
public class AllowTrustOperation extends Operation {

  private final Keypair mTrustor;
  private final String mAssetCode;
  private final boolean mAuthorize;

  private AllowTrustOperation(Keypair trustor, String assetCode, boolean authorize) {
    mTrustor = checkNotNull(trustor, "trustor cannot be null");
    mAssetCode = checkNotNull(assetCode, "assetCode cannot be null");
    mAuthorize = authorize;
  }

  /**
   * The account of the recipient of the trustline.
   */
  public Keypair getTrustor() {
    return mTrustor;
  }

  /**
   * The asset of the trustline the source account is authorizing. For example, if a gateway wants to allow another account to hold its USD credit, the type is USD.
   */
  public String getAssetCode() {
    return mAssetCode;
  }

  /**
   * Flag indicating whether the trustline is authorized.
   */
  public boolean getAuthorize() {
    return mAuthorize;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    AllowTrustOp op = new AllowTrustOp();

    // trustor
    AccountID trustor = new AccountID();
    trustor.setAccountID(mTrustor.getXdrPublicKey());
    op.setTrustor(trustor);
    // asset
    AllowTrustOp.AllowTrustOpAsset asset = new AllowTrustOp.AllowTrustOpAsset();
    if (mAssetCode.length() <= 4) {
      asset.setDiscriminant(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4);
      asset.setAssetCode4(Util.paddedByteArray(mAssetCode, 4));
    } else {
      asset.setDiscriminant(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12);
      asset.setAssetCode12(Util.paddedByteArray(mAssetCode, 12));
    }
    op.setAsset(asset);
    // authorize
    op.setAuthorize(mAuthorize);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.ALLOW_TRUST);
    body.setAllowTrustOp(op);
    return body;
  }

  /**
   * Builds AllowTrust operation.
   * @see AllowTrustOperation
   */
  public static class Builder {
    private final Keypair mTrustor;
    private final String mAssetCode;
    private final boolean mAuthorize;

    private Keypair mSourceAccount;

    Builder(AllowTrustOp op) {
      mTrustor = Keypair.fromXdrPublicKey(op.getTrustor().getAccountID());
      switch (op.getAsset().getDiscriminant()) {
        case ASSET_TYPE_CREDIT_ALPHANUM4:
          mAssetCode = new String(op.getAsset().getAssetCode4());
          break;
        case ASSET_TYPE_CREDIT_ALPHANUM12:
          mAssetCode = new String(op.getAsset().getAssetCode12().toString());
          break;
        default:
          throw new RuntimeException("Unknown asset code");
      }
      mAuthorize = op.getAuthorize();
    }

    /**
     * Creates a new AllowTrust builder.
     * @param trustor The account of the recipient of the trustline.
     * @param assetCode The asset of the trustline the source account is authorizing. For example, if a gateway wants to allow another account to hold its USD credit, the type is USD.
     * @param authorize Flag indicating whether the trustline is authorized.
     */
    public Builder(Keypair trustor, String assetCode, boolean authorize) {
      mTrustor = trustor;
      mAssetCode = assetCode;
      mAuthorize = authorize;
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
     */
    public AllowTrustOperation build() {
      AllowTrustOperation operation = new AllowTrustOperation(mTrustor, mAssetCode, mAuthorize);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
