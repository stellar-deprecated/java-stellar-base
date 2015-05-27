// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct LedgerHeader
//  {
//      Hash previousLedgerHash; // hash of the previous ledger header
//      Hash txSetHash;          // the tx set that was SCP confirmed
//      Hash txSetResultHash;    // the TransactionResultSet that led to this ledger
//      Hash bucketListHash;     // hash of the ledger state
//  
//      uint32 ledgerSeq; // sequence number of this ledger
//      uint64 closeTime; // network close time
//  
//      int64 totalCoins; // total number of stroops in existence
//  
//      int64 feePool;       // fees burned since last inflation run
//      uint32 inflationSeq; // inflation sequence number
//  
//      uint64 idPool; // last used global ID, used for generating objects
//  
//      int32 baseFee;     // base fee per operation in stroops
//      int32 baseReserve; // account base reserve in stroops
//  };

//  ===========================================================================
public class LedgerHeader  {
  public LedgerHeader () {}
  private Hash previousLedgerHash;
  public Hash getpreviousLedgerHash() {
    return this.previousLedgerHash;
  }
  public void setpreviousLedgerHash(Hash value) {
    this.previousLedgerHash = value;
  }
  private Hash txSetHash;
  public Hash gettxSetHash() {
    return this.txSetHash;
  }
  public void settxSetHash(Hash value) {
    this.txSetHash = value;
  }
  private Hash txSetResultHash;
  public Hash gettxSetResultHash() {
    return this.txSetResultHash;
  }
  public void settxSetResultHash(Hash value) {
    this.txSetResultHash = value;
  }
  private Hash bucketListHash;
  public Hash getbucketListHash() {
    return this.bucketListHash;
  }
  public void setbucketListHash(Hash value) {
    this.bucketListHash = value;
  }
  private Uint32 ledgerSeq;
  public Uint32 getledgerSeq() {
    return this.ledgerSeq;
  }
  public void setledgerSeq(Uint32 value) {
    this.ledgerSeq = value;
  }
  private Uint64 closeTime;
  public Uint64 getcloseTime() {
    return this.closeTime;
  }
  public void setcloseTime(Uint64 value) {
    this.closeTime = value;
  }
  private Int64 totalCoins;
  public Int64 gettotalCoins() {
    return this.totalCoins;
  }
  public void settotalCoins(Int64 value) {
    this.totalCoins = value;
  }
  private Int64 feePool;
  public Int64 getfeePool() {
    return this.feePool;
  }
  public void setfeePool(Int64 value) {
    this.feePool = value;
  }
  private Uint32 inflationSeq;
  public Uint32 getinflationSeq() {
    return this.inflationSeq;
  }
  public void setinflationSeq(Uint32 value) {
    this.inflationSeq = value;
  }
  private Uint64 idPool;
  public Uint64 getidPool() {
    return this.idPool;
  }
  public void setidPool(Uint64 value) {
    this.idPool = value;
  }
  private Int32 baseFee;
  public Int32 getbaseFee() {
    return this.baseFee;
  }
  public void setbaseFee(Int32 value) {
    this.baseFee = value;
  }
  private Int32 baseReserve;
  public Int32 getbaseReserve() {
    return this.baseReserve;
  }
  public void setbaseReserve(Int32 value) {
    this.baseReserve = value;
  }
  public static void encode(XdrDataOutputStream stream, LedgerHeader encodedLedgerHeader) throws IOException{
    Hash.encode(stream, encodedLedgerHeader.previousLedgerHash);
    Hash.encode(stream, encodedLedgerHeader.txSetHash);
    Hash.encode(stream, encodedLedgerHeader.txSetResultHash);
    Hash.encode(stream, encodedLedgerHeader.bucketListHash);
    Uint32.encode(stream, encodedLedgerHeader.ledgerSeq);
    Uint64.encode(stream, encodedLedgerHeader.closeTime);
    Int64.encode(stream, encodedLedgerHeader.totalCoins);
    Int64.encode(stream, encodedLedgerHeader.feePool);
    Uint32.encode(stream, encodedLedgerHeader.inflationSeq);
    Uint64.encode(stream, encodedLedgerHeader.idPool);
    Int32.encode(stream, encodedLedgerHeader.baseFee);
    Int32.encode(stream, encodedLedgerHeader.baseReserve);
  }
  public static LedgerHeader decode(XdrDataInputStream stream) throws IOException {
    LedgerHeader decodedLedgerHeader = new LedgerHeader();
    decodedLedgerHeader.previousLedgerHash = Hash.decode(stream);
    decodedLedgerHeader.txSetHash = Hash.decode(stream);
    decodedLedgerHeader.txSetResultHash = Hash.decode(stream);
    decodedLedgerHeader.bucketListHash = Hash.decode(stream);
    decodedLedgerHeader.ledgerSeq = Uint32.decode(stream);
    decodedLedgerHeader.closeTime = Uint64.decode(stream);
    decodedLedgerHeader.totalCoins = Int64.decode(stream);
    decodedLedgerHeader.feePool = Int64.decode(stream);
    decodedLedgerHeader.inflationSeq = Uint32.decode(stream);
    decodedLedgerHeader.idPool = Uint64.decode(stream);
    decodedLedgerHeader.baseFee = Int32.decode(stream);
    decodedLedgerHeader.baseReserve = Int32.decode(stream);
    return decodedLedgerHeader;
  }
}
