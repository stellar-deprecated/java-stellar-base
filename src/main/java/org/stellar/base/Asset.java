package org.stellar.base;

/**
 * Base Asset class.
 */
public abstract class Asset {
  /**
   * Creates one of AssetTypeCreditAlphaNum4 or AssetTypeCreditAlphaNum12 object based on a <code>code</code> length
   * @param code Asset code
   * @param issuer Asset issuer
   * @return
   */
  public static Asset createNonNativeAsset(String code, Keypair issuer) {
    if (code.length() >= 1 && code.length() <= 4) {
      return new AssetTypeCreditAlphaNum4(code, issuer);
    } else if (code.length() >= 5 && code.length() <= 12) {
      return new AssetTypeCreditAlphaNum12(code, issuer);
    } else {
      throw new AssetCodeLengthInvalidException();
    }
  }

  /**
   * Generates Asset object from a given XDR object
   * @param xdr XDR object
   * @return
   */
  public static Asset fromXdr(org.stellar.base.xdr.Asset xdr) {
    switch (xdr.getDiscriminant()) {
      case ASSET_TYPE_NATIVE:
        return new AssetTypeNative();
      case ASSET_TYPE_CREDIT_ALPHANUM4:
        String assetCode4 = Util.paddedByteArrayToString(xdr.getAlphaNum4().getAssetCode());
        Keypair issuer4 = Keypair.fromXdrPublicKey(
                xdr.getAlphaNum4().getIssuer().getAccountID());
        return new AssetTypeCreditAlphaNum4(assetCode4, issuer4);
      case ASSET_TYPE_CREDIT_ALPHANUM12:
        String assetCode12 = Util.paddedByteArrayToString(xdr.getAlphaNum12().getAssetCode());
        Keypair issuer12 = Keypair.fromXdrPublicKey(xdr.getAlphaNum12().getIssuer().getAccountID());
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
  public abstract org.stellar.base.xdr.Asset toXdr();
}
