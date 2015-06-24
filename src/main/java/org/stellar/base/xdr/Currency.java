// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union Currency switch (CurrencyType type)
//  {
//  case CURRENCY_TYPE_NATIVE:
//      void;
//  
//  case CURRENCY_TYPE_ALPHANUM:
//      struct
//      {
//          opaque currencyCode[4];
//          AccountID issuer;
//      } alphaNum;
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
  private CurrencyAlphaNum alphaNum;
  public CurrencyAlphaNum getalphaNum() {
    return this.alphaNum;
  }
  public void setalphaNum(CurrencyAlphaNum value) {
    this.alphaNum = value;
  }
  public static void encode(XdrDataOutputStream stream, Currency encodedCurrency) throws IOException {
    stream.writeInt(encodedCurrency.getDiscriminant().getValue());
    switch (encodedCurrency.getDiscriminant()) {
  case CURRENCY_TYPE_NATIVE:
  break;
  case CURRENCY_TYPE_ALPHANUM:
  CurrencyAlphaNum.encode(stream, encodedCurrency.alphaNum);
  break;
  }
  }
  public static Currency decode(XdrDataInputStream stream) throws IOException {
    Currency decodedCurrency = new Currency();
    switch (decodedCurrency.getDiscriminant()) {
  case CURRENCY_TYPE_NATIVE:
  break;
  case CURRENCY_TYPE_ALPHANUM:
  decodedCurrency.alphaNum = CurrencyAlphaNum.decode(stream);
  break;
  }
    return decodedCurrency;
  }

  public static class CurrencyAlphaNum {
    public CurrencyAlphaNum () {}
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
    public static void encode(XdrDataOutputStream stream, CurrencyAlphaNum encodedCurrencyAlphaNum) throws IOException{
      int currencyCodesize = encodedCurrencyAlphaNum.currencyCode.length;
      stream.write(encodedCurrencyAlphaNum.getcurrencyCode(), 0, currencyCodesize);
      AccountID.encode(stream, encodedCurrencyAlphaNum.issuer);
    }
    public static CurrencyAlphaNum decode(XdrDataInputStream stream) throws IOException {
      CurrencyAlphaNum decodedCurrencyAlphaNum = new CurrencyAlphaNum();
      int currencyCodesize = 4;
      decodedCurrencyAlphaNum.currencyCode = new byte[currencyCodesize];
      stream.read(decodedCurrencyAlphaNum.currencyCode, 0, currencyCodesize);
      decodedCurrencyAlphaNum.issuer = AccountID.decode(stream);
      return decodedCurrencyAlphaNum;
    }

  }
}
