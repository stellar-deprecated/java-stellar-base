// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct ISOCurrencyIssuer
//  {
//      opaque currencyCode[4];
//      AccountID issuer;
//  };

//  ===========================================================================
public class ISOCurrencyIssuer  {
  public ISOCurrencyIssuer () {}
  private byte[] currencyCode;
  public byte[] getcurrencyCode() {
    return this.currencyCode;
  }
  public void setcurrencyCode(byte[] value) {
    this.currencyCode = value;
  }
  private AccountID issuer;
  public AccountID getissuer() {
    return this.issuer;
  }
  public void setissuer(AccountID value) {
    this.issuer = value;
  }
  public static void encode(XdrDataOutputStream stream, ISOCurrencyIssuer encodedISOCurrencyIssuer) throws IOException{
    int currencyCodesize = encodedISOCurrencyIssuer.currencyCode.length;
    stream.write(encodedISOCurrencyIssuer.getcurrencyCode(), 0, currencyCodesize);
    AccountID.encode(stream, encodedISOCurrencyIssuer.issuer);
  }
  public static ISOCurrencyIssuer decode(XdrDataInputStream stream) throws IOException {
    ISOCurrencyIssuer decodedISOCurrencyIssuer = new ISOCurrencyIssuer();
    int currencyCodesize = 4;
    decodedISOCurrencyIssuer.currencyCode = new byte[currencyCodesize];
    stream.read(decodedISOCurrencyIssuer.currencyCode, 0, currencyCodesize);
    decodedISOCurrencyIssuer.issuer = AccountID.decode(stream);
    return decodedISOCurrencyIssuer;
  }
}
