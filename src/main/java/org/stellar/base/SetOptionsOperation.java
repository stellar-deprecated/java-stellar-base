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

  private final Keypair mInflationDestination;
  private final Integer mClearFlags;
  private final Integer mSetFlags;
  private final Integer mMasterKeyWeight;
  private final Integer mLowThreshold;
  private final Integer mMediumThreshold;
  private final Integer mHighThreshold;
  private final String mHomeDomain;
  private final Keypair mSigner;
  private final Byte mSignerWeight;

  private SetOptionsOperation(Keypair inflationDestination, Integer clearFlags, Integer setFlags,
                              Integer masterKeyWeight, Integer lowThreshold, Integer mediumThreshold,
                              Integer highThreshold, String homeDomain, Keypair signer, Byte signerWeight) {
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
   */
  public Keypair getInflationDestination() {
    return mInflationDestination;
  }

  /**
   * Indicates which flags to clear. For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html" target="_blank">accounts doc</a>.
   */
  public Integer getClearFlags() {
    return mClearFlags;
  }

  /**
   * Indicates which flags to set. For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html" target="_blank">accounts doc</a>.
   */
  public Integer getSetFlags() {
    return mSetFlags;
  }

  /**
   * Weight of the master key.
   */
  public Integer getMasterKeyWeight() {
    return mMasterKeyWeight;
  }

  /**
   * A number from 0-255 representing the threshold this account sets on all operations it performs that have <a href="https://www.stellar.org/developers/learn/concepts/multi-sig.html" target="_blank">a low threshold</a>.
   */
  public Integer getLowThreshold() {
    return mLowThreshold;
  }

  /**
   * A number from 0-255 representing the threshold this account sets on all operations it performs that have <a href="https://www.stellar.org/developers/learn/concepts/multi-sig.html" target="_blank">a medium threshold</a>.
   */
  public Integer getMediumThreshold() {
    return mMediumThreshold;
  }

  /**
   * A number from 0-255 representing the threshold this account sets on all operations it performs that have <a href="https://www.stellar.org/developers/learn/concepts/multi-sig.html" target="_blank">a high threshold</a>.
   */
  public Integer getHighThreshold() {
    return mHighThreshold;
  }

  /**
   * The home domain of an account.
   */
  public String getHomeDomain() {
    return mHomeDomain;
  }

  /**
   * Additional signer added/removed in this operation.
   */
  public Keypair getSigner() {
    return mSigner;
  }

  /**
   * Additional signer weight. The signer is deleted if the weight is 0.
   */
  public Byte getSignerWeight() {
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
    if (mClearFlags != null) {
      Uint32 clearFlags = new Uint32();
      clearFlags.setUint32(mClearFlags);
      op.setClearFlags(clearFlags);
    }
    if (mSetFlags != null) {
      Uint32 setFlags = new Uint32();
      setFlags.setUint32(mSetFlags);
      op.setSetFlags(setFlags);
    }
    if (mMasterKeyWeight != null) {
      Uint32 uint32 = new Uint32();
      uint32.setUint32(mMasterKeyWeight);
      op.setMasterWeight(uint32);
    }
    if (mLowThreshold != null) {
      Uint32 uint32 = new Uint32();
      uint32.setUint32(mLowThreshold);
      op.setLowThreshold(uint32);
    }
    if (mMediumThreshold != null) {
      Uint32 uint32 = new Uint32();
      uint32.setUint32(mMediumThreshold);
      op.setMedThreshold(uint32);
    }
    if (mHighThreshold != null) {
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
    private Keypair mInflationDestination;
    private Integer mClearFlags;
    private Integer mSetFlags;
    private Integer mMasterKeyWeight;
    private Integer mLowThreshold;
    private Integer mMediumThreshold;
    private Integer mHighThreshold;
    private String mHomeDomain;
    private Keypair mSigner;
    private Byte mSignerWeight;
    private Keypair mSourceAccount;

    Builder(SetOptionsOp op) {
      if (op.getInflationDest() != null) {
        mInflationDestination = Keypair.fromXdrPublicKey(
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
        mSigner = Keypair.fromXdrPublicKey(op.getSigner().getPubKey().getAccountID());
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
     * @return Builder object so you can chain methods.
     */
    public Builder setInflationDestination(Keypair inflationDestination) {
      mInflationDestination = inflationDestination;
      return this;
    }

    /**
     * Clears the given flags from the account.
     * @param clearFlags For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html" target="_blank">accounts doc</a>.
     * @return Builder object so you can chain methods.
     */
    public Builder setClearFlags(int clearFlags) {
      mClearFlags = clearFlags;
      return this;
    }

    /**
     * Sets the given flags on the account.
     * @param setFlags For details about the flags, please refer to the <a href="https://www.stellar.org/developers/learn/concepts/accounts.html" target="_blank">accounts doc</a>.
     * @return Builder object so you can chain methods.
     */
    public Builder setSetFlags(int setFlags) {
      mSetFlags = setFlags;
      return this;
    }

    /**
     * Weight of the master key.
     * @param masterKeyWeight Number between 0 and 255
     * @return Builder object so you can chain methods.
     */
    public Builder setMasterKeyWeight(int masterKeyWeight) {
      mMasterKeyWeight = masterKeyWeight;
      return this;
    }

    /**
     * A number from 0-255 representing the threshold this account sets on all operations it performs that have a low threshold.
     * @param lowThreshold Number between 0 and 255
     * @return Builder object so you can chain methods.
     */
    public Builder setLowThreshold(int lowThreshold) {
      mLowThreshold = lowThreshold;
      return this;
    }

    /**
     * A number from 0-255 representing the threshold this account sets on all operations it performs that have a medium threshold.
     * @param mediumThreshold Number between 0 and 255
     * @return Builder object so you can chain methods.
     */
    public Builder setMediumThreshold(int mediumThreshold) {
      mMediumThreshold = mediumThreshold;
      return this;
    }

    /**
     * A number from 0-255 representing the threshold this account sets on all operations it performs that have a high threshold.
     * @param highThreshold Number between 0 and 255
     * @return Builder object so you can chain methods.
     */
    public Builder setHighThreshold(int highThreshold) {
      mHighThreshold = highThreshold;
      return this;
    }

    /**
     * Sets the account's home domain address used in <a href="https://www.stellar.org/developers/learn/concepts/federation.html" target="_blank">Federation</a>.
     * @param homeDomain A string of the address which can be up to 32 characters.
     * @return Builder object so you can chain methods.
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
     * @return Builder object so you can chain methods.
     */
    public Builder setSigner(Keypair signer, int weight) {
      mSigner = signer;
      mSignerWeight = (byte) (weight & 0xFF);
      return this;
    }

    /**
     * Sets the source account for this operation.
     * @param sourceAccount The operation's source account.
     * @return Builder object so you can chain methods.
     */
    public Builder setSourceAccount(Keypair sourceAccount) {
      mSourceAccount = sourceAccount;
      return this;
    }

    /**
     * Builds an operation
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
