package org.stellar.base;

import org.apache.commons.math3.fraction.Fraction;
import org.stellar.base.xdr.Int32;

public class Price {
    private final int n;
    private final int d;

    Price(int n, int d) {
        this.n = n;
        this.d = d;
    }

    int getNumerator() {
        return n;
    }

    int getDenominator() {
        return d;
    }

    static Price rationalApproximation(double price) {
        Fraction fraction = new Fraction(price, Integer.MAX_VALUE);
        return new Price(fraction.getNumerator(), fraction.getDenominator());
    }

    public org.stellar.base.xdr.Price toXdr() {
        org.stellar.base.xdr.Price xdr = new org.stellar.base.xdr.Price();
        Int32 n = new Int32();
        Int32 d = new Int32();
        n.setint32(this.n);
        d.setint32(this.d);
        xdr.setn(n);
        xdr.setd(d);
        return xdr;
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Price)) {
            return false;
        }

        Price price = (Price) object;

        return this.getNumerator() == price.getNumerator() &&
                this.getDenominator() == price.getDenominator();

    }
}
