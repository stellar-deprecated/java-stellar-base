package org.stellar.base;

/**
 * Base Asset class.
 */
public abstract class Asset {
  /**
   * Creates one of AssetTypeCreditAlphaNum4 or AssetTypeCreditAlphaNum12 object based on a <code>assetCode</code> length
   * @param assetCode
   * @param issuer
   * @return
   */
  static Asset createNonNativeAsset(String assetCode, StellarKeypair issuer) {
    if (assetCode.length() >= 1 && assetCode.length() <= 4) {
      return new AssetTypeCreditAlphaNum4(assetCode, issuer);
    } else if (assetCode.length() >= 5 && assetCode.length() <= 12) {
      return new AssetTypeCreditAlphaNum12(assetCode, issuer);
    } else {
      throw new AssetCodeLengthInvalidException();
    }
  }

  /**
   * Generates Asset object from a given XDR object
   * @param xdr
   * @return
   */
  static Asset fromXdr(org.stellar.base.xdr.Asset xdr) {
    switch (xdr.getDiscriminant()) {
      case ASSET_TYPE_NATIVE:
        return new AssetTypeNative();
      case ASSET_TYPE_CREDIT_ALPHANUM4:
        String assetCode4 = Util.paddedByteArrayToString(xdr.getAlphaNum4().getAssetCode());
        StellarKeypair issuer4 = StellarKeypair.fromXdrPublicKey(
            xdr.getAlphaNum4().getIssuer().getAccountID());
        return new AssetTypeCreditAlphaNum4(assetCode4, issuer4);
      case ASSET_TYPE_CREDIT_ALPHANUM12:
        String assetCode12 = Util.paddedByteArrayToString(xdr.getAlphaNum12().getAssetCode());
        StellarKeypair issuer12 = StellarKeypair.fromXdrPublicKey(xdr.getAlphaNum12().getIssuer().getAccountID());
        return new AssetTypeCreditAlphaNum12(assetCode12, issuer12);
      default:
        throw new IllegalArgumentException("Unknown asset type " + xdr.getDiscriminant());
    }
  }

  @Override
  public abstract boolean equals(Object object);

  /**
   * Generates XDR object from a given Asset object
   * @return
   */
  abstract org.stellar.base.xdr.Asset toXdr();
}
