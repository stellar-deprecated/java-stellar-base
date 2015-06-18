package org.stellar.base;

import static com.google.common.base.Preconditions.checkArgument;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * <p>Base58 is a way to encode Stellar addresses and secret seeds.
 * Each encoded address and secret takes the form:
 * <p/>
 * <pre>[one version byte] [payload bytes] [4 checksum bytes]</pre>
 * <p/>
 * <p>The result of this form is then encoded in Base58.</p>
 */
public class Base58 {
  public static final char[] ALPHABET = "gsphnaf39wBUDNEGHJKLM4PQRST7VWXYZ2bcdeCr65jkm8oFqi1tuvAxyz".toCharArray();
  // to avoid quadratic time letter index lookups when decoding addresses
  private static final int[] INDEXES = new int[128];
  static {
    for (int i = 0; i < INDEXES.length; i++) {
      INDEXES[i] = -1;
    }
    for (int i = 0; i < ALPHABET.length; i++) {
      INDEXES[ALPHABET[i]] = i;
    }
  }

  public static final byte ADDRESS_VERSION_BYTE = 0x00;
  public static final byte SECRET_VERSION_BYTE = 0x21;

  /**
   * Encode a public key's bytes to a readable, base58 encoded, versioned, and checked
   * Stellar address.
   * @param data The public key bytes from the Keypair.
   * @return The Base58 versioned checked encoded String.
   */
  public static String encodeStellarAddress(byte[] data) {
    return encodeVersionedChecked(ADDRESS_VERSION_BYTE, data);
  }

  /**
   * Encode a private key's bytes to a readable, base58 encoded, versioned, and checked
   * Stellar secret seed.
   * @param data The private key seed bytes from the Keypair.
   * @return The Base58 versioned checked encoded String.
   */
  public static String encodeStellarSecretSeed(byte[] data) {
    return encodeVersionedChecked(SECRET_VERSION_BYTE, data);
  }

  public static String encodeVersionedChecked(byte version, byte[] data) {
    byte[] payload = new byte[1 + data.length + 4];
    payload[0] = version;
    System.arraycopy(data, 0, payload, 1, data.length);
    byte[] hash = doubleDigest(payload, 0, data.length + 1);
    System.arraycopy(hash, 0, payload, data.length + 1, 4);
    return encode(payload);
  }

  /**
   * Decodes a Stellar address to its raw public key byte array.
   * @param data The stellar address.
   * @return The public key bytes.
   */
  public static byte[] decodeStellarAddress(String data) throws AddressFormatException {
    return decodeVersionedChecked(ADDRESS_VERSION_BYTE, data);
  }

  /**
   *  Decodes a Stellar secret seed to its raw secret key seed byte array.
   * @param data The Stellar secret seed.
   * @return The private key seed bytes.
   */
  public static byte[] decodeStellarSecretSeed(String data) throws AddressFormatException {
    return decodeVersionedChecked(SECRET_VERSION_BYTE, data);
  }

  public static byte[] decodeVersionedChecked(byte version, String data)
      throws AddressFormatException {
    byte[] decoded = decode(data);
    checkArgument(decoded[0] == version, "Given version does not match expected version");
    byte[] payload = new byte[decoded.length - 5];
    System.arraycopy(decoded, 1, payload, 0, decoded.length - 5);
    return payload;
  }

  // ported from https://github.com/bitcoin/bitcoin/blob/master/src/base58.cpp
  public static String encode(byte[] data) {
    // skip and count leading zeros
    int zeros = 0;
    while (zeros < data.length && data[zeros] == 0) {
      zeros++;
    }
    // Allocate enough space in big-endian base58 representation.
    byte[] b58 = new byte[(data.length - zeros) * 138 / 100 + 1]; // log(256) / log(58), rounded up.
    // process bytes
    int index = zeros;
    while (index != data.length) {
      int carry = data[index] & 0xFF;
      for (int j = b58.length - 1; j >= 0; j--) {
        carry += 256 * (int) b58[j];
        b58[j] = (byte) (carry % 58);
        carry /= 58;
      }
      index++;
    }
    String result = "";
    // add our leading zeros
    for (int i = 0; i < zeros; i++) {
      result += ALPHABET[0];
    }
    // skip ahead to the first non zero b58 digit
    index = 0;
    while (index < b58.length && b58[index] == 0) {
      index++;
    }
    while (index < b58.length) {
      result += ALPHABET[b58[index]];
      index++;
    }
    return result;
  }

  // ported from https://github.com/bitcoin/bitcoin/blob/master/src/base58.cpp
  public static byte[] decode(String data) throws AddressFormatException {
    data = data.trim();
    char[] letters = data.toCharArray();
    int zeros = 0;
    while (letters[zeros] == ALPHABET[0]) {
      zeros++;
    }
    // Allocate enough space in big-endian base256 representation.
    byte[] b256 = new byte[letters.length * 733 / 1000 + 1]; // log(58) / log(256), rounded up.
    int index = 0;
    while (index != letters.length) {
      char ch = letters[index];

      int carry = -1;
      if (ch >= 0 && ch < 128) {
        carry = INDEXES[ch];
      }
      if (carry < 0) {
        throw new AddressFormatException("Illegal character " + ch + " at " + index);
      }
      for (int j = b256.length - 1; j >= 0; j--) {
        carry += 58 * (b256[j] & 0xFF);
        b256[j] = (byte) (carry % 256);
        carry /= 256;
      }
      index++;
    }
    // skip ahead to the first non zero b58 digit
    int b58Index = 0;
    while (b58Index < b256.length - 1 && b256[b58Index] == 0) {
      b58Index++;
    }
    byte[] result = new byte[(b256.length - b58Index) + zeros];
    // add our leading zeros
    for (int i = 0; i < zeros; i++) {
      result[i] = 0x00;
    }
    index = zeros;
    while (b58Index < b256.length) {
      result[index] = b256[b58Index];
      index++;
      b58Index++;
    }
    return result;
  }

  private static byte[] doubleDigest(byte[] input, int offset, int length) {
    MessageDigest digest;
    try {
      digest = MessageDigest.getInstance("SHA-256");
    } catch (NoSuchAlgorithmException e) {
      throw new RuntimeException(e);
    }
    digest.update(input, offset, length);
    byte[] first = digest.digest();
    return digest.digest(first);
  }
}
