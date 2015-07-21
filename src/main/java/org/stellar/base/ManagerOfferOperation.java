package org.stellar.base;

import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.Int32;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.ManageOfferOp;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.Price;
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
    RationalApproximation rationalApproximation = toRationalApproximation(mPrice);
    Price price = new Price();
    Int32 n = new Int32();
    n.setint32((int) rationalApproximation.a);
    Int32 d = new Int32();
    d.setint32((int) rationalApproximation.b);
    price.setn(n);
    price.setd(d);
    op.setprice(price);
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
    private final float mPrice;
    private final long mOfferId;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new CreateAccount builder from a CreateAccountOp XDR.
     * @param op {@link CreateAccountOp}
     */
    Builder(ManageOfferOp op) {
      mSelling = Asset.fromXdr(op.getselling());
      mBuying = Asset.fromXdr(op.getbuying());
      mAmount = op.getamount().getint64().longValue();
      float n = op.getprice().getn().getint32().floatValue();
      float d = op.getprice().getd().getint32().floatValue();
      mPrice = (long) n / d;
      mOfferId = op.getofferID().getuint64().longValue();
    }

    public Builder(Asset selling, Asset buying, long amount, float price, long id) {
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

  // from http://stackoverflow.com/questions/13222664/convert-floating-point-number-into-a-rational-number-in-java
  static RationalApproximation toRationalApproximation(double price) {
    long bits = Double.doubleToLongBits(price);

    long sign = bits >>> 63;
    long exponent = ((bits >>> 52) ^ (sign << 11)) - 1023;
    long fraction = bits << 12; // bits are "reversed" but that's not a problem

    long a = 1L;
    long b = 1L;

    for (int i = 63; i >= 12; i--) {
      long tempA = a * 2 + ((fraction >>> i) & 1);
      if (tempA >= Integer.MAX_VALUE) {
        break;
      }
      a = tempA;
      b *= 2;
    }

    if (exponent > 0)
      a *= 1 << exponent;
    else
      b *= 1 << -exponent;

    if (sign == 1)
      a *= -1;

    return new RationalApproximation(a, b);
  }

  static class RationalApproximation {
    private final long a;
    private final long b;

    RationalApproximation(long a, long b) {
      this.a = a;
      this.b = b;
    }
  }
}
