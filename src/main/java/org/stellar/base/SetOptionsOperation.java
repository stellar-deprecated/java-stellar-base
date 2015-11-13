package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.SetOptionsOp;
import org.stellar.base.xdr.Signer;
import org.stellar.base.xdr.String32;
import org.stellar.base.xdr.Uint32;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#set-options">SetOptions</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html">List of Operations</a>
 */
public class SetOptionsOperation extends Operation {

  private final StellarKeypair mInflationDestination;
  private final int mClearFlags;
  private final int mSetFlags;
  private int mMasterKeyWeight = -1;
  private int mLowThreshold = -1;
  private int mMediumThreshold = -1;
  private int mHighThreshold = -1;
  private final String mHomeDomain;
  private final StellarKeypair mSigner;
  private final byte mSignerWeight;

  private SetOptionsOperation(StellarKeypair inflationDestination, int clearFlags, int setFlags,
      int masterKeyWeight, int lowThreshold, int mediumThreshold, int highThreshold,
      String homeDomain, StellarKeypair signer, byte signerWeight) {
    mInflationDestination = inflationDestination;
    mClearFlags = clearFlags;
    mSetFlags = setFlags;
    mMasterKeyWeight = masterKeyWeight;
    mLowThreshold = lowThreshold;
    mMediumThreshold = mediumThreshold;
    mHighThreshold = highThreshold;
    mHomeDomain = homeDomain;
    mSigner = signer;
    mSignerWeight = signerWeight;
  }

  /**
   * Account of the inflation destination.
   * @return
   */
  public StellarKeypair getInflationDestination() {
    return mInflationDestination;
  }

  /**
   * Indicates which flags to clear. For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html">accounts doc</a>.
   * @return
   */
  public int getClearFlags() {
    return mClearFlags;
  }

  /**
   * Indicates which flags to set. For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html">accounts doc</a>.
   * @return
   */
  public int getSetFlags() {
    return mSetFlags;
  }

  /**
   * Weight of the master key.
   * @return
   */
  public int getMasterKeyWeight() {
    return mMasterKeyWeight;
  }

  /**
   * A number from 0-255 representing the threshold this account sets on all operations it performs that have <a href="https://www.stellar.org/developers/learn/concepts/multi-sig.html">a low threshold</a>.
   * @return
   */
  public int getLowThreshold() {
    return mLowThreshold;
  }

  /**
   * A number from 0-255 representing the threshold this account sets on all operations it performs that have <a href="https://www.stellar.org/developers/learn/concepts/multi-sig.html">a medium threshold</a>.
   * @return
   */
  public int getMediumThreshold() {
    return mMediumThreshold;
  }

  /**
   * A number from 0-255 representing the threshold this account sets on all operations it performs that have <a href="https://www.stellar.org/developers/learn/concepts/multi-sig.html">a high threshold</a>.
   * @return
   */
  public int getHighThreshold() {
    return mHighThreshold;
  }

  /**
   * The home domain of an account.
   * @return
   */
  public String getHomeDomain() {
    return mHomeDomain;
  }

  /**
   * Additional signer added/removed in this operation.
   * @return
   */
  public StellarKeypair getSigner() {
    return mSigner;
  }

  /**
   * Additional signer weight. The signer is deleted if the weight is 0.
   * @return
   */
  public byte getSignerWeight() {
    return mSignerWeight;
  }

