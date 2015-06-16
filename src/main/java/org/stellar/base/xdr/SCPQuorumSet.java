// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct SCPQuorumSet
//  {
//      uint32 threshold;
//  	Hash validators<>;
//      SCPQuorumSet innerSets<>;
//  };

//  ===========================================================================
public class SCPQuorumSet  {
  public SCPQuorumSet () {}
  private Uint32 threshold;
  public Uint32 getthreshold() {
    return this.threshold;
  }
  public void setthreshold(Uint32 value) {
    this.threshold = value;
  }
  private Hash[] validators;
  public Hash[] getvalidators() {
    return this.validators;
  }
  public void setvalidators(Hash[] value) {
    this.validators = value;
  }
  private SCPQuorumSet[] innerSets;
  public SCPQuorumSet[] getinnerSets() {
    return this.innerSets;
  }
  public void setinnerSets(SCPQuorumSet[] value) {
    this.innerSets = value;
  }
  public static void encode(XdrDataOutputStream stream, SCPQuorumSet encodedSCPQuorumSet) throws IOException{
    Uint32.encode(stream, encodedSCPQuorumSet.threshold);
    int validatorssize = encodedSCPQuorumSet.getvalidators().length;
    stream.writeInt(validatorssize);
    for (int i = 0; i < validatorssize; i++) {
      Hash.encode(stream, encodedSCPQuorumSet.validators[i]);
    }
    int innerSetssize = encodedSCPQuorumSet.getinnerSets().length;
    stream.writeInt(innerSetssize);
    for (int i = 0; i < innerSetssize; i++) {
      SCPQuorumSet.encode(stream, encodedSCPQuorumSet.innerSets[i]);
    }
  }
  public static SCPQuorumSet decode(XdrDataInputStream stream) throws IOException {
    SCPQuorumSet decodedSCPQuorumSet = new SCPQuorumSet();
    decodedSCPQuorumSet.threshold = Uint32.decode(stream);
    int validatorssize = stream.readInt();
    decodedSCPQuorumSet.validators = new Hash[validatorssize];
    for (int i = 0; i < validatorssize; i++) {
      decodedSCPQuorumSet.validators[i] = Hash.decode(stream);
    }
    int innerSetssize = stream.readInt();
    decodedSCPQuorumSet.innerSets = new SCPQuorumSet[innerSetssize];
    for (int i = 0; i < innerSetssize; i++) {
      decodedSCPQuorumSet.innerSets[i] = SCPQuorumSet.decode(stream);
    }
    return decodedSCPQuorumSet;
  }
}
