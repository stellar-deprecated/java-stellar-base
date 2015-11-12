package org.stellar.base;

import junit.framework.TestCase;

import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;

public class OperationTest extends TestCase {

  @Test
  public void testCreateAccountOperation() throws FormatException, IOException, AssetCodeLengthInvalidException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR
    StellarKeypair destination = StellarKeypair.fromSecretSeed("SDHZGHURAYXKU2KMVHPOXI6JG2Q4BSQUQCEOY72O3QQTCLR2T455PMII");

    long startingAmount = 1000;
    CreateAccountOperation operation = new CreateAccountOperation.Builder(destination, startingAmount)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    CreateAccountOperation parsedOperation = (CreateAccountOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(source.getAddress(), parsedOperation.getSourceAccount().getAddress());
    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertEquals(startingAmount, parsedOperation.getStartingBalance());

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAAAAAAA7eBSYbzcL5UKo7oXO24y1ckX+XuCtkDsyNHOp1n1bxAAAAAAAAAD6A==",
            operation.toBase64());
  }

  public void testPaymentOperation() throws FormatException, IOException, AssetCodeLengthInvalidException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR
    StellarKeypair destination = StellarKeypair.fromSecretSeed("SDHZGHURAYXKU2KMVHPOXI6JG2Q4BSQUQCEOY72O3QQTCLR2T455PMII");

    Asset asset = new AssetTypeNative();
    long amount = 1000;

    PaymentOperation operation = new PaymentOperation.Builder(destination, asset, amount)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    PaymentOperation parsedOperation = (PaymentOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(source.getAddress(), parsedOperation.getSourceAccount().getAddress());
    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertTrue(parsedOperation.getAsset() instanceof AssetTypeNative);
    Assert.assertEquals(amount, parsedOperation.getAmount());

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAEAAAAA7eBSYbzcL5UKo7oXO24y1ckX+XuCtkDsyNHOp1n1bxAAAAAAAAAAAAAAA+g=",
            operation.toBase64());
  }

  public void testPathPaymentOperation() throws FormatException, IOException, AssetCodeLengthInvalidException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR
    StellarKeypair destination = StellarKeypair.fromSecretSeed("SDHZGHURAYXKU2KMVHPOXI6JG2Q4BSQUQCEOY72O3QQTCLR2T455PMII");
    // GCGZLB3X2B3UFOFSHHQ6ZGEPEX7XYPEH6SBFMIV74EUDOFZJA3VNL6X4
    StellarKeypair issuer = StellarKeypair.fromSecretSeed("SBOBVZUN6WKVMI6KIL2GHBBEETEV6XKQGILITNH6LO6ZA22DBMSDCPAG");

    // GAVAQKT2M7B4V3NN7RNNXPU5CWNDKC27MYHKLF5UNYXH4FNLFVDXKRSV
    StellarKeypair pathIssuer1 = StellarKeypair.fromSecretSeed("SALDLG5XU5AEJWUOHAJPSC4HJ2IK3Z6BXXP4GWRHFT7P7ILSCFFQ7TC5");
    // GBCP5W2VS7AEWV2HFRN7YYC623LTSV7VSTGIHFXDEJU7S5BAGVCSETRR
    StellarKeypair pathIssuer2 = StellarKeypair.fromSecretSeed("SA64U7C5C7BS5IHWEPA7YWFN3Z6FE5L6KAMYUIT4AQ7KVTVLD23C6HEZ");

    Asset sendAsset = new AssetTypeNative();
    long sendMax = 1000;
    Asset destAsset = new AssetTypeCreditAlphaNum4("USD", issuer);
    long destAmount = 1000;
    Asset[] path = {new AssetTypeCreditAlphaNum4("USD", pathIssuer1), new AssetTypeCreditAlphaNum12("TESTTEST", pathIssuer2)};

    PathPaymentOperation operation = new PathPaymentOperation.Builder(
        sendAsset, sendMax, destination, destAsset, destAmount, path)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    PathPaymentOperation parsedOperation = (PathPaymentOperation) Operation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getSendAsset() instanceof AssetTypeNative);
    Assert.assertEquals(source.getAddress(), parsedOperation.getSourceAccount().getAddress());
    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());
    Assert.assertEquals(sendMax, parsedOperation.getSendMax());
    Assert.assertTrue(parsedOperation.getDestAsset() instanceof AssetTypeCreditAlphaNum4);
    Assert.assertEquals(destAmount, parsedOperation.getDestAmount());
    Assert.assertEquals(path.length, parsedOperation.getPath().length);

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAIAAAAAAAAAAAAAA+gAAAAA7eBSYbzcL5UKo7oXO24y1ckX+XuCtkDsyNHOp1n1bxAAAAABVVNEAAAAAACNlYd30HdCuLI54eyYjyX/fDyH9IJWIr/hKDcXKQbq1QAAAAAAAAPoAAAAAgAAAAFVU0QAAAAAACoIKnpnw8rtrfxa276dFZo1C19mDqWXtG4ufhWrLUd1AAAAAlRFU1RURVNUAAAAAAAAAABE/ttVl8BLV0csW/xgXtbXOVf1lMyDluMiafl0IDVFIg==",
            operation.toBase64());
  }

  public void testChangeTrustOperation() throws FormatException, IOException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");

    Asset asset = new AssetTypeNative();
    long limit = Long.MAX_VALUE;

    ChangeTrustOperation operation = new ChangeTrustOperation.Builder(asset, limit)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    ChangeTrustOperation parsedOperation = (ChangeTrustOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(source.getAddress(), parsedOperation.getSourceAccount().getAddress());
    Assert.assertTrue(parsedOperation.getAsset() instanceof AssetTypeNative);
    Assert.assertEquals(limit, parsedOperation.getLimit());

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAYAAAAAf/////////8=",
            operation.toBase64());
  }

  public void testAllowTrustOperation() throws IOException, FormatException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR
    StellarKeypair trustor = StellarKeypair.fromSecretSeed("SDHZGHURAYXKU2KMVHPOXI6JG2Q4BSQUQCEOY72O3QQTCLR2T455PMII");

    String assetCode = "USDA";
    boolean authorize = true;

    AllowTrustOperation operation = new AllowTrustOperation.Builder(trustor, assetCode, authorize)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    AllowTrustOperation parsedOperation = (AllowTrustOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(source.getAddress(), parsedOperation.getSourceAccount().getAddress());
    Assert.assertEquals(trustor.getAddress(), parsedOperation.getTrustor().getAddress());
    Assert.assertEquals(assetCode, parsedOperation.getAssetCode());
    Assert.assertEquals(authorize, parsedOperation.getAuthorize());

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAcAAAAA7eBSYbzcL5UKo7oXO24y1ckX+XuCtkDsyNHOp1n1bxAAAAABVVNEQQAAAAE=",
            operation.toBase64());
  }

  public void testSetOptionsOperation() throws IOException, FormatException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR
    StellarKeypair inflationDestination = StellarKeypair.fromSecretSeed("SDHZGHURAYXKU2KMVHPOXI6JG2Q4BSQUQCEOY72O3QQTCLR2T455PMII");
    // GBCP5W2VS7AEWV2HFRN7YYC623LTSV7VSTGIHFXDEJU7S5BAGVCSETRR
    StellarKeypair signer = StellarKeypair.fromSecretSeed("SA64U7C5C7BS5IHWEPA7YWFN3Z6FE5L6KAMYUIT4AQ7KVTVLD23C6HEZ");

    int clearFlags = 1;
    int setFlags = 1;
    int masterKeyWeight = 1;
    int lowThreshold = 2;
    int mediumThreshold = 3;
    int highThreshold = 4;
    String homeDomain = "stellar.org";
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
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    SetOptionsOperation parsedOperation = (SetOptionsOperation) SetOptionsOperation.fromXdr(xdr);

    Assert.assertEquals(inflationDestination.getAddress(), parsedOperation.getInflationDestination().getAddress());
    Assert.assertEquals(clearFlags, parsedOperation.getClearFlags());
    Assert.assertEquals(setFlags, parsedOperation.getSetFlags());
    Assert.assertEquals(masterKeyWeight, parsedOperation.getMasterKeyWeight());
    Assert.assertEquals(lowThreshold, parsedOperation.getLowThreshold());
    Assert.assertEquals(mediumThreshold, parsedOperation.getMediumThreshold());
    Assert.assertEquals(highThreshold, parsedOperation.getHighThreshold());
    Assert.assertEquals(homeDomain, parsedOperation.getHomeDomain());
    Assert.assertEquals(signer.getAddress(), parsedOperation.getSigner().getAddress());
    Assert.assertEquals(signerWeight, parsedOperation.getSignerWeight());

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAUAAAABAAAAAO3gUmG83C+VCqO6FztuMtXJF/l7grZA7MjRzqdZ9W8QAAAAAQAAAAEAAAABAAAAAQAAAAEAAAABAAAAAQAAAAIAAAABAAAAAwAAAAEAAAAEAAAAAQAAAAtzdGVsbGFyLm9yZwAAAAABAAAAAET+21WXwEtXRyxb/GBe1tc5V/WUzIOW4yJp+XQgNUUiAAAAAQ==",
            operation.toBase64());
  }

  public void testManagerOfferOperation() throws IOException, FormatException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GBCP5W2VS7AEWV2HFRN7YYC623LTSV7VSTGIHFXDEJU7S5BAGVCSETRR
    StellarKeypair issuer = StellarKeypair.fromSecretSeed("SA64U7C5C7BS5IHWEPA7YWFN3Z6FE5L6KAMYUIT4AQ7KVTVLD23C6HEZ");

    Asset selling = new AssetTypeNative();
    Asset buying = Asset.createNonNativeAsset("USD", issuer);
    long amount = 100;
    String price = "0.85334384"; // n=5333399 d=6250000
    Price priceObj = Price.fromString(price);
    long offerId = 1;

    ManagerOfferOperation operation = new ManagerOfferOperation.Builder(selling, buying,
        amount, price, offerId)
        .setSourceAccount(source)
        .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    ManagerOfferOperation parsedOperation = (ManagerOfferOperation) ManagerOfferOperation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getSelling() instanceof AssetTypeNative);
    Assert.assertTrue(parsedOperation.getBuying() instanceof AssetTypeCreditAlphaNum4);
    Assert.assertTrue(parsedOperation.getBuying().equals(buying));
    Assert.assertEquals(amount, parsedOperation.getAmount());
    Assert.assertEquals(price, parsedOperation.getPrice());
    Assert.assertEquals(priceObj.getNumerator(), 5333399);
    Assert.assertEquals(priceObj.getDenominator(), 6250000);
    Assert.assertEquals(offerId, parsedOperation.getOfferId());

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAMAAAAAAAAAAVVTRAAAAAAARP7bVZfAS1dHLFv8YF7W1zlX9ZTMg5bjImn5dCA1RSIAAAAAAAAAZABRYZcAX14QAAAAAAAAAAE=",
            operation.toBase64());
  }

  public void testCreatePassiveOfferOperation() throws IOException, FormatException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GBCP5W2VS7AEWV2HFRN7YYC623LTSV7VSTGIHFXDEJU7S5BAGVCSETRR
    StellarKeypair issuer = StellarKeypair.fromSecretSeed("SA64U7C5C7BS5IHWEPA7YWFN3Z6FE5L6KAMYUIT4AQ7KVTVLD23C6HEZ");

    Asset selling = new AssetTypeNative();
    Asset buying = Asset.createNonNativeAsset("USD", issuer);
    long amount = 100;
    String price = "2.93850088"; // n=36731261 d=12500000
    Price priceObj = Price.fromString(price);

      CreatePassiveOfferOperation operation = new CreatePassiveOfferOperation.Builder(selling, buying, amount, price)
            .setSourceAccount(source)
            .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();
    CreatePassiveOfferOperation parsedOperation = (CreatePassiveOfferOperation) CreatePassiveOfferOperation.fromXdr(xdr);

    Assert.assertTrue(parsedOperation.getSelling() instanceof AssetTypeNative);
    Assert.assertTrue(parsedOperation.getBuying() instanceof AssetTypeCreditAlphaNum4);
    Assert.assertTrue(parsedOperation.getBuying().equals(buying));
    Assert.assertEquals(amount, parsedOperation.getAmount());
    Assert.assertEquals(price, parsedOperation.getPrice());
    Assert.assertEquals(priceObj.getNumerator(), 36731261);
    Assert.assertEquals(priceObj.getDenominator(), 12500000);

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAQAAAAAAAAAAVVTRAAAAAAARP7bVZfAS1dHLFv8YF7W1zlX9ZTMg5bjImn5dCA1RSIAAAAAAAAAZAIweX0Avrwg",
            operation.toBase64());
  }

  public void testAccountMergeOperation() throws IOException, FormatException {
    // GC5SIC4E3V56VOHJ3OZAX5SJDTWY52JYI2AFK6PUGSXFVRJQYQXXZBZF
    StellarKeypair source = StellarKeypair.fromSecretSeed("SC4CGETADVYTCR5HEAVZRB3DZQY5Y4J7RFNJTRA6ESMHIPEZUSTE2QDK");
    // GDW6AUTBXTOC7FIKUO5BOO3OGLK4SF7ZPOBLMQHMZDI45J2Z6VXRB5NR
    StellarKeypair destination = StellarKeypair.fromSecretSeed("SDHZGHURAYXKU2KMVHPOXI6JG2Q4BSQUQCEOY72O3QQTCLR2T455PMII");

    AccountMergeOperation operation = new AccountMergeOperation.Builder(destination)
      .setSourceAccount(source)
      .build();

    org.stellar.base.xdr.Operation xdr = operation.toXdr();

    AccountMergeOperation parsedOperation = (AccountMergeOperation) Operation.fromXdr(xdr);

    Assert.assertEquals(destination.getAddress(), parsedOperation.getDestination().getAddress());

    assertEquals(
            "AAAAAQAAAAC7JAuE3XvquOnbsgv2SRztjuk4RoBVefQ0rlrFMMQvfAAAAAgAAAAA7eBSYbzcL5UKo7oXO24y1ckX+XuCtkDsyNHOp1n1bxA=",
            operation.toBase64());
  }
}