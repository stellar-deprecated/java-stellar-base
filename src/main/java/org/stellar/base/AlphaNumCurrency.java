package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.CurrencyType;

public class AlphaNumCurrency extends Currency {

  public final String mCurrencyCode;
  public final StellarKeypair mIssuer;

  public AlphaNumCurrency(String currencyCode, StellarKeypair issuer) {
    mCurrencyCode = currencyCode;
    mIssuer = issuer;
  }

  public String getCurrencyCode() {
    return mCurrencyCode;
  }

  public StellarKeypair getIssuer() {
    return mIssuer;
  }

  @Override
  org.stellar.base.xdr.Currency toXdr() {
    org.stellar.base.xdr.Currency xdr = new org.stellar.base.xdr.Currency();
    xdr.setDiscriminant(CurrencyType.CURRENCY_TYPE_ALPHANUM);
    org.stellar.base.xdr.Currency.CurrencyAlphaNum alhpaNum = new org.stellar.base.xdr.Currency.CurrencyAlphaNum();
    alhpaNum.setcurrencyCode(mCurrencyCode.getBytes());
    AccountID accountID = new AccountID();
    accountID.setAccountID(mIssuer.getPublicKey());
    alhpaNum.setissuer(accountID);
    xdr.setalphaNum(alhpaNum);
    return xdr;
  }
}
