package org.stellar.base;

import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.ManageOfferOp;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.Uint64;

public class ManagerOfferOperation extends Operation {

  private final Asset mSelling;
  private final Asset mBuying;
  private final long mAmount;
  private final double mPrice;
  private final long mOfferId;

  private ManagerOfferOperation(Asset selling, Asset buying, long amount, double price, long offerId) {
    mSelling = selling;
    mBuying = buying;
    mAmount = amount;
    mPrice = price;
    mOfferId = offerId;
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

  public double getPrice() {
    return mPrice;
  }

  public long getOfferId() {
    return mOfferId;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    ManageOfferOp op = new ManageOfferOp();
    op.setselling(mSelling.toXdr());
    op.setbuying(mBuying.toXdr());
    Int64 amount = new Int64();
    amount.setint64(Long.valueOf(mAmount));
    op.setamount(amount);
    Price price = Price.rationalApproximation(mPrice);
    op.setprice(price.toXdr());
    Uint64 offerId = new Uint64();
    offerId.setuint64(Long.valueOf(mOfferId));
    op.setofferID(offerId);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.MANAGE_OFFER);
    body.setmanageOfferOp(op);

    return body;
  }

  static class Builder {

    private final Asset mSelling;
    private final Asset mBuying;
    private final long mAmount;
    private final double mPrice;
    private final long mOfferId;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new CreateAccount builder from a CreateAccountOp XDR.
     * @param op {@link CreateAccountOp}
     */
    Builder(ManageOfferOp op) throws AssetCodeLengthInvalidException {
      mSelling = Asset.fromXdr(op.getselling());
      mBuying = Asset.fromXdr(op.getbuying());
      mAmount = op.getamount().getint64().longValue();
      int n = op.getprice().getn().getint32().intValue();
      int d = op.getprice().getd().getint32().intValue();
      mPrice = (double) n / d;
      mOfferId = op.getofferID().getuint64().longValue();
    }

    public Builder(Asset selling, Asset buying, long amount, double price, long id) {
      mSelling = selling;
      mBuying = buying;
      mAmount = amount;
      mPrice = price;
      mOfferId = id;
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

    public ManagerOfferOperation build() {
      ManagerOfferOperation operation = new ManagerOfferOperation(mSelling, mBuying, mAmount, mPrice,
          mOfferId);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
