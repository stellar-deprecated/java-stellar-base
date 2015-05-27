// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct TimeBounds
//  {
//      uint64 minTime;
//      uint64 maxTime;
//  };

//  ===========================================================================
public class TimeBounds  {
  public TimeBounds () {}
  private Uint64 minTime;
  public Uint64 getminTime() {
    return this.minTime;
  }
  public void setminTime(Uint64 value) {
    this.minTime = value;
  }
  private Uint64 maxTime;
  public Uint64 getmaxTime() {
    return this.maxTime;
  }
  public void setmaxTime(Uint64 value) {
    this.maxTime = value;
  }
  public static void encode(XdrDataOutputStream stream, TimeBounds encodedTimeBounds) throws IOException{
    Uint64.encode(stream, encodedTimeBounds.minTime);
    Uint64.encode(stream, encodedTimeBounds.maxTime);
  }
  public static TimeBounds decode(XdrDataInputStream stream) throws IOException {
    TimeBounds decodedTimeBounds = new TimeBounds();
    decodedTimeBounds.minTime = Uint64.decode(stream);
    decodedTimeBounds.maxTime = Uint64.decode(stream);
    return decodedTimeBounds;
  }
}
