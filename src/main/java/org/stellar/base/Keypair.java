package org.stellar.base;

import net.i2p.crypto.eddsa.EdDSAEngine;
import net.i2p.crypto.eddsa.EdDSAPrivateKey;
import net.i2p.crypto.eddsa.EdDSAPublicKey;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveSpec;
import net.i2p.crypto.eddsa.spec.EdDSANamedCurveTable;
import net.i2p.crypto.eddsa.spec.EdDSAPrivateKeySpec;
import net.i2p.crypto.eddsa.spec.EdDSAPublicKeySpec;

import org.stellar.base.xdr.CryptoKeyType;
import org.stellar.base.xdr.DecoratedSignature;
import org.stellar.base.xdr.PublicKey;
import org.stellar.base.xdr.SignatureHint;
import org.stellar.base.xdr.Uint256;
import org.stellar.base.xdr.XdrDataOutputStream;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.Signature;
import java.security.SignatureException;
import java.util.Arrays;

/**
 * Holds a Stellar keypair.
 */
public class Keypair {

  private static final EdDSANamedCurveSpec ed25519 = EdDSANamedCurveTable.getByName("ed25519-sha-512");

  private final EdDSAPublicKey mPublicKey;
  private final EdDSAPrivateKey mPrivateKey;

  /**
   * Creates a new Keypair without a private key. Useful to simply verify a signature from a
   * given public address.
   * @param publicKey
   */
  public Keypair(EdDSAPublicKey publicKey) {
    this(publicKey, null);
  }

  /**
   * Creates a new Keypair from the given public and private keys.
   * @param publicKey
   * @param privateKey
   */
  public Keypair(EdDSAPublicKey publicKey, EdDSAPrivateKey privateKey) {
    if (publicKey == null) {
      throw new IllegalArgumentException("Public key cannot be null");
    }
    mPublicKey = publicKey;
    mPrivateKey = privateKey;
  }

  /**
   * Creates a new Stellar Keypair from a strkey encoded Stellar secret seed.
   * @param seed The strkey encoded Stellar secret seed.
   * @return {@link Keypair}
   */
  public static Keypair fromSecretSeed(String seed) {
    byte[] decoded = StrKey.decodeStellarSecretSeed(seed);
    return fromSecretSeed(decoded);
  }

  /**
   * Creates a new Stellar keypair from a 32 byte secret seed.
   * @param seed The 32 byte secret seed.
   * @return {@link Keypair}
   */
  public static Keypair fromSecretSeed(byte[] seed) {
    EdDSAPrivateKeySpec privKeySpec = new EdDSAPrivateKeySpec(seed, ed25519);
    EdDSAPublicKeySpec publicKeySpec = new EdDSAPublicKeySpec(privKeySpec.getA().toByteArray(), ed25519);
    return new Keypair(new EdDSAPublicKey(publicKeySpec), new EdDSAPrivateKey(privKeySpec));
  }

  /**
   * Creates a new Stellar Keypair from a strkey encoded Stellar address.
   * @param address The strkey encoded Stellar address.
   * @return {@link Keypair}
   */
  public static Keypair fromAddress(String address) {
    byte[] decoded = StrKey.decodeStellarAddress(address);
    return fromPublicKey(decoded);
  }

  /**
   * Creates a new Stellar keypair from a 32 byte address.
   * @param address The 32 byte address.
   * @return {@link Keypair}
   */
  public static Keypair fromPublicKey(byte[] address) {
    EdDSAPublicKeySpec publicKeySpec = new EdDSAPublicKeySpec(address, ed25519);
    return new Keypair(new EdDSAPublicKey(publicKeySpec));
  }

  /**
   * Generates a random Stellar keypair.
   * @return a random Stellar keypair.
   */
  public static Keypair random() {
    KeyPair keypair = new KeyPairGenerator().generateKeyPair();
    return new Keypair((EdDSAPublicKey) keypair.getPublic(), (EdDSAPrivateKey) keypair.getPrivate());
  }

  /**
   * Returns the human readable address encoded in strkey.
   */
  public String getAddress() {
    return StrKey.encodeStellarAddress(mPublicKey.getAbyte());
  }

  /**
   * Returns the human readable secret seed encoded in strkey.
   */
  public String getSecretSeed() {
    return StrKey.encodeStellarSecretSeed(mPrivateKey.getSeed());
  }

  public byte[] getPublicKey() {
    return mPublicKey.getAbyte();
  }

  public SignatureHint getSignatureHint() {
    try {
      ByteArrayOutputStream publicKeyBytesStream = new ByteArrayOutputStream();
      XdrDataOutputStream xdrOutputStream = new XdrDataOutputStream(publicKeyBytesStream);
      PublicKey.encode(xdrOutputStream, this.getXdrPublicKey());
      byte[] publicKeyBytes = publicKeyBytesStream.toByteArray();
      byte[] signatureHintBytes = Arrays.copyOfRange(publicKeyBytes, publicKeyBytes.length - 4, publicKeyBytes.length);

      SignatureHint signatureHint = new SignatureHint();
      signatureHint.setSignatureHint(signatureHintBytes);
      return signatureHint;
    } catch (IOException e) {
      throw new AssertionError(e);
    }
  }

  public PublicKey getXdrPublicKey() {
    PublicKey publicKey = new PublicKey();
    publicKey.setDiscriminant(CryptoKeyType.KEY_TYPE_ED25519);
    Uint256 uint256 = new Uint256();
    uint256.setUint256(getPublicKey());
    publicKey.setEd25519(uint256);
    return publicKey;
  }

  public static Keypair fromXdrPublicKey(PublicKey key) {
    return Keypair.fromPublicKey(key.getEd25519().getUint256());
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
   * Sign the provided data with the keypair's private key and returns {@link DecoratedSignature}.
   * @param data
   */
  public DecoratedSignature signDecorated(byte[] data) {
    byte[] signatureBytes = this.sign(data);

    org.stellar.base.xdr.Signature signature = new org.stellar.base.xdr.Signature();
    signature.setSignature(signatureBytes);

    DecoratedSignature decoratedSignature = new DecoratedSignature();
    decoratedSignature.setHint(this.getSignatureHint());
    decoratedSignature.setSignature(signature);
    return decoratedSignature;
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
