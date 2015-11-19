package org.stellar.base;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * Represents an account in Stellar network with it's sequence number.
 * Account object is required to build a {@link Transaction}.
 * @see org.stellar.base.Transaction.Builder
 */
public class Account implements TransactionBuilderAccount {
  private final Keypair mKeypair;
  private Long mSequenceNumber;

  /**
   * Class constructor.
   * @param keypair Keypair associated with this Account
   * @param sequenceNumber Current sequence number of the account
   */
  public Account(Keypair keypair, Long sequenceNumber) {
    mKeypair = checkNotNull(keypair, "keypair cannot be null");
    mSequenceNumber = checkNotNull(sequenceNumber, "sequenceNumber cannot be null");
  }

  /**
   * Returns keypair associated with this Account
   */
  public Keypair getKeypair() {
    return mKeypair;
  }

  /**
   * Returns current sequence number ot this Account.
   */
  public Long getSequenceNumber() {
    return mSequenceNumber;
  }

  /**
   * Increments sequence number in this object by one.
   */
  public void incrementSequenceNumber() {
    mSequenceNumber++;
  }
}
