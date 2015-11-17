package org.stellar.base;

import org.stellar.base.xdr.AssetType;

/**
 * Represents Stellar native asset - <a href="https://www.stellar.org/developers/learn/concepts/assets.html" target="_blank">lumens (XLM)</a>
 * @see <a href="https://www.stellar.org/developers/learn/concepts/assets.html" target="_blank">Assets</a>
 */
public class AssetTypeNative extends Asset {

  public AssetTypeNative() {}

  @Override
  public boolean equals(Object object) {
    return this.getClass().equals(object.getClass());
  }

  @Override
  public org.stellar.base.xdr.Asset toXdr() {
    org.stellar.base.xdr.Asset xdr = new org.stellar.base.xdr.Asset();
    xdr.setDiscriminant(AssetType.ASSET_TYPE_NATIVE);
    return xdr;
  }
}
