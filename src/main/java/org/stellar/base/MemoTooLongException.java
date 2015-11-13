package org.stellar.base;

public class MemoTooLongException extends RuntimeException {
    public MemoTooLongException() {
        super();
    }

    public MemoTooLongException(String message) {
        super(message);
    }
}
