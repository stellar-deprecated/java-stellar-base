// Automatically generated on 2015-07-21T12:54:50-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct OperationMeta
//  {
//      LedgerEntryChanges changes;
//  };

//  ===========================================================================
public class OperationMeta  {
  public OperationMeta () {}
  private LedgerEntryChanges changes;
  public LedgerEntryChanges getchanges() {
    return this.changes;
  }
  public void setchanges(LedgerEntryChanges value) {
    this.changes = value;
  }
  public static void encode(XdrDataOutputStream stream, OperationMeta encodedOperationMeta) throws IOException{
    LedgerEntryChanges.encode(stream, encodedOperationMeta.changes);
  }
  public static OperationMeta decode(XdrDataInputStream stream) throws IOException {
    OperationMeta decodedOperationMeta = new OperationMeta();
    decodedOperationMeta.changes = LedgerEntryChanges.decode(stream);
    return decodedOperationMeta;
  }
}
