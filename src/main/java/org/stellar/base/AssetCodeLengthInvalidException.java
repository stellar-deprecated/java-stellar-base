package org.stellar.base;

/**
 * Created by bartek on 09.11.15.
 */
public class AssetCodeLengthInvalidException extends Exception {
    public AssetCodeLengthInvalidException() {
        super();
    }

    public AssetCodeLengthInvalidException(String message) {
        super(message);
    }
}