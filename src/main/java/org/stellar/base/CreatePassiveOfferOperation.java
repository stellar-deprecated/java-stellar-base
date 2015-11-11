package org.stellar.base;

import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.CreatePassiveOfferOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;

import java.math.BigDecimal;

public class CreatePassiveOfferOperation extends Operation {
  private final Asset mSelling;
  private final Asset mBuying;
  private final long mAmount;
  private final String mPrice;

  private CreatePassiveOfferOperation(Asset selling, Asset buying, long amount, String price) {
    mSelling = selling;
    mBuying = buying;
    mAmount = amount;
    mPrice = price;
  }

  public Asset getSelling() {
    return mSelling;
  }

  public Asset getBuying() {
    return mBuying;
  }

  public long getAmount() {
    return mAmount;
  }

  public String getPrice() {
    return mPrice;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    CreatePassiveOfferOp op = new CreatePassiveOfferOp();
    op.setselling(mSelling.toXdr());
    op.setbuying(mBuying.toXdr());
    Int64 amount = new Int64();
    amount.setint64(Long.valueOf(mAmount));
    op.setamount(amount);
    Price price = Price.fromString(mPrice);
    op.setprice(price.toXdr());

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.CREATE_PASSIVE_OFFER);
    body.setcreatePassiveOfferOp(op);

    return body;
  }

  static class Builder {

    private final Asset mSelling;
    private final Asset mBuying;
    private final long mAmount;
    private final String mPrice;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new CreateAccount builder from a CreateAccountOp XDR.
     * @param op {@link CreateAccountOp}
     */
    Builder(CreatePassiveOfferOp op) {
      mSelling = Asset.fromXdr(op.getselling());
      mBuying = Asset.fromXdr(op.getbuying());
      mAmount = op.getamount().getint64().longValue();
      int n = op.getprice().getn().getint32().intValue();
      int d = op.getprice().getd().getint32().intValue();
      mPrice = new BigDecimal(n).divide(new BigDecimal(d)).toString();
    }

    public Builder(Asset selling, Asset buying, long amount, String price) {
      mSelling = selling;
      mBuying = buying;
      mAmount = amount;
      mPrice = price;
    }

    /**
     * Sets the source account for this operation.
     * @param account The operation's source account.
     * @return
     */
    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

    public CreatePassiveOfferOperation build() {
      CreatePassiveOfferOperation operation = new CreatePassiveOfferOperation(mSelling, mBuying, mAmount, mPrice);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
