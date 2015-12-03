package org.stellar.base;

import org.apache.commons.codec.DecoderException;
import org.stellar.base.xdr.Memo;
import org.stellar.base.xdr.MemoType;

/**
 * Represents MEMO_RETURN.
 */
public class MemoReturnHash extends MemoHashAbstract {
  public MemoReturnHash(byte[] bytes) {
    super(bytes);
  }

  public MemoReturnHash(String hexString) throws DecoderException {
    super(hexString);
  }

  @Override
  Memo toXdr() {
    org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
    memo.setDiscriminant(MemoType.MEMO_RETURN);

    org.stellar.base.xdr.Hash hash = new org.stellar.base.xdr.Hash();
    hash.setHash(bytes);

    memo.setHash(hash);
    return memo;
  }
}
