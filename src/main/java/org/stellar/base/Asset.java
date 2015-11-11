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

  public abstract String getCode();
  public abstract StellarKeypair getIssuer();

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
        String assetCode4 = Asset.paddedByteArrayToString(xdr.getalphaNum4().getassetCode());
        StellarKeypair issuer4 = StellarKeypair.fromXdrPublicKey(
            xdr.getalphaNum4().getissuer().getAccountID());
        return new AssetTypeCreditAlphaNum4(assetCode4, issuer4);
      case ASSET_TYPE_CREDIT_ALPHANUM12:
        String assetCode12 = Asset.paddedByteArrayToString(xdr.getalphaNum12().getassetCode());
        StellarKeypair issuer12 = StellarKeypair.fromXdrPublicKey(xdr.getalphaNum12().getissuer().getAccountID());
        return new AssetTypeCreditAlphaNum12(assetCode12, issuer12);
      default:
        throw new IllegalArgumentException("Unknown asset type " + xdr.getDiscriminant());
    }
  }

  @Override
  public boolean equals(Object object) {
    if (!(object instanceof Asset)) {
      return false;
    }

    Asset asset = (Asset) object;

    if (!this.getCode().equals(asset.getCode())) {
      return false;
    }

    if (this.getIssuer() != null && asset.getIssuer() != null) {
      return this.getIssuer().getAddress().equals(asset.getIssuer().getAddress());
    } else if (this.getIssuer() == null && asset.getIssuer() == null) {
      // Both native
      return true;
    } else {
      return false;
    }
  }

  abstract org.stellar.base.xdr.Asset toXdr();
}
