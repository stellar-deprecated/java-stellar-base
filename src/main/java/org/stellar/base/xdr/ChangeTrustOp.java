// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct ChangeTrustOp
//  {
//      Currency line;
//  
//      // if limit is set to 0, deletes the trust line
//      int64 limit;
//  };

//  ===========================================================================
public class ChangeTrustOp  {
  public ChangeTrustOp () {}
  private Currency line;
  public Currency getline() {
    return this.line;
  }
  public void setline(Currency value) {
    this.line = value;
  }
  private Int64 limit;
  public Int64 getlimit() {
    return this.limit;
  }
  public void setlimit(Int64 value) {
    this.limit = value;
  }
  public static void encode(XdrDataOutputStream stream, ChangeTrustOp encodedChangeTrustOp) throws IOException{
    Currency.encode(stream, encodedChangeTrustOp.line);
    Int64.encode(stream, encodedChangeTrustOp.limit);
  }
  public static ChangeTrustOp decode(XdrDataInputStream stream) throws IOException {
    ChangeTrustOp decodedChangeTrustOp = new ChangeTrustOp();
    decodedChangeTrustOp.line = Currency.decode(stream);
    decodedChangeTrustOp.limit = Int64.decode(stream);
    return decodedChangeTrustOp;
  }
}
