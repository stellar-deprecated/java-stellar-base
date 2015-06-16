// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct AllowTrustOp
//  {
//      AccountID trustor;
//      union switch (CurrencyType type)
//      {
//      // CURRENCY_TYPE_NATIVE is not allowed
//      case CURRENCY_TYPE_ALPHANUM:
//          opaque currencyCode[4];
//  
//          // add other currency types here in the future
//      }
//      currency;
//  
//      bool authorize;
//  };

//  ===========================================================================
public class AllowTrustOp  {
  public AllowTrustOp () {}
  private AccountID trustor;
  public AccountID gettrustor() {
    return this.trustor;
  }
  public void settrustor(AccountID value) {
    this.trustor = value;
  }
  private AllowTrustOpCurrency currency;
  public AllowTrustOpCurrency getcurrency() {
    return this.currency;
  }
  public void setcurrency(AllowTrustOpCurrency value) {
    this.currency = value;
  }
  private Boolean authorize;
  public Boolean getauthorize() {
    return this.authorize;
  }
  public void setauthorize(Boolean value) {
    this.authorize = value;
  }
  public static void encode(XdrDataOutputStream stream, AllowTrustOp encodedAllowTrustOp) throws IOException{
    AccountID.encode(stream, encodedAllowTrustOp.trustor);
    AllowTrustOpCurrency.encode(stream, encodedAllowTrustOp.currency);
    stream.writeBoolean(encodedAllowTrustOp.authorize);
  }
  public static AllowTrustOp decode(XdrDataInputStream stream) throws IOException {
    AllowTrustOp decodedAllowTrustOp = new AllowTrustOp();
    decodedAllowTrustOp.trustor = AccountID.decode(stream);
    decodedAllowTrustOp.currency = AllowTrustOpCurrency.decode(stream);
    decodedAllowTrustOp.authorize = stream.readBoolean();
    return decodedAllowTrustOp;
  }

  public static class AllowTrustOpCurrency {
    public AllowTrustOpCurrency () {}
    CurrencyType type;
    public CurrencyType getDiscriminant() {
      return this.type;
    }
    public void setDiscriminant(CurrencyType value) {
      this.type = value;
    }
    private byte[] currencyCode;
    public byte[] getcurrencyCode() {
      return this.currencyCode;
    }
    public void setcurrencyCode(byte[] value) {
      this.currencyCode = value;
    }
    public static void encode(XdrDataOutputStream stream, AllowTrustOpCurrency encodedAllowTrustOpCurrency) throws IOException {
      switch (encodedAllowTrustOpCurrency.getDiscriminant()) {
    case CURRENCY_TYPE_ALPHANUM:
    int currencyCodesize = encodedAllowTrustOpCurrency.currencyCode.length;
    stream.write(encodedAllowTrustOpCurrency.getcurrencyCode(), 0, currencyCodesize);
    break;
    }
    }
    public static AllowTrustOpCurrency decode(XdrDataInputStream stream) throws IOException {
      AllowTrustOpCurrency decodedAllowTrustOpCurrency = new AllowTrustOpCurrency();
      switch (decodedAllowTrustOpCurrency.getDiscriminant()) {
    case CURRENCY_TYPE_ALPHANUM:
    int currencyCodesize = 4;
    decodedAllowTrustOpCurrency.currencyCode = new byte[currencyCodesize];
    stream.read(decodedAllowTrustOpCurrency.currencyCode, 0, currencyCodesize);
    break;
    }
      return decodedAllowTrustOpCurrency;
    }

  }
}
