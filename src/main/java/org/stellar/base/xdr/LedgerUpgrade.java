// Automatically generated on 2015-11-05T11:21:06-08:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union LedgerUpgrade switch (LedgerUpgradeType type)
//  {
//  case LEDGER_UPGRADE_VERSION:
//      uint32 newLedgerVersion; // update ledgerVersion
//  case LEDGER_UPGRADE_BASE_FEE:
//      uint32 newBaseFee; // update baseFee
//  case LEDGER_UPGRADE_MAX_TX_SET_SIZE:
//      uint32 newMaxTxSetSize; // update maxTxSetSize
//  };

//  ===========================================================================
public class LedgerUpgrade  {
  public LedgerUpgrade () {}
  LedgerUpgradeType type;
  public LedgerUpgradeType getDiscriminant() {
    return this.type;
  }
  public void setDiscriminant(LedgerUpgradeType value) {
    this.type = value;
  }
  private Uint32 newLedgerVersion;
  public Uint32 getnewLedgerVersion() {
    return this.newLedgerVersion;
  }
  public void setnewLedgerVersion(Uint32 value) {
    this.newLedgerVersion = value;
  }
  private Uint32 newBaseFee;
  public Uint32 getnewBaseFee() {
    return this.newBaseFee;
  }
  public void setnewBaseFee(Uint32 value) {
    this.newBaseFee = value;
  }
  private Uint32 newMaxTxSetSize;
  public Uint32 getnewMaxTxSetSize() {
    return this.newMaxTxSetSize;
  }
  public void setnewMaxTxSetSize(Uint32 value) {
    this.newMaxTxSetSize = value;
  }
  public static void encode(XdrDataOutputStream stream, LedgerUpgrade encodedLedgerUpgrade) throws IOException {
  stream.writeInt(encodedLedgerUpgrade.getDiscriminant().getValue());
  switch (encodedLedgerUpgrade.getDiscriminant()) {
  case LEDGER_UPGRADE_VERSION:
  Uint32.encode(stream, encodedLedgerUpgrade.newLedgerVersion);
  break;
  case LEDGER_UPGRADE_BASE_FEE:
  Uint32.encode(stream, encodedLedgerUpgrade.newBaseFee);
  break;
  case LEDGER_UPGRADE_MAX_TX_SET_SIZE:
  Uint32.encode(stream, encodedLedgerUpgrade.newMaxTxSetSize);
  break;
  }
  }
  public static LedgerUpgrade decode(XdrDataInputStream stream) throws IOException {
    LedgerUpgrade decodedLedgerUpgrade = new LedgerUpgrade();
    switch (decodedLedgerUpgrade.getDiscriminant()) {
  case LEDGER_UPGRADE_VERSION:
  decodedLedgerUpgrade.newLedgerVersion = Uint32.decode(stream);
  break;
  case LEDGER_UPGRADE_BASE_FEE:
  decodedLedgerUpgrade.newBaseFee = Uint32.decode(stream);
  break;
  case LEDGER_UPGRADE_MAX_TX_SET_SIZE:
  decodedLedgerUpgrade.newMaxTxSetSize = Uint32.decode(stream);
  break;
  }
    return decodedLedgerUpgrade;
  }
}
