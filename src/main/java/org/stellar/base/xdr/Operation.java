// Automatically generated on 2015-04-10T00:48:12-07:00
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
//      case PAYMENT:
//          PaymentOp paymentOp;
//      case CREATE_OFFER:
//          CreateOfferOp createOfferOp;
//      case SET_OPTIONS:
//          SetOptionsOp setOptionsOp;
//      case CHANGE_TRUST:
//          ChangeTrustOp changeTrustOp;
//      case ALLOW_TRUST:
//          AllowTrustOp allowTrustOp;
//      case ACCOUNT_MERGE:
//          uint256 destination;
//      case INFLATION:
//          uint32 inflationSeq;
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
    private PaymentOp paymentOp;
    public PaymentOp getpaymentOp() {
      return this.paymentOp;
    }
    public void setpaymentOp(PaymentOp value) {
      this.paymentOp = value;
    }
    private CreateOfferOp createOfferOp;
    public CreateOfferOp getcreateOfferOp() {
      return this.createOfferOp;
    }
    public void setcreateOfferOp(CreateOfferOp value) {
      this.createOfferOp = value;
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
    private Uint32 inflationSeq;
    public Uint32 getinflationSeq() {
      return this.inflationSeq;
    }
    public void setinflationSeq(Uint32 value) {
      this.inflationSeq = value;
    }
    public static void encode(XdrDataOutputStream stream, OperationBody encodedOperationBody) throws IOException {
      switch (encodedOperationBody.getDiscriminant()) {
    case PAYMENT:
    PaymentOp.encode(stream, encodedOperationBody.paymentOp);
    break;
    case CREATE_OFFER:
    CreateOfferOp.encode(stream, encodedOperationBody.createOfferOp);
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
    Uint32.encode(stream, encodedOperationBody.inflationSeq);
    break;
    }
    }
    public static OperationBody decode(XdrDataInputStream stream) throws IOException {
      OperationBody decodedOperationBody = new OperationBody();
      switch (decodedOperationBody.getDiscriminant()) {
    case PAYMENT:
    decodedOperationBody.paymentOp = PaymentOp.decode(stream);
    break;
    case CREATE_OFFER:
    decodedOperationBody.createOfferOp = CreateOfferOp.decode(stream);
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
    decodedOperationBody.inflationSeq = Uint32.decode(stream);
    break;
    }
      return decodedOperationBody;
    }

  }
}
