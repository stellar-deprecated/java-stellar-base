package org.stellar.base;

import org.stellar.base.xdr.MemoType;

/**
 * Represents MEMO_NONE.
 */
public class MemoNone extends Memo {
  @Override
  org.stellar.base.xdr.Memo toXdr() {
    org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
    memo.setDiscriminant(MemoType.MEMO_NONE);
    return memo;
  }
}
