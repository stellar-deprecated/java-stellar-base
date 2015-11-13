package org.stellar.base;

import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.CreatePassiveOfferOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;

import java.math.BigDecimal;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#create-passive-offer">CreatePassiveOffer</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html">List of Operations</a>
 */
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

  /**
   * The asset being sold in this operation
   * @return
   */
  public Asset getSelling() {
    return mSelling;
  }

  /**
   * The asset being bought in this operation
   * @return
   */
  public Asset getBuying() {
    return mBuying;
  }

  /**
   * Amount of selling being sold.
   * @return
   */
  public long getAmount() {
    return mAmount;
  }

  /**
   * Price of 1 unit of selling in terms of buying.
   * @return
   */
  public String getPrice() {
    return mPrice;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    CreatePassiveOfferOp op = new CreatePassiveOfferOp();
    op.setSelling(mSelling.toXdr());
    op.setBuying(mBuying.toXdr());
    Int64 amount = new Int64();
    amount.setInt64(Long.valueOf(mAmount));
    op.setAmount(amount);
    Price price = Price.fromString(mPrice);
    op.setPrice(price.toXdr());

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.CREATE_PASSIVE_OFFER);
    body.setCreatePassiveOfferOp(op);

    return body;
  }

  /**
   * Builds CreatePassiveOffer operation.
   * @see CreatePassiveOfferOperation
   */
  public static class Builder {

    private final Asset mSelling;
    private final Asset mBuying;
    private final long mAmount;
    private final String mPrice;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new CreatePassiveOffer builder from a CreatePassiveOfferOp XDR.
     * @param op
     */
    Builder(CreatePassiveOfferOp op) {
      mSelling = Asset.fromXdr(op.getSelling());
      mBuying = Asset.fromXdr(op.getBuying());
      mAmount = op.getAmount().getInt64().longValue();
      int n = op.getPrice().getN().getInt32().intValue();
      int d = op.getPrice().getD().getInt32().intValue();
      mPrice = new BigDecimal(n).divide(new BigDecimal(d)).toString();
    }

    /**
     * Creates a new CreatePassiveOffer builder.
     * @param selling The asset being sold in this operation
     * @param buying The asset being bought in this operation
     * @param amount Amount of selling being sold.
     * @param price Price of 1 unit of selling in terms of buying.
     */
    public Builder(Asset selling, Asset buying, long amount, String price) {
      mSelling = selling;
      mBuying = buying;
      mAmount = amount;
      mPrice = price;
    }

    /**
     * Sets the source account for this operation.
     * @param sourceAccount The operation's source account.
     * @return
     */
    public Builder setSourceAccount(StellarKeypair sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /**
     * Builds an operation
     * @return
     */
    public CreatePassiveOfferOperation build() {
      CreatePassiveOfferOperation operation = new CreatePassiveOfferOperation(mSelling, mBuying, mAmount, mPrice);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
