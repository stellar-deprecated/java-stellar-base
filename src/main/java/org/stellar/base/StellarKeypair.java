package org.stellar.base;

import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.SignatureException;
import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveSpec;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec;
import org.stellar.base.xdr.CryptoKeyType;
import org.stellar.base.xdr.PublicKey;
import org.stellar.base.xdr.Uint256;

/**
 * Holds a Stellar keypair.
 */
public class StellarKeypair {

  private static final EdDSANamedCurveSpec ed25519 = EdDSANamedCurveTable.getByName("ed25519-sha-512");

  private final EdDSAPublicKey mPublicKey;
  private final EdDSAPrivateKey mPrivateKey;

  /**
   * Creates a new StellarKeypair without a private key. Useful to simply verify a signature from a
   * given public address.
   * @param publicKey
   */
  public StellarKeypair(EdDSAPublicKey publicKey) {
    this(publicKey, null);
  }

  /**
   * Creates a new StellarKeypair from the given public and private keys.
   * @param publicKey
   * @param privateKey
   */
  public StellarKeypair(EdDSAPublicKey publicKey, EdDSAPrivateKey privateKey) {
    if (publicKey == null) {
      throw new IllegalArgumentException("Public key cannot be null");
    }
    mPublicKey = publicKey;
    mPrivateKey = privateKey;
  }

  /**
   * Creates a new Stellar Keypair from a base 58 encoded Stellar secret seed.
   * @param seed The base 58 encoded Stellar secret seed.
   * @return {@link StellarKeypair}
   * @throws AddressFormatException
   */
  public static StellarKeypair fromSecretSeed(String seed) throws AddressFormatException {
    byte[] decoded = Base58.decodeStellarSecretSeed(seed);
    return fromSecretSeed(decoded);
  }

  /**
   * Creates a new Stellar keypair from a 32 byte secret seed.
   * @param seed The 32 byte secret seed.
   * @return {@link StellarKeypair}
   */
  public static StellarKeypair fromSecretSeed(byte[] seed) {
    EdDSAPrivateKeySpec privKeySpec = new EdDSAPrivateKeySpec(seed, ed25519);
    EdDSAPublicKeySpec publicKeySpec = new EdDSAPublicKeySpec(privKeySpec.getA().toByteArray(), ed25519);
    return new StellarKeypair(new EdDSAPublicKey(publicKeySpec), new EdDSAPrivateKey(privKeySpec));
  }

  /**
   * Creates a new Stellar Keypair from a base 58 encoded Stellar address.
   * @param address The base 58 encoded Stellar address.
   * @return {@link StellarKeypair}
   * @throws AddressFormatException
   */
  public static StellarKeypair fromAddress(String address) throws AddressFormatException {
    byte[] decoded = Base58.decodeStellarAddress(address);
    return fromPublicKey(decoded);
  }

  /**
   * Creates a new Stellar keypair from a 32 byte address.
   * @param address The 32 byte address.
   * @return {@link StellarKeypair}
   */
  public static StellarKeypair fromPublicKey(byte[] address) {
    EdDSAPublicKeySpec publicKeySpec = new EdDSAPublicKeySpec(address, ed25519);
    return new StellarKeypair(new EdDSAPublicKey(publicKeySpec));
  }

  /**
   * @return a random Stellar keypair.
   */
  public static StellarKeypair random() {
    KeyPair keypair = new KeyPairGenerator().generateKeyPair();
    return new StellarKeypair((EdDSAPublicKey) keypair.getPublic(), (EdDSAPrivateKey) keypair.getPrivate());
  }

  /**
   * @return the human readable address encoded in base 58.
   */
  public String getAddress() {
    return Base58.encodeStellarAddress(mPublicKey.getAbyte());
  }

  /**
   * @return the human readable secret seed encoded in base 58.
   */
  public String getSecretSeed() {
    return Base58.encodeStellarSecretSeed(mPrivateKey.getSeed());
  }

  public byte[] getPublicKey() {
    return mPublicKey.getAbyte();
  }

  public PublicKey getXdrPublicKey() {
    PublicKey publicKey = new PublicKey();
    publicKey.setDiscriminant(CryptoKeyType.KEY_TYPE_ED25519);
    Uint256 uint256 = new Uint256();
    uint256.setuint256(getPublicKey());
    publicKey.seted25519(uint256);
    return publicKey;
  }

  public static StellarKeypair fromXdrPublicKey(PublicKey key) {
    return StellarKeypair.fromPublicKey(key.geted25519().getuint256());
  }

  /**
   * Sign the provided data with the keypair's private key.
   * @param data The data to sign.
   * @return signed bytes, null if the private key for this keypair is null.
   */
  public byte[] sign(byte[] data) {
    if (mPrivateKey == null) {
      return null;
    }
    try {
      Signature sgr = new EdDSAEngine(MessageDigest.getInstance("SHA-512"));
      sgr.initSign(mPrivateKey);
      sgr.update(data);
      return sgr.sign();
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Verify the provided data and signature match this keypair's public key.
   * @param data The data that was signed.
   * @param signature The signature.
   * @return True if they match, false otherwise.
   * @throws SignatureException If the signature length is wrong.
   */
  public boolean verify(byte[] data, byte[] signature) {
    try {
      Signature sgr = new EdDSAEngine(MessageDigest.getInstance("SHA-512"));
      sgr.initVerify(mPublicKey);
      sgr.update(data);
      return sgr.verify(signature);
    } catch (SignatureException e) {
      return false;
    } catch (GeneralSecurityException e) {
      throw new RuntimeException(e);
    }
  }

  @Override
  public boolean equals(Object obj) {
    return super.equals(obj);
  }
}