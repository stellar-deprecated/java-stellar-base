package org.stellar.base;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;
import org.stellar.base.xdr.MemoType;
import org.stellar.base.xdr.Uint64;

import java.nio.charset.StandardCharsets;

/**
 * Represents Memo.
 * @see Transaction
 */
public class Memo {
    /**
     * Creates MEMO_NONE type memo.
     * @return
     */
    public static org.stellar.base.xdr.Memo none() {
        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_NONE);
        return memo;
    }

    /**
     * Creates MEMO_TEXT type memo.
     * @param text
     * @return
     */
    public static org.stellar.base.xdr.Memo text(String text) {
        if (text.getBytes(StandardCharsets.UTF_8).length > 28) {
            throw new MemoTooLongException("text must be <= 28 bytes.");
        }

        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_TEXT);
        memo.setText(text);
        return memo;
    }

    /**
     * Creates MEMO_ID type memo.
     * @param id
     * @return
     */
    public static org.stellar.base.xdr.Memo id(long id) {
        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_ID);
        Uint64 idXdr = new Uint64();
        idXdr.setUint64(id);
        memo.setId(idXdr);
        return memo;
    }

    /**
     * Creates MEMO_HASH type memo from byte array.
     * @param bytes
     * @return
     */
    public static org.stellar.base.xdr.Memo hash(byte[] bytes) {
        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_HASH);

        if (bytes.length < 32) {
            bytes = Util.paddedByteArray(bytes, 32);
        } else if (bytes.length > 32) {
            throw new MemoTooLongException("Memo.hash can contain 32 bytes at max.");
        }

        org.stellar.base.xdr.Hash hash = new org.stellar.base.xdr.Hash();
        hash.setHash(bytes);

        memo.setHash(hash);
        return memo;
    }

    /**
     * Creates MEMO_HASH type memo from hex-encoded string
     * @param hexString
     * @return
     * @throws DecoderException
     */
    public static org.stellar.base.xdr.Memo hash(String hexString) throws DecoderException {
        Hex hexCodec = new Hex();
        byte[] decoded = hexCodec.decodeHex(hexString.toCharArray());
        return Memo.hash(decoded);
    }

    /**
     * Creates MEMO_RETURN type memo from byte array.
     * @param bytes
     * @return
     */
    public static org.stellar.base.xdr.Memo returnHash(byte[] bytes) {
        org.stellar.base.xdr.Memo memo = Memo.hash(bytes);
        memo.setDiscriminant(MemoType.MEMO_RETURN);
        return memo;
    }

    /**
     * Creates MEMO_RETURN type memo from hex-encoded string.
     * @param hexString
     * @return
     * @throws DecoderException
     */
    public static org.stellar.base.xdr.Memo returnHash(String hexString) throws DecoderException {
        org.stellar.base.xdr.Memo memo = Memo.hash(hexString);
        memo.setDiscriminant(MemoType.MEMO_RETURN);
        return memo;
    }
}
