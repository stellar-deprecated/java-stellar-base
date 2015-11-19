package org.stellar.base;

import junit.framework.TestCase;

import org.junit.Test;

public class AccountTest extends TestCase {
    @Test
    public void testNullArguments() {
        try {
            new Account(null, 10L);
            fail();
        } catch (NullPointerException e) {}

        try {
            new Account(Keypair.random(), null);
            fail();
        } catch (NullPointerException e) {}
    }

    @Test
    public void testIncrementSequenceNumber() {
        Account account = new Account(Keypair.random(), 100L);
        account.incrementSequenceNumber();
        assertEquals(account.getSequenceNumber(), new Long(101L));
    }

    @Test
    public void testGetters() {
        Keypair keypair = Keypair.random();
        Account account = new Account(keypair, 100L);
        assertEquals(account.getKeypair().getAddress(), keypair.getAddress());
        assertEquals(account.getSequenceNumber(), new Long(100L));
    }
}
