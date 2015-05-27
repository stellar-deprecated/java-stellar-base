// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct LedgerHeaderHistoryEntry
//  {
//      Hash hash;
//      LedgerHeader header;
//  };

//  ===========================================================================
public class LedgerHeaderHistoryEntry  {
  public LedgerHeaderHistoryEntry () {}
  private Hash hash;
  public Hash gethash() {
    return this.hash;
  }
  public void sethash(Hash value) {
    this.hash = value;
  }
  private LedgerHeader header;
  public LedgerHeader getheader() {
    return this.header;
  }
  public void setheader(LedgerHeader value) {
    this.header = value;
  }
  public static void encode(XdrDataOutputStream stream, LedgerHeaderHistoryEntry encodedLedgerHeaderHistoryEntry) throws IOException{
    Hash.encode(stream, encodedLedgerHeaderHistoryEntry.hash);
    LedgerHeader.encode(stream, encodedLedgerHeaderHistoryEntry.header);
  }
  public static LedgerHeaderHistoryEntry decode(XdrDataInputStream stream) throws IOException {
    LedgerHeaderHistoryEntry decodedLedgerHeaderHistoryEntry = new LedgerHeaderHistoryEntry();
    decodedLedgerHeaderHistoryEntry.hash = Hash.decode(stream);
    decodedLedgerHeaderHistoryEntry.header = LedgerHeader.decode(stream);
    return decodedLedgerHeaderHistoryEntry;
  }
}
