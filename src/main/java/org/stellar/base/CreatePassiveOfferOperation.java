package org.stellar.base;

import org.stellar.base.xdr.CreateAccountOp;
import org.stellar.base.xdr.CreatePassiveOfferOp;

/**
 * Created by andrewrogers on 7/21/15.
 */
public class CreatePassiveOfferOperation extends Operation {
  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    return null;
  }

  static class Builder {

    private StellarKeypair mSourceAccount;

    /**
     * Construct a new CreateAccount builder from a CreateAccountOp XDR.
     * @param op {@link CreateAccountOp}
     */
    Builder(CreatePassiveOfferOp op) {
    }

    public Builder() {
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
      CreatePassiveOfferOperation operation = new CreatePassiveOfferOperation();
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
