package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.AssetType;

public class AssetTypeCreditAlphaNum4 extends Asset {

  public final String mAssetCode;
  public final StellarKeypair mIssuer;

  public AssetTypeCreditAlphaNum4(String assetCode, StellarKeypair issuer) throws AssetCodeLengthInvalidException {
    if (assetCode.length() < 1 || assetCode.length() > 4) {
      throw new AssetCodeLengthInvalidException();
    }
    mAssetCode = assetCode;
    mIssuer = issuer;
  }

  public String getAssetCode() {
    return mAssetCode;
  }

  @Override
  public String getCode() {
    return mAssetCode;
  }

  public StellarKeypair getIssuer() {
    return mIssuer;
  }

  @Override
  org.stellar.base.xdr.Asset toXdr() {
    org.stellar.base.xdr.Asset xdr = new org.stellar.base.xdr.Asset();
    xdr.setDiscriminant(AssetType.ASSET_TYPE_CREDIT_ALPHANUM4);
    org.stellar.base.xdr.Asset.AssetAlphaNum4 credit = new org.stellar.base.xdr.Asset.AssetAlphaNum4();
    credit.setassetCode(Asset.filledByteArray(mAssetCode, 4));
    AccountID accountID = new AccountID();
    accountID.setAccountID(mIssuer.getXdrPublicKey());
    credit.setissuer(accountID);
    xdr.setalphaNum4(credit);
    return xdr;
  }
}
