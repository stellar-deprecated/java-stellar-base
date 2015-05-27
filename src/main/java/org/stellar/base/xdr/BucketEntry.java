// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union BucketEntry switch (BucketEntryType type)
//  {
//  case LIVEENTRY:
//      LedgerEntry liveEntry;
//  
//  case DEADENTRY:
//      LedgerKey deadEntry;
//  };

//  ===========================================================================
public class BucketEntry  {
  public BucketEntry () {}
  BucketEntryType type;
  public BucketEntryType getDiscriminant() {
    return this.type;
  }
  public void setDiscriminant(BucketEntryType value) {
    this.type = value;
  }
  private LedgerEntry liveEntry;
  public LedgerEntry getliveEntry() {
    return this.liveEntry;
  }
  public void setliveEntry(LedgerEntry value) {
    this.liveEntry = value;
  }
  private LedgerKey deadEntry;
  public LedgerKey getdeadEntry() {
    return this.deadEntry;
  }
  public void setdeadEntry(LedgerKey value) {
    this.deadEntry = value;
  }
  public static void encode(XdrDataOutputStream stream, BucketEntry encodedBucketEntry) throws IOException {
    switch (encodedBucketEntry.getDiscriminant()) {
  case LIVEENTRY:
  LedgerEntry.encode(stream, encodedBucketEntry.liveEntry);
  break;
  case DEADENTRY:
  LedgerKey.encode(stream, encodedBucketEntry.deadEntry);
  break;
  }
  }
  public static BucketEntry decode(XdrDataInputStream stream) throws IOException {
    BucketEntry decodedBucketEntry = new BucketEntry();
    switch (decodedBucketEntry.getDiscriminant()) {
  case LIVEENTRY:
  decodedBucketEntry.liveEntry = LedgerEntry.decode(stream);
  break;
  case DEADENTRY:
  decodedBucketEntry.deadEntry = LedgerKey.decode(stream);
  break;
  }
    return decodedBucketEntry;
  }
}
