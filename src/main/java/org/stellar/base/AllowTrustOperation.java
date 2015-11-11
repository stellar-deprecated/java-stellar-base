package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.AllowTrustOp;
import org.stellar.base.xdr.AssetType;
import org.stellar.base.xdr.OperationType;

public class AllowTrustOperation extends Operation {

  private final StellarKeypair mTrustor;
  private final String mAssetCode;
  private final boolean mAuthorize;

  private AllowTrustOperation(StellarKeypair trustor, String assetCode, boolean authorize) {
    mTrustor = trustor;
    mAssetCode = assetCode;
    mAuthorize = authorize;
  }

  public StellarKeypair getTrustor() {
    return mTrustor;
  }

  public String getAssetCode() {
    return mAssetCode;
  }

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
      asset.setAssetCode4(Asset.filledByteArray(mAssetCode, 4));
    } else {
      asset.setDiscriminant(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12);
      asset.setAssetCode12(Asset.filledByteArray(mAssetCode, 12));
    }
    op.setAsset(asset);
    // authorize
    op.setAuthorize(mAuthorize);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.ALLOW_TRUST);
    body.setAllowTrustOp(op);
    return body;
  }

  public static class Builder {
    private final StellarKeypair mTrustor;
    private final String mAssetCode;
    private final boolean mAuthorize;

    private StellarKeypair mSourceAccount;

    Builder(AllowTrustOp op) {
      mTrustor = StellarKeypair.fromXdrPublicKey(op.getTrustor().getAccountID());
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

    public Builder(StellarKeypair trustor, String assetCode, boolean authorize) {
      mTrustor = trustor;
      mAssetCode = assetCode;
      mAuthorize = authorize;
    }

    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

    public AllowTrustOperation build() {
      AllowTrustOperation operation = new AllowTrustOperation(mTrustor, mAssetCode, mAuthorize);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
