package org.stellar.base;

/**
 * Created by andrewrogers on 7/1/15.
 */
public abstract class Currency {

  static Currency fromXdr(org.stellar.base.xdr.Currency xdr) {
    switch (xdr.getDiscriminant()) {
      case CURRENCY_TYPE_NATIVE:
        return new NativeCurrency();
      case CURRENCY_TYPE_ALPHANUM:
        String currencyCode = new String(xdr.getalphaNum().getcurrencyCode());
        StellarKeypair issuer = StellarKeypair.fromPublicKey(xdr.getalphaNum().getissuer().getAccountID());
        return new AlphaNumCurrency(currencyCode, issuer);
      default:
        throw new IllegalArgumentException("Unknown currency type " + xdr.getDiscriminant());
    }
  }

  abstract org.stellar.base.xdr.Currency toXdr();
}
