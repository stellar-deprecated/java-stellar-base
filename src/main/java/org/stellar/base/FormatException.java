package org.stellar.base;

/**
 * Indicates that there was a problem decoding strkey encoded string.
 * @see Keypair
 */
public class FormatException extends RuntimeException {
  public FormatException() {
    super();
  }

  public FormatException(String message) {
    super(message);
  }
}
