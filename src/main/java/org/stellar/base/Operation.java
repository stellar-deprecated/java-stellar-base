package org.stellar.base;

import org.stellar.base.xdr.AccountID;

abstract class Operation {

  private StellarKeypair mSourceAccount;

  public org.stellar.base.xdr.Operation toXdr() {
    org.stellar.base.xdr.Operation xdr = new org.stellar.base.xdr.Operation();
    if (getSourceAccount() != null) {
      AccountID sourceAccount = new AccountID();
      sourceAccount.setAccountID(getSourceAccount().getXdrPublicKey());
      xdr.setsourceAccount(sourceAccount);
    }
    xdr.setbody(toOperationBody());
    return xdr;
  }

  public static Operation fromXdr(org.stellar.base.xdr.Operation xdr) {
    org.stellar.base.xdr.Operation.OperationBody body = xdr.getbody();
    Operation operation = null;
    switch (body.getDiscriminant()) {
      case CREATE_ACCOUNT:
        operation = new CreateAccountOperation.Builder(body.getcreateAccountOp()).build();
        break;
      case PAYMENT:
        operation = new PaymentOperation.Builder(body.getpaymentOp()).build();
        break;
      case PATH_PAYMENT:
        operation = new PathPaymentOperation.Builder(body.getpathPaymentOp()).build();
        break;
      case CHANGE_TRUST:
        operation = new ChangeTrustOperation.Builder(body.getchangeTrustOp()).build();
        break;
      case ALLOW_TRUST:
        operation = new AllowTrustOperation.Builder(body.getallowTrustOp()).build();
        break;
      case SET_OPTIONS:
        operation = new SetOptionsOperation.Builder(body.getsetOptionsOp()).build();
        break;
      default:
        throw new RuntimeException("Unknown operation body " + body.getDiscriminant());
    }
    if (xdr.getsourceAccount() != null) {
      operation.setSourceAccount(StellarKeypair.fromXdrPublicKey(xdr.getsourceAccount().getAccountID()));
    }
    return operation;
  }

  public StellarKeypair getSourceAccount() {
    return mSourceAccount;
  }

  void setSourceAccount(StellarKeypair keypair) {
    mSourceAccount = keypair;
  }

  abstract org.stellar.base.xdr.Operation.OperationBody toOperationBody();
}
