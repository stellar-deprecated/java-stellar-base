package org.stellar.base;

import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.ManageOfferOp;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.Uint64;

import java.math.BigDecimal;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#manage-offer" target="_blank">ManageOffer</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html" target="_blank">List of Operations</a>
 */
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

  /**
   * The ID of the offer.
   * @return
   */
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

  /**
   * Builds ManageOffer operation.
   * @see ManagerOfferOperation
   */
  public static class Builder {

    private final Asset mSelling;
    private final Asset mBuying;
    private final long mAmount;
    private final String mPrice;
    private long mOfferId = 0;

    private Keypair mSourceAccount;

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

    /**
     * Creates a new ManageOffer builder.
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
     * Sets offer ID. <code>0</code> creates a new offer. Set to existing offer ID to change it.
     * @param offerId
     */
    public Builder setOfferId(long offerId) {
      mOfferId = offerId;
      return this;
    }

    /**
     * Sets the source account for this operation.
     * @param account The operation's source account.
     * @return
     */
    public Builder setSourceAccount(Keypair account) {
      mSourceAccount = account;
      return this;
    }

    /**
     * Builds an operation
     * @return
     */
    public ManagerOfferOperation build() {
      ManagerOfferOperation operation = new ManagerOfferOperation(mSelling, mBuying, mAmount, mPrice, mOfferId);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
