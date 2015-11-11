package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.SetOptionsOp;
import org.stellar.base.xdr.Signer;
import org.stellar.base.xdr.String32;
import org.stellar.base.xdr.Thresholds;
import org.stellar.base.xdr.Uint256;
import org.stellar.base.xdr.Uint32;

import java.util.Arrays;

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

  public StellarKeypair getInflationDestination() {
    return mInflationDestination;
  }

  public int getClearFlags() {
    return mClearFlags;
  }

  public int getSetFlags() {
    return mSetFlags;
  }

  public int getMasterKeyWeight() {
    return mMasterKeyWeight;
  }

  public int getLowThreshold() {
    return mLowThreshold;
  }

  public int getMediumThreshold() {
    return mMediumThreshold;
  }

  public int getHighThreshold() {
    return mHighThreshold;
  }

  public String getHomeDomain() {
    return mHomeDomain;
  }

  public StellarKeypair getSigner() {
    return mSigner;
  }

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
      byte[] homeDomainBytes = new byte[32];
      Arrays.fill(homeDomainBytes, (byte) 0);
      System.arraycopy(mHomeDomain.getBytes(), 0, homeDomainBytes, 0, mHomeDomain.length());

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

  public static class Builder {
    private StellarKeypair mInflationDestination;
    private int mClearFlags;
    private int mSetFlags;
    private byte[] mThresholds = null;
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
     * @param clearFlags
     * @return
     */
    public Builder setClearFlags(int clearFlags) {
      mClearFlags = clearFlags;
      return this;
    }

    /**
     * Sets the given flags on the account.
     * @param setFlags
     * @return
     */
    public Builder setSetFlags(int setFlags) {
      mSetFlags = setFlags;
      return this;
    }

    public Builder setMasterKeyWeight(int masterKeyWeight) {
      mMasterKeyWeight = masterKeyWeight;
      return this;
    }

    public Builder setLowThreshold(int lowThreshold) {
      mLowThreshold = lowThreshold;
      return this;
    }

    public Builder setMediumThreshold(int mediumThreshold) {
      mMediumThreshold = mediumThreshold;
      return this;
    }

    public Builder setHighThreshold(int highThreshold) {
      mHighThreshold = highThreshold;
      return this;
    }

    /**
     * Sets the account's home domain address which can be used to lookup the stellar.txt file.
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
     * @param weight The weight to attach to the signer, uint8 (0-255)
     * @return Builder
     */
    public Builder setSigner(StellarKeypair signer, int weight) {
      mSigner = signer;
      mSignerWeight = (byte) (weight & 0xFF);
      return this;
    }

    public Builder setSourceAccount(StellarKeypair account) {
      mSourceAccount = account;
      return this;
    }

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
