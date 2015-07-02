package org.stellar.base;

import org.stellar.base.xdr.Operation;

public class Transaction {

  /**
   * Builder pattern to create new transactions.
   */
  public class Builder {
    private final Account mSourceAccount;

    /**
     * Construct a new transaction builder.
     * @param sourceAccount The source account for this transaction. This account is the account
     * who will use a sequence number. When build() is called, the account object's sequence number
     * will be incremented.
     */
    public Builder(Account sourceAccount) {
      mSourceAccount = sourceAccount;
    }

    public Builder addOperation(Operation operation) {
      return this;
    };
  }
}
