package org.stellar.base;

import org.apache.commons.codec.DecoderException;
import org.stellar.base.xdr.MemoType;
import org.stellar.base.xdr.Uint64;

import org.apache.commons.codec.binary.Hex;

import java.io.UnsupportedEncodingException;

public class Memo {
    public static org.stellar.base.xdr.Memo none() {
        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_NONE);
        return memo;
    }

    public static org.stellar.base.xdr.Memo text(String text) {
        try {
            if (text.getBytes("UTF8").length > 28) {
                throw new RuntimeException("text must be <= 28 bytes.");
            }
        } catch (UnsupportedEncodingException e) {
            return null;
        }

        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_TEXT);
        memo.setText(text);
        return memo;
    }

    public static org.stellar.base.xdr.Memo id(long id) {
        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_ID);
        Uint64 idXdr = new Uint64();
        idXdr.setUint64(id);
        memo.setId(idXdr);
        return memo;
    }

    public static org.stellar.base.xdr.Memo hash(byte[] bytes) {
        org.stellar.base.xdr.Memo memo = new org.stellar.base.xdr.Memo();
        memo.setDiscriminant(MemoType.MEMO_HASH);

        if (bytes.length < 32) {
            bytes = Util.paddedByteArray(bytes, 32);
        } else if (bytes.length > 32) {
            throw new RuntimeException("Memo.hash can contain 32 bytes at max.");
        }

        org.stellar.base.xdr.Hash hash = new org.stellar.base.xdr.Hash();
        hash.setHash(bytes);

        memo.setHash(hash);
        return memo;
    }

    public static org.stellar.base.xdr.Memo hash(String hexString) {
        Hex hexCodec = new Hex();
        byte[] decoded;
        try {
            decoded = hexCodec.decodeHex(hexString.toCharArray());
        } catch (DecoderException e) {
            throw new RuntimeException("Error decoding a string. Is it hex encoded?");
        }
        return Memo.hash(decoded);
    }

    public static org.stellar.base.xdr.Memo returnHash(byte[] bytes) {
        org.stellar.base.xdr.Memo memo = Memo.hash(bytes);
        memo.setDiscriminant(MemoType.MEMO_RETURN);
        return memo;
    }

    public static org.stellar.base.xdr.Memo returnHash(String hexString) {
        org.stellar.base.xdr.Memo memo = Memo.hash(hexString);
        memo.setDiscriminant(MemoType.MEMO_RETURN);
        return memo;
    }
}
