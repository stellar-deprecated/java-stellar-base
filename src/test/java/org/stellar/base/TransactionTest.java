package org.stellar.base;

import junit.framework.TestCase;

import org.junit.Test;

import java.io.IOException;

public class TransactionTest extends TestCase {

    public void tearDown() {
        Network.useTestNetwork();
    }

    @Test
    public void testBuilderSuccessTestnet() throws FormatException, IOException {
        // GBPMKIRA2OQW2XZZQUCQILI5TMVZ6JNRKM423BSAISDM7ZFWQ6KWEBC4
        StellarKeypair source = StellarKeypair.fromSecretSeed("SCH27VUZZ6UAKB67BDNF6FA42YMBMQCBKXWGMFD5TZ6S5ZZCZFLRXKHS");
        StellarKeypair destination = StellarKeypair.fromAddress("GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR");

        Account account = new Account(source, 2908908335136768L);
        Transaction transaction = new Transaction.Builder(account)
                .addOperation(new CreateAccountOperation.Builder(destination, 20000000000L).build())
                .build();

        transaction.sign(source);

        assertEquals(
                "AAAAAF7FIiDToW1fOYUFBC0dmyufJbFTOa2GQESGz+S2h5ViAAAAZAAKVaMAAAABAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAA7eBSYbzcL5UKo7oXO24y1ckX+XuCtkDsyNHOp1n1bxAAAAAEqBfIAAAAAAAAAAABtoeVYgAAAEDLki9Oi700N60Lo8gUmEFHbKvYG4QSqXiLIt9T0ru2O5BphVl/jR9tYtHAD+UeDYhgXNgwUxqTEu1WukvEyYcD",
                transaction.toBase64EnvelopeXdr());
    }

    @Test
    public void testBuilderSuccessPublic() throws FormatException, IOException {
        Network.usePublicNetwork();

        // GBPMKIRA2OQW2XZZQUCQILI5TMVZ6JNRKM423BSAISDM7ZFWQ6KWEBC4
        StellarKeypair source = StellarKeypair.fromSecretSeed("SCH27VUZZ6UAKB67BDNF6FA42YMBMQCBKXWGMFD5TZ6S5ZZCZFLRXKHS");
        StellarKeypair destination = StellarKeypair.fromAddress("GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR");

        Account account = new Account(source, 2908908335136768L);
        Transaction transaction = new Transaction.Builder(account)
                .addOperation(new CreateAccountOperation.Builder(destination, 20000000000L).build())
                .build();

        transaction.sign(source);

        assertEquals(
                "AAAAAF7FIiDToW1fOYUFBC0dmyufJbFTOa2GQESGz+S2h5ViAAAAZAAKVaMAAAABAAAAAAAAAAAAAAABAAAAAAAAAAAAAAAA7eBSYbzcL5UKo7oXO24y1ckX+XuCtkDsyNHOp1n1bxAAAAAEqBfIAAAAAAAAAAABtoeVYgAAAEDzfR5PgRFim5Wdvq9ImdZNWGBxBWwYkQPa9l5iiBdtPLzAZv6qj+iOfSrqinsoF0XrLkwdIcZQVtp3VRHhRoUE",
                transaction.toBase64EnvelopeXdr());
    }

    @Test
    public void testToBase64EnvelopeXdrBuilderNoSignatures() throws FormatException, IOException {
        // GBPMKIRA2OQW2XZZQUCQILI5TMVZ6JNRKM423BSAISDM7ZFWQ6KWEBC4
        StellarKeypair source = StellarKeypair.fromSecretSeed("SCH27VUZZ6UAKB67BDNF6FA42YMBMQCBKXWGMFD5TZ6S5ZZCZFLRXKHS");
        StellarKeypair destination = StellarKeypair.fromAddress("GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR");

        Account account = new Account(source, 2908908335136768L);
        Transaction transaction = new Transaction.Builder(account)
                .addOperation(new CreateAccountOperation.Builder(destination, 20000000000L).build())
                .build();

        try {
            transaction.toBase64EnvelopeXdr();
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("Transaction must be signed by at least one signer."));
        }
    }

    @Test
    public void testNoOperations() throws FormatException, IOException {
        // GBPMKIRA2OQW2XZZQUCQILI5TMVZ6JNRKM423BSAISDM7ZFWQ6KWEBC4
        StellarKeypair source = StellarKeypair.fromSecretSeed("SCH27VUZZ6UAKB67BDNF6FA42YMBMQCBKXWGMFD5TZ6S5ZZCZFLRXKHS");

        Account account = new Account(source, 2908908335136768L);
        try {
            Transaction transaction = new Transaction.Builder(account).build();
            fail();
        } catch (RuntimeException exception) {
            assertTrue(exception.getMessage().contains("At least one operation required."));
        }
    }
}
