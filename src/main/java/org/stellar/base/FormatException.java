package org.stellar.base;

/**
 * Created by andrewrogers on 6/17/15.
 */
@SuppressWarnings("serial")
public class FormatException extends Exception {
  public FormatException() {
    super();
  }

  public FormatException(String message) {
    super(message);
  }
}
