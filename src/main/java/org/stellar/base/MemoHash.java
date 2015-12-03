package org.stellar.base;

import org.apache.commons.codec.DecoderException;
import org.stellar.base.xdr.MemoType;

/**
 * Represents MEMO_HASH.
 */
public class MemoHash extends MemoHashAbstract {
  public MemoHash(byte[] bytes) {
    super(bytes);
  }

  public MemoHash(String hexString) throws DecoderException {
    super(hexString);
  }

  @Override
  org.stellar.base.xdr.Memo toXdr() {
    org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
    memo.setDiscriminant(MemoType.MEMO_HASH);

    org.stellar.base.xdr.Hash hash = new org.stellar.base.xdr.Hash();
    hash.setHash(bytes);

    memo.setHash(hash);
    return memo;
  }
}
