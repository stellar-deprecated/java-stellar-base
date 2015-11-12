package org.stellar.base;

import org.stellar.base.xdr.MemoType;

public class Memo {
    static org.stellar.base.xdr.Memo none() {
        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_NONE);
        return memo;
    }
}
