package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.AssetType;

public class AssetTypeCreditAlphaNum12 extends Asset {

  public final String mAssetCode;
  public final StellarKeypair mIssuer;

  public AssetTypeCreditAlphaNum12(String assetCode, StellarKeypair issuer) throws AssetCodeLengthInvalidException {
    if (assetCode.length() < 5 || assetCode.length() > 12) {
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
    xdr.setDiscriminant(AssetType.ASSET_TYPE_CREDIT_ALPHANUM12);
    org.stellar.base.xdr.Asset.AssetAlphaNum12 credit = new org.stellar.base.xdr.Asset.AssetAlphaNum12();
    credit.setassetCode(Asset.filledByteArray(mAssetCode, 12));
    AccountID accountID = new AccountID();
    accountID.setAccountID(mIssuer.getXdrPublicKey());
    credit.setissuer(accountID);
    xdr.setalphaNum12(credit);
    return xdr;
  }
}
