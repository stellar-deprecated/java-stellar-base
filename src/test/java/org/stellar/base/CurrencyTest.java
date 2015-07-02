package org.stellar.base;

import junit.framework.TestCase;
import org.junit.Test;

/**
 * Created by andrewrogers on 7/1/15.
 */
public class CurrencyTest extends TestCase {

  @Test
  public void testNativeCurrency() {
    NativeCurrency currency = new NativeCurrency();
    org.stellar.base.xdr.Currency xdr = currency.toXdr();
    Currency parsedCurrency = Currency.fromXdr(xdr);
    assertTrue(parsedCurrency instanceof NativeCurrency);
  }

  @Test
  public void testAlphaNumCurrency() {
    String code = "USD";
    StellarKeypair issuer = StellarKeypair.random();
    AlphaNumCurrency currency = new AlphaNumCurrency(code, issuer);
    org.stellar.base.xdr.Currency xdr = currency.toXdr();
    AlphaNumCurrency parsedCurrency = (AlphaNumCurrency) Currency.fromXdr(xdr);
    assertEquals(code, currency.getCurrencyCode());
    assertEquals(issuer.getAddress(), parsedCurrency.getIssuer().getAddress());
  }
}
