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

  public void testChangeTrustOperation() {
    Currency line = new NativeCurrency();
    long limit = Long.MAX_VALUE;
    StellarKeypair source = StellarKeypair.random();

    ChangeTrustOperation operation = new ChangeTrustOperation.Builder(line, limit)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    ChangeTrustOperation parsedOperation = (ChangeTrustOperation) Operation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getLine() instanceof NativeCurrency);
    Assert.assertEquals(limit, parsedOperation.getLimit());
  }

  public void testAllowTrustOperation() {
    StellarKeypair trustor = StellarKeypair.random();
    String currencyCode = "USD";
    boolean authorize = true;

    AllowTrustOperation operation = new AllowTrustOperation.Builder(trustor, currencyCode, authorize)
        .setSourceAccount(trustor)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    AllowTrustOperation parsedOperation = (AllowTrustOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(trustor.getAddress(), parsedOperation.getTrustor().getAddress());
    Assert.assertEquals(currencyCode, parsedOperation.getCurrencyCode());
    Assert.assertEquals(authorize, parsedOperation.getAuthorize());
  }

  public void testSetOptionsOperation() {
    StellarKeypair inflationDestination = StellarKeypair.random();
    int clearFlags = 1;
    int setFlags = 1;
    int masterKeyWeight = 1;
    int lowThreshold = 2;
    int mediumThreshold = 3;
    int highThreshold = 4;
    String homeDomain = "stellar.org";
    StellarKeypair signer = StellarKeypair.random();
    int signerWeight = 1;

    SetOptionsOperation operation = new SetOptionsOperation.Builder()
        .setInflationDestination(inflationDestination)
        .setClearFlags(clearFlags)
        .setSetFlags(setFlags)
        .setThresholds(masterKeyWeight, lowThreshold, mediumThreshold, highThreshold)
        .setHomeDomain(homeDomain)
        .setSigner(signer, signerWeight)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    SetOptionsOperation parsedOperation = (SetOptionsOperation) SetOptionsOperation.fromXdr(xdr);

    Assert.assertEquals(inflationDestination.getAddress(), parsedOperation.getInflationDestination().getAddress());
    Assert.assertEquals(clearFlags, parsedOperation.getClearFlags());
    Assert.assertEquals(setFlags, parsedOperation.getSetFlags());
    byte[] thresholds = {(byte) (masterKeyWeight & 0xFF), (byte) (lowThreshold & 0xFF),
        (byte) (mediumThreshold & 0xFF), (byte) (highThreshold & 0xFF)};
    Assert.assertArrayEquals(thresholds, operation.getThresholds());
    Assert.assertEquals(homeDomain, operation.getHomeDomain());
    Assert.assertEquals(signer.getAddress(), operation.getSigner().getAddress());
    Assert.assertEquals(signerWeight, operation.getSignerWeight());
  }
}