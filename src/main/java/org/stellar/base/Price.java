package org.stellar.base;

import org.apache.commons.math3.fraction.Fraction;
import org.stellar.base.xdr.Int32;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

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

    static Price fromString(String price) {
        BigDecimal maxInt = new BigDecimal(Integer.MAX_VALUE);
        BigDecimal number = new BigDecimal(price);
        BigDecimal a;
        BigDecimal f;
        List<BigDecimal[]> fractions = new ArrayList<BigDecimal[]>();
        fractions.add(new BigDecimal[]{new BigDecimal(0), new BigDecimal(1)});
        fractions.add(new BigDecimal[]{new BigDecimal(1), new BigDecimal(0)});
        int i = 2;
        while (true) {
            if (number.compareTo(maxInt) > 0) {
                break;
            }
            a = number.setScale(0, BigDecimal.ROUND_FLOOR);
            f = number.subtract(a);
            BigDecimal h = a.multiply(fractions.get(i - 1)[0]).add(fractions.get(i - 2)[0]);
            BigDecimal k = a.multiply(fractions.get(i - 1)[1]).add(fractions.get(i - 2)[1]);
            if (h.compareTo(maxInt) > 0 || k.compareTo(maxInt) > 0) {
                break;
            }
            fractions.add(new BigDecimal[]{h, k});
            if (f.compareTo(BigDecimal.ZERO) == 0) {
                break;
            }
            number = new BigDecimal(1).divide(f, 20, BigDecimal.ROUND_HALF_UP);
            i = i + 1;
        }
        BigDecimal n = fractions.get(fractions.size()-1)[0];
        BigDecimal d = fractions.get(fractions.size()-1)[1];
        return new Price(n.intValue(), d.intValue());
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
