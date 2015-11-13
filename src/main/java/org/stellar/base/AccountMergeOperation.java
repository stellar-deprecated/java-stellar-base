package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.Operation.OperationBody;
import org.stellar.base.xdr.OperationType;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#account-merge">AccountMerge</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html">List of Operations</a>
 */
public class AccountMergeOperation extends Operation {

    private final Keypair mDestination;

    private AccountMergeOperation(Keypair destination) {
        mDestination = destination;
    }

    /**
     * The account that receives the remaining XLM balance of the source account.
     * @return
     */
    public Keypair getDestination() {
        return mDestination;
    }

    @Override
    OperationBody toOperationBody() {
        OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
        AccountID destination = new AccountID();
        destination.setAccountID(mDestination.getXdrPublicKey());
        body.setDestination(destination);
        body.setDiscriminant(OperationType.ACCOUNT_MERGE);
        return body;
    }

    /**
     * Builds AccountMerge operation.
     * @see AccountMergeOperation
     */
    public static class Builder {
        private final Keypair mDestination;

        private Keypair mSourceAccount;

        Builder(OperationBody op) {
            mDestination = Keypair.fromXdrPublicKey(op.getDestination().getAccountID());
        }

        /**
         * Creates a new AccountMerge builder.
         * @param destination The account that receives the remaining XLM balance of the source account.
         */
        public Builder(Keypair destination) {
            mDestination = destination;
        }

        /**
         * Set source account of this operation
         * @param sourceAccount Source account
         * @return Builder object so you can chain methods.
         */
        public Builder setSourceAccount(Keypair sourceAccount) {
            mSourceAccount = sourceAccount;
            return this;
        }

        /**
         * Builds an operation
         * @return
         */
        public AccountMergeOperation build() {
            AccountMergeOperation operation = new AccountMergeOperation(mDestination);
            if (mSourceAccount != null) {
                operation.setSourceAccount(mSourceAccount);
            }
            return operation;
        }
    }
}
