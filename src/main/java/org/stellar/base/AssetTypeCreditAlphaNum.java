package org.stellar.base;

/**
 * Base class for AssetTypeCreditAlphaNum4 and AssetTypeCreditAlphaNum12 subclasses.
 */
public abstract class AssetTypeCreditAlphaNum extends Asset {
    protected final String mCode;
    protected final Keypair mIssuer;

    public AssetTypeCreditAlphaNum(String code, Keypair issuer) {
        mCode = code;
        mIssuer = issuer;
    }

    /**
     * Returns asset code
     * @return
     */
    public String getCode() {
        return mCode;
    }

    /**
     * Returns asset issuer
     * @return
     */
    public Keypair getIssuer() {
        return mIssuer;
    }

    @Override
    public boolean equals(Object object) {
        if (!this.getClass().equals(object.getClass())) {
            return false;
        }

        AssetTypeCreditAlphaNum o = (AssetTypeCreditAlphaNum) object;

        return this.getCode().equals(o.getCode()) &&
                this.getIssuer().getAddress().equals(o.getIssuer().getAddress());
    }
}
