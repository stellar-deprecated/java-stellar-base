package org.stellar.base;

/**
 * Represents an account on the network, storing the accounts master keypair and sequence number.
 */
public class Account {
  private final StellarKeypair mKeypair;
  private long mSequenceNumber;

  public Account(StellarKeypair keypair, long sequenceNumber) {
    mKeypair = keypair;
    mSequenceNumber = sequenceNumber;
  }

  public StellarKeypair getKeypair() {
    return mKeypair;
  }

  public long getSequenceNumber() {
    return mSequenceNumber;
  }

  public void incrementSequenceNumber() {
    mSequenceNumber++;
  }
}
