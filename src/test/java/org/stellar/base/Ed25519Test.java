package org.stellar.base;

import java.security.KeyPair;
import net.i2p.crypto.eddsa.KeyPairGenerator;
import org.junit.Test;

public class Ed25519Test {

  @Test
  public void test() {
    KeyPairGenerator keypairGenerator = new KeyPairGenerator();
    KeyPair keypair = keypairGenerator.generateKeyPair();
  }
}
