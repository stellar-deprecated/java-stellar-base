package org.stellar.base;

import junit.framework.TestCase;
import org.junit.*;

public class StellarKeypairTest extends TestCase {

  private static final String SEED = "1123740522f11bfef6b3671f51e159ccf589ccf8965262dd5f97d1721d383dd4";

  @org.junit.Test
  public void testSign() {
    String expectedSig = "587d4b472eeef7d07aafcd0b049640b0bb3f39784118c2e2b73a04fa2f64c9c538b4b2d0f5335e968a480021fdc23e98c0ddf424cb15d8131df8cb6c4bb58309";
    StellarKeypair keypair = StellarKeypair.fromSecretSeed(Util.hexToBytes(SEED));
    String data = "hello world";
    byte[] sig = keypair.sign(data.getBytes());
    Assert.assertArrayEquals(Util.hexToBytes(expectedSig), sig);
  }

  @org.junit.Test
  public void testVerifyTrue() throws Exception {
    String sig = "587d4b472eeef7d07aafcd0b049640b0bb3f39784118c2e2b73a04fa2f64c9c538b4b2d0f5335e968a480021fdc23e98c0ddf424cb15d8131df8cb6c4bb58309";
    String data = "hello world";
    StellarKeypair keypair = StellarKeypair.fromSecretSeed(Util.hexToBytes(SEED));
    Assert.assertTrue(keypair.verify(data.getBytes(), Util.hexToBytes(sig)));
  }

  @org.junit.Test
  public void testVerifyFalse() throws Exception {
    String badSig = "687d4b472eeef7d07aafcd0b049640b0bb3f39784118c2e2b73a04fa2f64c9c538b4b2d0f5335e968a480021fdc23e98c0ddf424cb15d8131df8cb6c4bb58309";
    byte[] corrupt = {0x00};
    String data = "hello world";
    StellarKeypair keypair = StellarKeypair.fromSecretSeed(Util.hexToBytes(SEED));
    Assert.assertFalse(keypair.verify(data.getBytes(), Util.hexToBytes(badSig)));
    Assert.assertFalse(keypair.verify(data.getBytes(), corrupt));
  }
}
