// Automatically generated on 2015-06-24T13:46:48-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  union LedgerEntryChange switch (LedgerEntryChangeType type)
//  {
//  case LEDGER_ENTRY_CREATED:
//      LedgerEntry created;
//  case LEDGER_ENTRY_UPDATED:
//      LedgerEntry updated;
//  case LEDGER_ENTRY_REMOVED:
//      LedgerKey removed;
//  };

//  ===========================================================================
public class LedgerEntryChange  {
  public LedgerEntryChange () {}
  LedgerEntryChangeType type;
  public LedgerEntryChangeType getDiscriminant() {
    return this.type;
  }
  public void setDiscriminant(LedgerEntryChangeType value) {
    this.type = value;
  }
  private LedgerEntry created;
  public LedgerEntry getcreated() {
    return this.created;
  }
  public void setcreated(LedgerEntry value) {
    this.created = value;
  }
  private LedgerEntry updated;
  public LedgerEntry getupdated() {
    return this.updated;
  }
  public void setupdated(LedgerEntry value) {
    this.updated = value;
  }
  private LedgerKey removed;
  public LedgerKey getremoved() {
    return this.removed;
  }
  public void setremoved(LedgerKey value) {
    this.removed = value;
  }
  public static void encode(XdrDataOutputStream stream, LedgerEntryChange encodedLedgerEntryChange) throws IOException {
    stream.writeInt(encodedLedgerEntryChange.getDiscriminant().getValue());
    switch (encodedLedgerEntryChange.getDiscriminant()) {
  case LEDGER_ENTRY_CREATED:
  LedgerEntry.encode(stream, encodedLedgerEntryChange.created);
  break;
  case LEDGER_ENTRY_UPDATED:
  LedgerEntry.encode(stream, encodedLedgerEntryChange.updated);
  break;
  case LEDGER_ENTRY_REMOVED:
  LedgerKey.encode(stream, encodedLedgerEntryChange.removed);
  break;
  }
  }
  public static LedgerEntryChange decode(XdrDataInputStream stream) throws IOException {
    LedgerEntryChange decodedLedgerEntryChange = new LedgerEntryChange();
    switch (decodedLedgerEntryChange.getDiscriminant()) {
  case LEDGER_ENTRY_CREATED:
  decodedLedgerEntryChange.created = LedgerEntry.decode(stream);
  break;
  case LEDGER_ENTRY_UPDATED:
  decodedLedgerEntryChange.updated = LedgerEntry.decode(stream);
  break;
  case LEDGER_ENTRY_REMOVED:
  decodedLedgerEntryChange.removed = LedgerKey.decode(stream);
  break;
  }
    return decodedLedgerEntryChange;
  }
}
