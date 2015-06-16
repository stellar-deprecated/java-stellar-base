// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Operation
//  {
//      // sourceAccount is the account used to run the operation
//      // if not set, the runtime defaults to "account" specified at
//      // the transaction level
//      AccountID* sourceAccount;
//  
//      union switch (OperationType type)
//      {
//      case CREATE_ACCOUNT:
//          CreateAccountOp createAccountOp;
//      case PAYMENT:
//          PaymentOp paymentOp;
//      case PATH_PAYMENT:
//          PathPaymentOp pathPaymentOp;
//      case MANAGE_OFFER:
//          ManageOfferOp manageOfferOp;
//      case CREATE_PASSIVE_OFFER:
//          CreatePassiveOfferOp createPassiveOfferOp;
//      case SET_OPTIONS:
//          SetOptionsOp setOptionsOp;
//      case CHANGE_TRUST:
//          ChangeTrustOp changeTrustOp;
//      case ALLOW_TRUST:
//          AllowTrustOp allowTrustOp;
//      case ACCOUNT_MERGE:
//          uint256 destination;
//      case INFLATION:
//          void;
//      }
//      body;
//  };

//  ===========================================================================
public class Operation  {
  public Operation () {}
  private AccountID sourceAccount;
  public AccountID getsourceAccount() {
    return this.sourceAccount;
  }
  public void setsourceAccount(AccountID value) {
    this.sourceAccount = value;
  }
  private OperationBody body;
  public OperationBody getbody() {
    return this.body;
  }
  public void setbody(OperationBody value) {
    this.body = value;
  }
  public static void encode(XdrDataOutputStream stream, Operation encodedOperation) throws IOException{
    if (encodedOperation.sourceAccount != null) {
    AccountID.encode(stream, encodedOperation.sourceAccount);
    }
    OperationBody.encode(stream, encodedOperation.body);
  }
  public static Operation decode(XdrDataInputStream stream) throws IOException {
    Operation decodedOperation = new Operation();
    int sourceAccountPresent = stream.readInt();
    if (sourceAccountPresent != 0) {
    decodedOperation.sourceAccount = AccountID.decode(stream);
    }
    decodedOperation.body = OperationBody.decode(stream);
    return decodedOperation;
  }

  public static class OperationBody {
    public OperationBody () {}
    OperationType type;
    public OperationType getDiscriminant() {
      return this.type;
    }
    public void setDiscriminant(OperationType value) {
      this.type = value;
    }
    private CreateAccountOp createAccountOp;
    public CreateAccountOp getcreateAccountOp() {
      return this.createAccountOp;
    }
    public void setcreateAccountOp(CreateAccountOp value) {
      this.createAccountOp = value;
    }
    private PaymentOp paymentOp;
    public PaymentOp getpaymentOp() {
      return this.paymentOp;
    }
    public void setpaymentOp(PaymentOp value) {
      this.paymentOp = value;
    }
    private PathPaymentOp pathPaymentOp;
    public PathPaymentOp getpathPaymentOp() {
      return this.pathPaymentOp;
    }
    public void setpathPaymentOp(PathPaymentOp value) {
      this.pathPaymentOp = value;
    }
    private ManageOfferOp manageOfferOp;
    public ManageOfferOp getmanageOfferOp() {
      return this.manageOfferOp;
    }
    public void setmanageOfferOp(ManageOfferOp value) {
      this.manageOfferOp = value;
    }
    private CreatePassiveOfferOp createPassiveOfferOp;
    public CreatePassiveOfferOp getcreatePassiveOfferOp() {
      return this.createPassiveOfferOp;
    }
    public void setcreatePassiveOfferOp(CreatePassiveOfferOp value) {
      this.createPassiveOfferOp = value;
    }
    private SetOptionsOp setOptionsOp;
    public SetOptionsOp getsetOptionsOp() {
      return this.setOptionsOp;
    }
    public void setsetOptionsOp(SetOptionsOp value) {
      this.setOptionsOp = value;
    }
    private ChangeTrustOp changeTrustOp;
    public ChangeTrustOp getchangeTrustOp() {
      return this.changeTrustOp;
    }
    public void setchangeTrustOp(ChangeTrustOp value) {
      this.changeTrustOp = value;
    }
    private AllowTrustOp allowTrustOp;
    public AllowTrustOp getallowTrustOp() {
      return this.allowTrustOp;
    }
    public void setallowTrustOp(AllowTrustOp value) {
      this.allowTrustOp = value;
    }
    private Uint256 destination;
    public Uint256 getdestination() {
      return this.destination;
    }
    public void setdestination(Uint256 value) {
      this.destination = value;
    }
    public static void encode(XdrDataOutputStream stream, OperationBody encodedOperationBody) throws IOException {
      switch (encodedOperationBody.getDiscriminant()) {
    case CREATE_ACCOUNT:
    CreateAccountOp.encode(stream, encodedOperationBody.createAccountOp);
    break;
    case PAYMENT:
    PaymentOp.encode(stream, encodedOperationBody.paymentOp);
    break;
    case PATH_PAYMENT:
    PathPaymentOp.encode(stream, encodedOperationBody.pathPaymentOp);
    break;
    case MANAGE_OFFER:
    ManageOfferOp.encode(stream, encodedOperationBody.manageOfferOp);
    break;
    case CREATE_PASSIVE_OFFER:
    CreatePassiveOfferOp.encode(stream, encodedOperationBody.createPassiveOfferOp);
    break;
    case SET_OPTIONS:
    SetOptionsOp.encode(stream, encodedOperationBody.setOptionsOp);
    break;
    case CHANGE_TRUST:
    ChangeTrustOp.encode(stream, encodedOperationBody.changeTrustOp);
    break;
    case ALLOW_TRUST:
    AllowTrustOp.encode(stream, encodedOperationBody.allowTrustOp);
    break;
    case ACCOUNT_MERGE:
    Uint256.encode(stream, encodedOperationBody.destination);
    break;
    case INFLATION:
    break;
    }
    }
    public static OperationBody decode(XdrDataInputStream stream) throws IOException {
      OperationBody decodedOperationBody = new OperationBody();
      switch (decodedOperationBody.getDiscriminant()) {
    case CREATE_ACCOUNT:
    decodedOperationBody.createAccountOp = CreateAccountOp.decode(stream);
    break;
    case PAYMENT:
    decodedOperationBody.paymentOp = PaymentOp.decode(stream);
    break;
    case PATH_PAYMENT:
    decodedOperationBody.pathPaymentOp = PathPaymentOp.decode(stream);
    break;
    case MANAGE_OFFER:
    decodedOperationBody.manageOfferOp = ManageOfferOp.decode(stream);
    break;
    case CREATE_PASSIVE_OFFER:
    decodedOperationBody.createPassiveOfferOp = CreatePassiveOfferOp.decode(stream);
    break;
    case SET_OPTIONS:
    decodedOperationBody.setOptionsOp = SetOptionsOp.decode(stream);
    break;
    case CHANGE_TRUST:
    decodedOperationBody.changeTrustOp = ChangeTrustOp.decode(stream);
    break;
    case ALLOW_TRUST:
    decodedOperationBody.allowTrustOp = AllowTrustOp.decode(stream);
    break;
    case ACCOUNT_MERGE:
    decodedOperationBody.destination = Uint256.decode(stream);
    break;
    case INFLATION:
    break;
    }
      return decodedOperationBody;
    }

  }
}
