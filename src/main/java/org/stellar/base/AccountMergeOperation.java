package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.Operation.OperationBody;

public class AccountMergeOperation extends Operation {

    private final StellarKeypair mDestination;

    private AccountMergeOperation(StellarKeypair destination) {
        mDestination = destination;
    }

    public StellarKeypair getDestination() {
        return mDestination;
    }

    @Override
    OperationBody toOperationBody() {
        OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
        AccountID destination = new AccountID();
        destination.setAccountID(mDestination.getXdrPublicKey());
        body.setdestination(destination);
        body.setDiscriminant(OperationType.ACCOUNT_MERGE);
        return body;
    }

    static class Builder {
        private final StellarKeypair mDestination;

        private StellarKeypair mSourceAccount;

        Builder(OperationBody op) {
            mDestination = StellarKeypair.fromXdrPublicKey(op.getdestination().getAccountID());
        }

        public Builder(StellarKeypair destination) {
            mDestination = destination;
        }

        public Builder setSourceAccount(StellarKeypair account) {
            mSourceAccount = account;
            return this;
        }

        public AccountMergeOperation build() {
            AccountMergeOperation operation = new AccountMergeOperation(mDestination);
            if (mSourceAccount != null) {
                operation.setSourceAccount(mSourceAccount);
            }
            return operation;
        }
    }
}
