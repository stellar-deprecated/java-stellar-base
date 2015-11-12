package org.stellar.base;

import java.util.Arrays;

/**
 * Base Asset class.
 */
public abstract class Asset {
  static Asset createNonNativeAsset(String assetCode, StellarKeypair issuer) {
    if (assetCode.length() >= 1 && assetCode.length() <= 4) {
      return new AssetTypeCreditAlphaNum4(assetCode, issuer);
    } else if (assetCode.length() >= 5 && assetCode.length() <= 12) {
      return new AssetTypeCreditAlphaNum12(assetCode, issuer);
    } else {
      throw new AssetCodeLengthInvalidException();
    }
  }

  static String paddedByteArrayToString(byte[] bytes) {
    return new String(bytes).split("\0")[0];
  }

  static byte[] filledByteArray(String assetCode, int length) {
    byte[] assetCodeBytes = new byte[length];
    Arrays.fill(assetCodeBytes, (byte) 0);
    System.arraycopy(assetCode.getBytes(), 0, assetCodeBytes, 0, assetCode.length());
    return assetCodeBytes;
  }

  static Asset fromXdr(org.stellar.base.xdr.Asset xdr) {
    switch (xdr.getDiscriminant()) {
      case ASSET_TYPE_NATIVE:
        return new AssetTypeNative();
      case ASSET_TYPE_CREDIT_ALPHANUM4:
        String assetCode4 = Asset.paddedByteArrayToString(xdr.getAlphaNum4().getAssetCode());
        StellarKeypair issuer4 = StellarKeypair.fromXdrPublicKey(
            xdr.getAlphaNum4().getIssuer().getAccountID());
        return new AssetTypeCreditAlphaNum4(assetCode4, issuer4);
      case ASSET_TYPE_CREDIT_ALPHANUM12:
        String assetCode12 = Asset.paddedByteArrayToString(xdr.getAlphaNum12().getAssetCode());
        StellarKeypair issuer12 = StellarKeypair.fromXdrPublicKey(xdr.getAlphaNum12().getIssuer().getAccountID());
        return new AssetTypeCreditAlphaNum12(assetCode12, issuer12);
      default:
        throw new IllegalArgumentException("Unknown asset type " + xdr.getDiscriminant());
    }
  }

  @Override
  public abstract boolean equals(Object object);
  abstract org.stellar.base.xdr.Asset toXdr();
}
