package org.stellar.base;

import org.stellar.base.xdr.CurrencyType;

public class NativeCurrency extends Currency {

  public NativeCurrency() {}

  @Override
  public org.stellar.base.xdr.Currency toXdr() {
    org.stellar.base.xdr.Currency xdr = new org.stellar.base.xdr.Currency();
    xdr.setDiscriminant(CurrencyType.CURRENCY_TYPE_NATIVE);
    return xdr;
  }
}
