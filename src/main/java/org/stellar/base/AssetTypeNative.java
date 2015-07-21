package org.stellar.base;

import org.stellar.base.xdr.AssetType;

public class AssetTypeNative extends Asset {

  public AssetTypeNative() {}

  @Override
  public org.stellar.base.xdr.Asset toXdr() {
    org.stellar.base.xdr.Asset xdr = new org.stellar.base.xdr.Asset();
    xdr.setDiscriminant(AssetType.ASSET_TYPE_NATIVE);
    return xdr;
  }
}
