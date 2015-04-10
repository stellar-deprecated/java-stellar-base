// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union ChangeTrustResult switch (ChangeTrustResultCode code)
//  {
//  case CHANGE_TRUST_SUCCESS:
//      void;
//  default:
//      void;
//  };

//  ===========================================================================
public class ChangeTrustResult  {
  public ChangeTrustResult () {}
  ChangeTrustResultCode code;
  public ChangeTrustResultCode getDiscriminant() {
    return this.code;
  }
  public void setDiscriminant(ChangeTrustResultCode value) {
    this.code = value;
  }
  public static void encode(XdrDataOutputStream stream, ChangeTrustResult encodedChangeTrustResult) throws IOException {
    switch (encodedChangeTrustResult.getDiscriminant()) {
  case CHANGE_TRUST_SUCCESS:
  break;
  default:
  break;
  }
  }
  public static ChangeTrustResult decode(XdrDataInputStream stream) throws IOException {
    ChangeTrustResult decodedChangeTrustResult = new ChangeTrustResult();
    switch (decodedChangeTrustResult.getDiscriminant()) {
  case CHANGE_TRUST_SUCCESS:
  break;
  default:
  break;
  }
    return decodedChangeTrustResult;
  }
}
