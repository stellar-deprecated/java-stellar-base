package org.stellar.base;

import org.stellar.base.xdr.OperationType;

/**
 * Represents <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html#inflation" target="_blank">Inflation</a> operation.
 * @see <a href="https://www.stellar.org/developers/learn/concepts/list-of-operations.html" target="_blank">List of Operations</a>
 */
public class InflationOperation extends Operation {
    @Override
    org.stellar.base.xdr.Operation.OperationBody toOperationBody() {
        org.stellar.base.xdr.Operation.OperationBody body = new org.stellar.base.xdr.Operation.OperationBody();
        body.setDiscriminant(OperationType.INFLATION);
        return body;
    }
}
