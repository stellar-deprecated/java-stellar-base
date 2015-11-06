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
    Asset asset = new AssetTypeNative();
    long amount = 1000;

    PaymentOperation operation = new PaymentOperation.Builder(destination, asset, amount)
        .setSourceAccount(destination)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    PaymentOperation parsedOperation = (PaymentOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertTrue(parsedOperation.getCurrency() instanceof AssetTypeNative);
    Assert.assertEquals(amount, parsedOperation.getAmount());
  }

  public void testPathPaymentOperation() {
    Asset sendAsset = new AssetTypeNative();
    long sendMax = 1000;
    StellarKeypair destination = StellarKeypair.random();
    Asset destAsset = new AssetTypeNative();
    long destAmount = 1000;
    Asset[] path = {new AssetTypeNative(), new AssetTypeNative()};

    PathPaymentOperation operation = new PathPaymentOperation.Builder(
        sendAsset, sendMax, destination, destAsset, destAmount, path)
        .setSourceAccount(destination)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    PathPaymentOperation parsedOperation = (PathPaymentOperation) Operation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getSendCurrency() instanceof AssetTypeNative);
    Assert.assertEquals(sendMax, parsedOperation.getSendMax());
    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertTrue(parsedOperation.getDestCurrency() instanceof AssetTypeNative);
    Assert.assertEquals(destAmount, parsedOperation.getDestAmount());
    Assert.assertEquals(path.length, parsedOperation.getPath().length);
  }

  public void testChangeTrustOperation() {
    Asset asset = new AssetTypeNative();
    long limit = Long.MAX_VALUE;
    StellarKeypair source = StellarKeypair.random();

    ChangeTrustOperation operation = new ChangeTrustOperation.Builder(asset, limit)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    ChangeTrustOperation parsedOperation = (ChangeTrustOperation) Operation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getAsset() instanceof AssetTypeNative);
    Assert.assertEquals(limit, parsedOperation.getLimit());
  }

  public void testAllowTrustOperation() {
    StellarKeypair trustor = StellarKeypair.random();
    String assetCode = "USDA";
    boolean authorize = true;

    AllowTrustOperation operation = new AllowTrustOperation.Builder(trustor, assetCode, authorize)
        .setSourceAccount(trustor)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    AllowTrustOperation parsedOperation = (AllowTrustOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(trustor.getAddress(), parsedOperation.getTrustor().getAddress());
    Assert.assertEquals(assetCode, parsedOperation.getAssetCode());
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
        .setMasterKeyWeight(masterKeyWeight)
        .setLowThreshold(lowThreshold)
        .setMediumThreshold(mediumThreshold)
        .setHighThreshold(highThreshold)
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
    Assert.assertEquals(masterKeyWeight, operation.getMasterKeyWeight());
    Assert.assertEquals(lowThreshold, operation.getLowThreshold());
    Assert.assertEquals(mediumThreshold, operation.getMediumThreshold());
    Assert.assertEquals(highThreshold, operation.getHighThreshold());
    Assert.assertEquals(homeDomain, operation.getHomeDomain());
    Assert.assertEquals(signer.getAddress(), operation.getSigner().getAddress());
    Assert.assertEquals(signerWeight, operation.getSignerWeight());
  }

  public void testManagerOfferOperation() {
    StellarKeypair source = StellarKeypair.random();
    Asset selling = new AssetTypeNative();
    Asset buying = new AssetTypeNative();
    long amount = 1L;
    float price = 1.1F;
    long offerId = 1L;

    ManagerOfferOperation operation = new ManagerOfferOperation.Builder(selling, buying,
        amount, price, offerId)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    ManagerOfferOperation parsedOperation = (ManagerOfferOperation) ManagerOfferOperation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getSelling() instanceof AssetTypeNative);
    Assert.assertTrue(parsedOperation.getBuying() instanceof AssetTypeNative);
    Assert.assertEquals(amount, parsedOperation.getAmount());
    Assert.assertEquals(price, parsedOperation.getPrice(), 0);
    Assert.assertEquals(offerId, parsedOperation.getOfferId());
  }

  public void testAccountMergeOperation() {
    StellarKeypair destination = StellarKeypair.random();

    AccountMergeOperation operation = new AccountMergeOperation.Builder(destination)
      .setSourceAccount(destination)
      .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();

    AccountMergeOperation parsedOperation = (AccountMergeOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
  }
}