package org.stellar.base;

import junit.framework.TestCase;

import org.junit.Test;

import org.stellar.base.xdr.MemoType;

import java.util.Arrays;

public class MemoTest extends TestCase {
    @Test
    public void testMemoNone() {
        org.stellar.base.xdr.Memo memo = Memo.none();
        assertEquals(MemoType.MEMO_NONE, memo.getDiscriminant());
    }

    @Test
    public void testMemoTextSuccess() {
        org.stellar.base.xdr.Memo memo = Memo.text("test");
        assertEquals(MemoType.MEMO_TEXT, memo.getDiscriminant());
        assertEquals("test", memo.getText());
    }

    @Test
    public void testMemoTextUtf8() {
        org.stellar.base.xdr.Memo memo = Memo.text("三");
        assertEquals(MemoType.MEMO_TEXT, memo.getDiscriminant());
        assertEquals("三", memo.getText());
    }

    @Test
    public void testMemoTextTooLong() {
        try {
            Memo.text("12345678901234567890123456789");
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("text must be <= 28 bytes."));
        }
    }

    @Test
    public void testMemoId() {
        org.stellar.base.xdr.Memo memo = Memo.id(9223372036854775807L);
        assertEquals(MemoType.MEMO_ID, memo.getDiscriminant());
        assertEquals(new Long(9223372036854775807L), memo.getId().getUint64());
    }

    @Test
    public void testMemoHashSuccess() {
        org.stellar.base.xdr.Memo memo = Memo.hash("4142434445464748494a4b4c");
        assertEquals(MemoType.MEMO_HASH, memo.getDiscriminant());
        String test = "ABCDEFGHIJKL";
        assertEquals(test, Util.paddedByteArrayToString(memo.getHash().getHash()));
    }

    @Test
    public void testMemoHashBytesSuccess() {
        byte[] bytes = new byte[10];
        Arrays.fill(bytes, (byte) 'A');
        org.stellar.base.xdr.Memo memo = Memo.hash(bytes);
        assertEquals(MemoType.MEMO_HASH, memo.getDiscriminant());
        assertEquals("AAAAAAAAAA", Util.paddedByteArrayToString(memo.getHash().getHash()));
    }

    @Test
    public void testMemoHashTooLong() {
        byte[] longer = new byte[33];
        Arrays.fill(longer, (byte) 0);
        try {
            Memo.hash(longer);
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("Memo.hash can contain 32 bytes at max."));
        }
    }

    @Test
    public void testMemoHashInvalidHex() {
        try {
            Memo.hash("test");
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("Error decoding a string."));
        }
    }

    @Test
    public void testMemoReturnHashSuccess() {
        org.stellar.base.xdr.Memo memo = Memo.returnHash("4142434445464748494a4b4c");
        assertEquals(MemoType.MEMO_RETURN, memo.getDiscriminant());
    }
}
