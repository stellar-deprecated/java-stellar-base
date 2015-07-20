package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.AllowTrustOp;
import org.stellar.base.xdr.CurrencyType;
import org.stellar.base.xdr.OperationType;

public class AllowTrustOperation extends Operation {

  private final StellarKeypair mTrustor;
  private final String mCurrencyCode;
  private final boolean mAuthorize;

  private AllowTrustOperation(StellarKeypair trustor, String currencyCode, boolean authorize) {
    mTrustor = trustor;
    mCurrencyCode = currencyCode;
    mAuthorize = authorize;
  }

  public StellarKeypair getTrustor() {
    return mTrustor;
  }

  public String getCurrencyCode() {
    return mCurrencyCode;
  }

  public boolean getAuthorize() {
    return mAuthorize;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    AllowTrustOp op = new AllowTrustOp();
    AccountID trustor = new AccountID();
    trustor.setAccountID(mTrustor.getPublicKey());
    op.settrustor(trustor);
    AllowTrustOp.AllowTrustOpCurrency currency = new AllowTrustOp.AllowTrustOpCurrency();
    // TODO: below, we've hardcoded currency_type_alphanum, but we need to add a way for the client
    // to specify what kind of currency type. but right now, it's just alphanum so we simplify.
    currency.setDiscriminant(CurrencyType.CURRENCY_TYPE_ALPHANUM);
    currency.setcurrencyCode(mCurrencyCode.getBytes());
    op.setcurrency(currency);
    op.setauthorize(Boolean.valueOf(mAuthorize));

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.ALLOW_TRUST);
    body.setallowTrustOp(op);
    return body;
  }

  public static class Builder {
    private final StellarKeypair mTrustor;
    private final String mCurrencyCode;
    private final boolean mAuthorize;

    private StellarKeypair mSourceAccount;

    Builder(AllowTrustOp op) {
      mTrustor = StellarKeypair.fromPublicKey(op.gettrustor().getAccountID());
      mCurrencyCode = new String(op.getcurrency().getcurrencyCode());
      mAuthorize = op.getauthorize();
    }

    public Builder(StellarKeypair trustor, String currencyCode, boolean authorize) {
      mTrustor = trustor;
      mCurrencyCode = currencyCode;
      mAuthorize = authorize;
    }

    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

    public AllowTrustOperation build() {
      AllowTrustOperation operation = new AllowTrustOperation(mTrustor, mCurrencyCode, mAuthorize);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
