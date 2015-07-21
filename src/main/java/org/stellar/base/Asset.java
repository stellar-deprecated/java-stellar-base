package org.stellar.base;

/**
 * Base Asset class.
 */
public abstract class Asset {
  static Asset fromXdr(org.stellar.base.xdr.Asset xdr) {
    switch (xdr.getDiscriminant()) {
      case ASSET_TYPE_NATIVE:
        return new AssetTypeNative();
      case ASSET_TYPE_CREDIT_ALPHANUM4:
        String currencyCode4 = new String(xdr.getalphaNum4().getassetCode());
        StellarKeypair issuer4 = StellarKeypair.fromXdrPublicKey(
            xdr.getalphaNum4().getissuer().getAccountID());
        return new AssetTypeCreditAlphaNum4(currencyCode4, issuer4);
      case ASSET_TYPE_CREDIT_ALPHANUM12:
        String currencyCode12 = new String(xdr.getalphaNum12().getassetCode());
        StellarKeypair issuer12 = StellarKeypair.fromXdrPublicKey(xdr.getalphaNum12().getissuer().getAccountID());
        return new AssetTypeCreditAlphaNum12(currencyCode12, issuer12);
      default:
        throw new IllegalArgumentException("Unknown currency type " + xdr.getDiscriminant());
    }
  }

  abstract org.stellar.base.xdr.Asset toXdr();
}
