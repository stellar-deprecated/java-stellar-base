package org.stellar.base;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class Base58Test extends TestCase {

  public static final byte[] MASTER_KEY_PUBLIC_KEY = {
      (byte) 0x89, (byte) 0x9b, (byte) 0x28, (byte) 0x40, (byte) 0xed, (byte) 0x56, (byte) 0x36, (byte) 0xc5,
      (byte) 0x6d, (byte) 0xdc, (byte) 0x5f, (byte) 0x14, (byte) 0xb2, (byte) 0x39, (byte) 0x75, (byte) 0xf7,
      (byte) 0x9f, (byte) 0x1b, (byte) 0xa2, (byte) 0x38, (byte) 0x8d, (byte) 0x26, (byte) 0x94, (byte) 0xe4,
      (byte) 0xc5, (byte) 0x6e, (byte) 0xcd, (byte) 0xdd, (byte) 0xc9, (byte) 0x60, (byte) 0xe5, (byte) 0xef
  };

  public static final byte[] MASTER_KEY_PRIVATE_KEY = {
      (byte) 0x61, (byte) 0x6c, (byte) 0x6c, (byte) 0x6d, (byte) 0x79, (byte) 0x6c, (byte) 0x69, (byte) 0x66,
      (byte) 0x65, (byte) 0x6d, (byte) 0x79, (byte) 0x68, (byte) 0x65, (byte) 0x61, (byte) 0x72, (byte) 0x74,
      (byte) 0x68, (byte) 0x61, (byte) 0x73, (byte) 0x62, (byte) 0x65, (byte) 0x65, (byte) 0x6e, (byte) 0x73,
      (byte) 0x65, (byte) 0x61, (byte) 0x72, (byte) 0x63, (byte) 0x68, (byte) 0x69, (byte) 0x6e, (byte) 0x67
  };

  public static final String MASTER_KEY_ADDRRESS = "gspbxqXqEUZkiCCEFFCN9Vu4FLucdjLLdLcsV6E82Qc1T7ehsTC";
  public static final String MASTER_KEY_SEED = "sft74k3MagHG6iF36yeSytQzCCLsJ2Fo9K4YJpQCECwgoUobc4v";

  @Test
  public void testEncode() {
    byte[] testbytes = {0x39};
    assertEquals("z", Base58.encode(testbytes));

    byte[] testbytes2 = {0x00, 0x00, 0x00, 0x39};
    assertEquals("gggz", Base58.encode(testbytes2));

    byte[] testbytes3 = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    assertEquals("s7zHL", Base58.encode(testbytes3));
  }

  @Test
  public void testEncodeAddress() {
    String encoded = Base58.encodeStellarAddress(MASTER_KEY_PUBLIC_KEY);
    assertEquals(encoded, MASTER_KEY_ADDRRESS);
  }

  @Test
  public void testEncodeSecretSeed() {
    String encoded = Base58.encodeStellarSecretSeed(MASTER_KEY_PRIVATE_KEY);
    assertEquals(encoded, MASTER_KEY_SEED);
  }

  @Test
  public void testDecode() throws FormatException {
    String testString1 = "z";
    byte[] expected1 = {0x39};
    Assert.assertArrayEquals(expected1, Base58.decode(testString1));

    String testString2 = "gggz";
    byte[] expected2 = {0x00, 0x00, 0x00, 0x39};
    Assert.assertArrayEquals(expected2, Base58.decode(testString2));

    String testString3 = "s7zHL";
    byte[] expected3 = {(byte) 0xFF, (byte) 0xFF, (byte) 0xFF};
    Assert.assertArrayEquals(expected3, Base58.decode(testString3));
  }

  @Test
  public void testDecodeAddress() throws FormatException {
    Assert.assertArrayEquals(MASTER_KEY_PUBLIC_KEY,
        Base58.decodeStellarAddress(MASTER_KEY_ADDRRESS));
  }

  @Test
  public void testDecodeSecretSeed() throws FormatException {
    Assert.assertArrayEquals(MASTER_KEY_PRIVATE_KEY,
        Base58.decodeStellarSecretSeed(MASTER_KEY_SEED));
  }
}
