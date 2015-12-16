package org.stellar.base;

/**
 * Specifies interface for Account object used in {@link org.stellar.base.Transaction.Builder}
 */
public interface TransactionBuilderAccount {
  /**
   * Returns keypair associated with this Account
   */
  KeyPair getKeypair();

  /**
   * Returns current sequence number ot this Account.
   */
  Long getSequenceNumber();

  /**
   * Increments sequence number in this object by one.
   */
  void incrementSequenceNumber();
}
