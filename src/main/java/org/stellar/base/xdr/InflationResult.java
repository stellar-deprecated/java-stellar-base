// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union InflationResult switch (InflationResultCode code)
//  {
//  case INFLATION_SUCCESS:
//      inflationPayout payouts<>;
//  default:
//      void;
//  };

//  ===========================================================================
public class InflationResult  {
  public InflationResult () {}
  InflationResultCode code;
  public InflationResultCode getDiscriminant() {
    return this.code;
  }
  public void setDiscriminant(InflationResultCode value) {
    this.code = value;
  }
  private InflationPayout[] payouts;
  public InflationPayout[] getpayouts() {
    return this.payouts;
  }
  public void setpayouts(InflationPayout[] value) {
    this.payouts = value;
  }
  public static void encode(XdrDataOutputStream stream, InflationResult encodedInflationResult) throws IOException {
    switch (encodedInflationResult.getDiscriminant()) {
  case INFLATION_SUCCESS:
  int payoutssize = encodedInflationResult.getpayouts().length;
  stream.writeInt(payoutssize);
  for (int i = 0; i < payoutssize; i++) {
    InflationPayout.encode(stream, encodedInflationResult.payouts[i]);
  }
  break;
  default:
  break;
  }
  }
  public static InflationResult decode(XdrDataInputStream stream) throws IOException {
    InflationResult decodedInflationResult = new InflationResult();
    switch (decodedInflationResult.getDiscriminant()) {
  case INFLATION_SUCCESS:
  int payoutssize = stream.readInt();
  decodedInflationResult.payouts = new InflationPayout[payoutssize];
  for (int i = 0; i < payoutssize; i++) {
    decodedInflationResult.payouts[i] = InflationPayout.decode(stream);
  }
  break;
  default:
  break;
  }
    return decodedInflationResult;
  }
}
