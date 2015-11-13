package org.stellar.base;

public class NotEnoughSignaturesException extends RuntimeException {
    public NotEnoughSignaturesException() {
        super();
    }

    public NotEnoughSignaturesException(String message) {
        super(message);
    }
}
