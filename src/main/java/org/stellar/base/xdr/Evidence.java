// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  typedef opaque Evidence<>;

//  ===========================================================================
public class Evidence  {
  private byte[] Evidence;
  public byte[] getEvidence() {
    return this.Evidence;
  }
  public void setEvidence(byte[] value) {
    this.Evidence = value;
  }
  public static void encode(XdrDataOutputStream stream, Evidence  encodedEvidence) throws IOException {
  int Evidencesize = encodedEvidence.Evidence.length;
  stream.writeInt(Evidencesize);
  stream.write(encodedEvidence.getEvidence(), 0, Evidencesize);
  }
  public static Evidence decode(XdrDataInputStream stream) throws IOException {
    Evidence decodedEvidence = new Evidence();
  int Evidencesize = stream.readInt();
  decodedEvidence.Evidence = new byte[Evidencesize];
  stream.read(decodedEvidence.Evidence, 0, Evidencesize);
    return decodedEvidence;
  }
}
