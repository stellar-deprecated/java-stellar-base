package org.stellar.base;

/**
 * Created by andrewrogers on 6/17/15.
 */
@SuppressWarnings("serial")
public class AddressFormatException extends Exception {
  public AddressFormatException() {
    super();
  }

  public AddressFormatException(String message) {
    super(message);
  }
}
