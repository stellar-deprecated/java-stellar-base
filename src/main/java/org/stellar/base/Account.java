package org.stellar.base;

/**
 * Represents an account in Stellar network with it's sequence number.
 * Account object is required to build a {@link Transaction}.
 * @see org.stellar.base.Transaction.Builder
 */
public class Account {
  private final Keypair mKeypair;
  private long mSequenceNumber;

  /**
   * Class constructor.
   * @param keypair Keypair associated with this Account
   * @param sequenceNumber Current sequence number of the account
   */
  public Account(Keypair keypair, long sequenceNumber) {
    mKeypair = keypair;
    mSequenceNumber = sequenceNumber;
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
  public long getSequenceNumber() {
    return mSequenceNumber;
  }

  /**
   * Increments sequence number in this object by one.
   */
  public void incrementSequenceNumber() {
    mSequenceNumber++;
  }
}
