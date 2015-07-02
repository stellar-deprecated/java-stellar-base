package org.stellar.base;

/**
 * Represents an account on the network, storing the accounts master keypair and sequence number.
 */
public class Account {
  private final StellarKeypair mMasterKeypair;

  private int mSequenceNumber;

  public Account(StellarKeypair masterKeypair, int sequenceNumber) {
    mMasterKeypair = masterKeypair;
    mSequenceNumber = sequenceNumber;
  }
}
