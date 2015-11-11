package org.stellar.base;

public class AssetCodeLengthInvalidException extends RuntimeException {
    public AssetCodeLengthInvalidException() {
        super();
    }

    public AssetCodeLengthInvalidException(String message) {
        super(message);
    }
}