  @Override
  org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
    SetOptionsOp op = new SetOptionsOp();
    if (mInflationDestination != null) {
      AccountID inflationDestination = new AccountID();
      inflationDestination.setAccountID(mInflationDestination.getXdrPublicKey());
      op.setInflationDest(inflationDestination);
    }
    if (mClearFlags != 0) {
      Uint32 clearFlags = new Uint32();
      clearFlags.setUint32(mClearFlags);
      op.setClearFlags(clearFlags);
    }
    if (mSetFlags != 0) {
      Uint32 setFlags = new Uint32();
      setFlags.setUint32(mSetFlags);
      op.setSetFlags(setFlags);
    }
    if (mMasterKeyWeight != -1) {
      Uint32 uint32 = new Uint32();
      uint32.setUint32(mMasterKeyWeight);
      op.setMasterWeight(uint32);
    }
    if (mLowThreshold != -1) {
      Uint32 uint32 = new Uint32();
      uint32.setUint32(mLowThreshold);
      op.setLowThreshold(uint32);
    }
    if (mMediumThreshold != -1) {
      Uint32 uint32 = new Uint32();
      uint32.setUint32(mMediumThreshold);
      op.setMedThreshold(uint32);
    }
    if (mHighThreshold != -1) {
      Uint32 uint32 = new Uint32();
      uint32.setUint32(mHighThreshold);
      op.setHighThreshold(uint32);
    }
    if (mHomeDomain != null) {
      String32 homeDomain = new String32();
      homeDomain.setString32(mHomeDomain);
      op.setHomeDomain(homeDomain);
    }
    if (mSigner != null) {
      Signer signer = new Signer();
      Uint32 weight = new Uint32();
      weight.setUint32((int) mSignerWeight & 0xFF);
      AccountID accountID = new AccountID();
      accountID.setAccountID(mSigner.getXdrPublicKey());
      signer.setPubKey(accountID);
      signer.setWeight(weight);
      op.setSigner(signer);
    }

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.SET_OPTIONS);
    body.setSetOptionsOp(op);
    return body;
  }

  /**
   * Builds SetOptions operation.
   * @see SetOptionsOperation
   */
  public static class Builder {
    private StellarKeypair mInflationDestination;
    private int mClearFlags;
    private int mSetFlags;
    private int mMasterKeyWeight = -1;
    private int mLowThreshold = -1;
    private int mMediumThreshold = -1;
    private int mHighThreshold = -1;
    private String mHomeDomain;
    private StellarKeypair mSigner;
    private byte mSignerWeight;
    private StellarKeypair mSourceAccount;

    Builder(SetOptionsOp op) {
      if (op.getInflationDest() != null) {
        mInflationDestination = StellarKeypair.fromXdrPublicKey(
            op.getInflationDest().getAccountID());
      }
      if (op.getClearFlags() != null) {
        mClearFlags = op.getClearFlags().getUint32();
      }
      if (op.getSetFlags() != null) {
        mSetFlags = op.getSetFlags().getUint32();
      }
      if (op.getMasterWeight() != null) {
        mMasterKeyWeight = op.getMasterWeight().getUint32().intValue();
      }
      if (op.getLowThreshold() != null) {
        mLowThreshold = op.getLowThreshold().getUint32().intValue();
      }
      if (op.getMedThreshold() != null) {
        mMediumThreshold = op.getMedThreshold().getUint32().intValue();
      }
      if (op.getHighThreshold() != null) {
        mHighThreshold = op.getHighThreshold().getUint32().intValue();
      }
      if (op.getHomeDomain() != null) {
        mHomeDomain = op.getHomeDomain().getString32();
      }
      if (op.getSigner() != null) {
        mSigner = StellarKeypair.fromXdrPublicKey(op.getSigner().getPubKey().getAccountID());
        mSignerWeight = (byte) (op.getSigner().getWeight().getUint32().intValue() & 0xFF);
      }
    }

    /**
     * Creates a new SetOptionsOperation builder.
     */
    public Builder() {}

    /**
     * Sets the inflation destination for the account.
     * @param inflationDestination The inflation destination account.
     */
    public Builder setInflationDestination(StellarKeypair inflationDestination) {
      mInflationDestination = inflationDestination;
      return this;
    }

    /**
     * Clears the given flags from the account.
     * @param clearFlags For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html">accounts doc</a>.
     * @return
     */
    public Builder setClearFlags(int clearFlags) {
      mClearFlags = clearFlags;
      return this;
    }

    /**
     * Sets the given flags on the account.
     * @param setFlags For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html">accounts doc</a>.
     * @return
     */
    public Builder setSetFlags(int setFlags) {
      mSetFlags = setFlags;
      return this;
    }

    /**
     * Weight of the master key.
     * @param masterKeyWeight Number between 0 and 255
     * @return
     */
    public Builder setMasterKeyWeight(int masterKeyWeight) {
      mMasterKeyWeight = masterKeyWeight;
      return this;
    }

    /**
     * A number from 0-255 representing the threshold this account sets on all operations it performs that have a low threshold.
     * @param lowThreshold Number between 0 and 255
     * @return
     */
    public Builder setLowThreshold(int lowThreshold) {
      mLowThreshold = lowThreshold;
      return this;
    }

    /**
     * A number from 0-255 representing the threshold this account sets on all operations it performs that have a medium threshold.
     * @param mediumThreshold Number between 0 and 255
     * @return
     */
    public Builder setMediumThreshold(int mediumThreshold) {
      mMediumThreshold = mediumThreshold;
      return this;
    }

    /**
     * A number from 0-255 representing the threshold this account sets on all operations it performs that have a high threshold.
     * @param highThreshold Number between 0 and 255
     * @return
     */
    public Builder setHighThreshold(int highThreshold) {
      mHighThreshold = highThreshold;
      return this;
    }

    /**
     * Sets the account's home domain address used in <a href="https://www.stellar.org/developers/learn/concepts/federation.html">Federation</a>.
     * @param homeDomain A string of the address which can be up to 32 characters.
     */
    public Builder setHomeDomain(String homeDomain) {
      if (homeDomain.length() > 32) {
        throw new IllegalArgumentException("Home domain must be <= 32 characters");
      }
      mHomeDomain = homeDomain;
      return this;
    }

    /**
     * Add, update, or remove a signer from the account. Signer is deleted if the weight = 0;
     * @param signer The keypair to set as a signer.
     * @param weight The weight to attach to the signer (0-255)
     * @return Builder
     */
    public Builder setSigner(StellarKeypair signer, int weight) {
      mSigner = signer;
      mSignerWeight = (byte) (weight & 0xFF);
      return this;
    }

    /**
     * Sets the source account for this operation.
     * @param sourceAccount The operation's source account.
     * @return
     */
    public Builder setSourceAccount(StellarKeypair sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /**
     * Builds an operation
     * @return
     */
    public SetOptionsOperation build() {
      SetOptionsOperation operation = new SetOptionsOperation(mInflationDestination, mClearFlags,
          mSetFlags, mMasterKeyWeight, mLowThreshold, mMediumThreshold, mHighThreshold,
          mHomeDomain, mSigner, mSignerWeight);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
