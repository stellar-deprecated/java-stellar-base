package org.stellar.base;

public abstract class AssetTypeCreditAlphaNum extends Asset {
    protected final String mCode;
    protected final StellarKeypair mIssuer;

    public AssetTypeCreditAlphaNum(String code, StellarKeypair issuer) {
        mCode = code;
        mIssuer = issuer;
    }

    public String getCode() {
        return mCode;
    }
    public StellarKeypair getIssuer() {
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
