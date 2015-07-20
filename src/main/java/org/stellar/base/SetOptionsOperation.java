package org.stellar.base;

import org.stellar.base.xdr.AccountID;
import org.stellar.base.xdr.OperationType;
import org.stellar.base.xdr.SetOptionsOp;
import org.stellar.base.xdr.Signer;
import org.stellar.base.xdr.String32;
import org.stellar.base.xdr.Thresholds;
import org.stellar.base.xdr.Uint256;
import org.stellar.base.xdr.Uint32;

public class SetOptionsOperation extends Operation {

  private final StellarKeypair mInflationDestination;
  private final int mClearFlags;
  private final int mSetFlags;
  private final byte[] mThresholds;
  private final String mHomeDomain;
  private final StellarKeypair mSigner;
  private final byte mSignerWeight;

  private SetOptionsOperation(StellarKeypair inflationDestination, int clearFlags, int setFlags,
      byte[] thresholds, String homeDomain, StellarKeypair signer, byte signerWeight) {
    mInflationDestination = inflationDestination;
    mClearFlags = clearFlags;
    mSetFlags = setFlags;
    mThresholds = thresholds;
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

  public byte[] getThresholds() {
    return mThresholds;
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
      inflationDestination.setAccountID(mInflationDestination.getPublicKey());
      op.setinflationDest(inflationDestination);
    }
    if (mClearFlags != 0) {
      Uint32 clearFlags = new Uint32();
      clearFlags.setuint32(mClearFlags);
      op.setclearFlags(clearFlags);
    }
    if (mSetFlags != 0) {
      Uint32 setFlags = new Uint32();
      setFlags.setuint32(mSetFlags);
      op.setsetFlags(setFlags);
    }
    if (mThresholds != null) {
      Thresholds thresholds = new Thresholds();
      thresholds.setThresholds(mThresholds);
      op.setthresholds(thresholds);
    }
    if (mHomeDomain != null) {
      String32 homeDomain = new String32();
      homeDomain.setstring32(mHomeDomain);
      op.sethomeDomain(homeDomain);
    }
    if (mSigner != null) {
      Signer signer = new Signer();
      Uint256 pubKey = new Uint256();
      pubKey.setuint256(mSigner.getPublicKey());
      Uint32 weight = new Uint32();
      weight.setuint32((int) mSignerWeight & 0xFF);
      signer.setpubKey(pubKey);
      signer.setweight(weight);
      op.setsigner(signer);
    }

    org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
    body.setDiscriminant(OperationType.SET_OPTIONS);
    body.setsetOptionsOp(op);
    return body;
  }

  public static class Builder {
    private StellarKeypair mInflationDestination;
    private int mClearFlags;
    private int mSetFlags;
    private byte[] mThresholds = null;
    private String mHomeDomain;
    private StellarKeypair mSigner;
    private byte mSignerWeight;
    private StellarKeypair mSourceAccount;

    Builder(SetOptionsOp op) {
      if (op.getinflationDest() != null) {
        mInflationDestination = StellarKeypair.fromPublicKey(op.getinflationDest().getAccountID());
      }
      if (op.getclearFlags() != null) {
        mClearFlags = op.getclearFlags().getuint32();
      }
      if (op.getsetFlags() != null) {
        mSetFlags = op.getsetFlags().getuint32();
      }
      if (op.getthresholds() != null) {
        mThresholds = op.getthresholds().getThresholds();
      }
      if (op.gethomeDomain() != null) {
        mHomeDomain = op.gethomeDomain().getstring32();
      }
      if (op.getsigner() != null) {
        mSigner = StellarKeypair.fromPublicKey(op.getsigner().getpubKey().getuint256());
        mSignerWeight = (byte) (op.getsigner().getweight().getuint32().intValue() & 0xFF);
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

    /**
     * Sets the master key weight and each threshold for the account.
     * @param masterKeyWeight uint8
     * @param lowThreshold uint8
     * @param mediumThreshold uint8
     * @param highThreshold uint8
     * @return
     */
    public Builder setThresholds(int masterKeyWeight, int lowThreshold, int mediumThreshold,
        int highThreshold) {
      mThresholds = new byte[4];
      mThresholds[0] = (byte) (masterKeyWeight & 0xFF);
      mThresholds[1] = (byte) (lowThreshold & 0xFF);
      mThresholds[2] = (byte) (mediumThreshold & 0xFF);
      mThresholds[3] = (byte) (highThreshold & 0xFF);
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
          mSetFlags, mThresholds, mHomeDomain, mSigner, mSignerWeight);
      if (mSourceAccount != null) {
        operation.setSourceAccount(mSourceAccount);
      }
      return operation;
    }
  }
}
