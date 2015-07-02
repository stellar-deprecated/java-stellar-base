package org.stellar.base;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class OperationTest extends TestCase {

  @Test
  public void testCreateAccountOperation() {
    StellarKeypair keypair = StellarKeypair.random();
    long startingAmount = 1000;
    CreateAccountOperation operation = new CreateAccountOperation.Builder(keypair, startingAmount)
        .setSourceAccount(keypair)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    CreateAccountOperation parsedOperation = (CreateAccountOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(keypair.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertEquals(startingAmount, parsedOperation.getStartingBalance());
    Assert.assertEquals(keypair.getAddress(), parsedOperation.getSourceAccount().getAddress());
  }

  public void testPaymentOperation() {
    StellarKeypair destination = StellarKeypair.random();
    Currency currency = new NativeCurrency();
    long amount = 1000;

    PaymentOperation operation = new PaymentOperation.Builder(destination, currency, amount)
        .setSourceAccount(destination)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    PaymentOperation parsedOperation = (PaymentOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertTrue(parsedOperation.getCurrency() instanceof NativeCurrency);
    Assert.assertEquals(amount, parsedOperation.getAmount());
  }

  public void testPathPaymentOperation() {
    Currency sendCurrency = new NativeCurrency();
    long sendMax = 1000;
    StellarKeypair destination = StellarKeypair.random();
    Currency destCurrency = new NativeCurrency();
    long destAmount = 1000;
    Currency[] path = {new NativeCurrency(), new NativeCurrency()};

    PathPaymentOperation operation = new PathPaymentOperation.Builder(
        sendCurrency, sendMax, destination, destCurrency, destAmount, path)
        .setSourceAccount(destination)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    PathPaymentOperation parsedOperation = (PathPaymentOperation) Operation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getSendCurrency() instanceof NativeCurrency);
    Assert.assertEquals(sendMax, parsedOperation.getSendMax());
    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertTrue(parsedOperation.getDestCurrency() instanceof NativeCurrency);
    Assert.assertEquals(destAmount, parsedOperation.getDestAmount());
    Assert.assertEquals(path.length, parsedOperation.getPath().length);
  }
}