// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct Error
//  {
//      ErrorCode code;
//      string msg<100>;
//  };

//  ===========================================================================
public class Error  {
  public Error () {}
  private ErrorCode code;
  public ErrorCode getcode() {
    return this.code;
  }
  public void setcode(ErrorCode value) {
    this.code = value;
  }
  private String msg;
  public String getmsg() {
    return this.msg;
  }
  public void setmsg(String value) {
    this.msg = value;
  }
  public static void encode(XdrDataOutputStream stream, Error encodedError) throws IOException{
    ErrorCode.encode(stream, encodedError.code);
    stream.writeString(encodedError.msg);
  }
  public static Error decode(XdrDataInputStream stream) throws IOException {
    Error decodedError = new Error();
    decodedError.code = ErrorCode.decode(stream);
    decodedError.msg = stream.readString();
    return decodedError;
  }
}
