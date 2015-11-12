package org.stellar.base;

import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.ManageOfferOp;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.Uint64;

import java.math.BigDecimal;

public class ManagerOfferOperation extends Operation {

  private final Asset mSelling;
  private final Asset mBuying;
  private final long mAmount;
  private final String mPrice;
  private final long mOfferId;

  private ManagerOfferOperation(Asset selling, Asset buying, long amount, String price, long offerId) {
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

  public String getPrice() {
    return mPrice;
  }

  public long getOfferId() {
    return mOfferId;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    ManageOfferOp op = new ManageOfferOp();
    op.setSelling(mSelling.toXdr());
    op.setBuying(mBuying.toXdr());
    Int64 amount = new Int64();
    amount.setInt64(Long.valueOf(mAmount));
    op.setAmount(amount);
    Price price = Price.fromString(mPrice);
    op.setPrice(price.toXdr());
    Uint64 offerId = new Uint64();
    offerId.setUint64(Long.valueOf(mOfferId));
    op.setOfferID(offerId);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.MANAGE_OFFER);
    body.setManageOfferOp(op);

    return body;
  }

  static class Builder {

    private final Asset mSelling;
    private final Asset mBuying;
    private final long mAmount;
    private final String mPrice;
    private final long mOfferId;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new CreateAccount builder from a CreateAccountOp XDR.
     * @param op {@link CreateAccountOp}
     */
    Builder(ManageOfferOp op) {
      mSelling = Asset.fromXdr(op.getSelling());
      mBuying = Asset.fromXdr(op.getBuying());
      mAmount = op.getAmount().getInt64().longValue();
      int n = op.getPrice().getN().getInt32().intValue();
      int d = op.getPrice().getD().getInt32().intValue();
      mPrice = new BigDecimal(n).divide(new BigDecimal(d)).toString();
      mOfferId = op.getOfferID().getUint64().longValue();
    }

    public Builder(Asset selling, Asset buying, long amount, String price, long id) {
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
