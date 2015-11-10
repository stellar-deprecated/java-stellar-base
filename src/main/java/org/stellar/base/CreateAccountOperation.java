package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.Int64;
import org.stellar.base.xdr.OperationType;

public class CreateAccountOperation extends Operation {

  private final StellarKeypair mDestination;
  private final long mStartingBalance;

  private StellarKeypair mSourceAccount;

  private CreateAccountOperation(StellarKeypair destination, long startingBalance) {
    mDestination = destination;
    mStartingBalance = startingBalance;
  }

  public long getStartingBalance() {
    return mStartingBalance;
  }

  public StellarKeypair getDestination() {
    return mDestination;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    CreateAccountOp op = new CreateAccountOp();
    AccountID destination = new AccountID();
    destination.setAccountID(mDestination.getXdrPublicKey());
    op.setdestination(destination);
    Int64 startingBalance = new Int64();
    startingBalance.setint64(Long.valueOf(mStartingBalance));
    op.setstartingBalance(startingBalance);

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.CREATE_ACCOUNT);
    body.setcreateAccountOp(op);
    return body;
  }

  static class Builder {
    private final StellarKeypair mDestination;
    private final long mStartingBalance;

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new CreateAccount builder from a CreateAccountOp XDR.
     * @param op {@link CreateAccountOp}
     */
    Builder(CreateAccountOp op) {
      mDestination = StellarKeypair.fromXdrPublicKey(op.getdestination().getAccountID());
      mStartingBalance = op.getstartingBalance().getint64().longValue();
    }

    /**
     * Creates a new CreateAccount builder.
     * @param destination The destination keypair (uses only the public key).
     * @param startingBalance The initial balance to start with.
     */
    public Builder(StellarKeypair destination, long startingBalance) {
      mDestination = destination;
      mStartingBalance = startingBalance;
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

    public CreateAccountOperation build() {
      CreateAccountOperation operation = new CreateAccountOperation(mDestination, mStartingBalance);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
