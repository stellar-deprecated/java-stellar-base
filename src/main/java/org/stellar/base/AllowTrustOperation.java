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
    AccountID trustor = new AccountID();
    trustor.setAccountID(mTrustor.getXdrPublicKey());
    op.settrustor(trustor);
    AllowTrustOp.AllowTrustOpAsset asset = new AllowTrustOp.AllowTrustOpAsset();
    if (mAssetCode.length() <= 4) {
      asset.setDiscriminant(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4);
      asset.setassetCode4(mAssetCode.getBytes());
    } else {
      asset.setDiscriminant(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12);
      asset.setassetCode12(mAssetCode.getBytes());
    }
    op.setasset(asset);
    op.setauthorize(mAuthorize);
    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.ALLOW_TRUST);
    body.setallowTrustOp(op);
    return body;
  }

  public static class Builder {
    private final StellarKeypair mTrustor;
    private final String mAssetCode;
    private final boolean mAuthorize;

    private StellarKeypair mSourceAccount;

    Builder(AllowTrustOp op) {
      mTrustor = StellarKeypair.fromXdrPublicKey(op.gettrustor().getAccountID());
      switch (op.getasset().getDiscriminant()) {
        case ASSET_TYPE_CREDIT_ALPHANUM4:
          mAssetCode = new String(op.getasset().getassetCode4());
          break;
        case ASSET_TYPE_CREDIT_ALPHANUM12:
          mAssetCode = new String(op.getasset().getassetCode12().toString());
          break;
        default:
          throw new RuntimeException("Unknown asset code");
      }
      mAuthorize = op.getauthorize();
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
