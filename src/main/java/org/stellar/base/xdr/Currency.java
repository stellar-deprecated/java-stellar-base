// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union Currency switch (CurrencyType type)
//  {
//  case NATIVE:
//      void;
//  
//  case ISO4217:
//      ISOCurrencyIssuer isoCI;
//  
//      // add other currency types here in the future
//  };

//  ===========================================================================
public class Currency  {
  public Currency () {}
  CurrencyType type;
  public CurrencyType getDiscriminant() {
    return this.type;
  }
  public void setDiscriminant(CurrencyType value) {
    this.type = value;
  }
  private ISOCurrencyIssuer isoCI;
  public ISOCurrencyIssuer getisoCI() {
    return this.isoCI;
  }
  public void setisoCI(ISOCurrencyIssuer value) {
    this.isoCI = value;
  }
  public static void encode(XdrDataOutputStream stream, Currency encodedCurrency) throws IOException {
    switch (encodedCurrency.getDiscriminant()) {
  case NATIVE:
  break;
  case ISO4217:
  ISOCurrencyIssuer.encode(stream, encodedCurrency.isoCI);
  break;
  }
  }
  public static Currency decode(XdrDataInputStream stream) throws IOException {
    Currency decodedCurrency = new Currency();
    switch (decodedCurrency.getDiscriminant()) {
  case NATIVE:
  break;
  case ISO4217:
  decodedCurrency.isoCI = ISOCurrencyIssuer.decode(stream);
  break;
  }
    return decodedCurrency;
  }
}
