// Automatically generated on 2015-06-16T15:35:11-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct SCPNomination
//  {
//      Hash quorumSetHash; // D
//      Value votes<>; // X
//      Value accepted<>; // Y
//  };

//  ===========================================================================
public class SCPNomination  {
  public SCPNomination () {}
  private Hash quorumSetHash;
  public Hash getquorumSetHash() {
    return this.quorumSetHash;
  }
  public void setquorumSetHash(Hash value) {
    this.quorumSetHash = value;
  }
  private Value[] votes;
  public Value[] getvotes() {
    return this.votes;
  }
  public void setvotes(Value[] value) {
    this.votes = value;
  }
  private Value[] accepted;
  public Value[] getaccepted() {
    return this.accepted;
  }
  public void setaccepted(Value[] value) {
    this.accepted = value;
  }
  public static void encode(XdrDataOutputStream stream, SCPNomination encodedSCPNomination) throws IOException{
    Hash.encode(stream, encodedSCPNomination.quorumSetHash);
    int votessize = encodedSCPNomination.getvotes().length;
    stream.writeInt(votessize);
    for (int i = 0; i < votessize; i++) {
      Value.encode(stream, encodedSCPNomination.votes[i]);
    }
    int acceptedsize = encodedSCPNomination.getaccepted().length;
    stream.writeInt(acceptedsize);
    for (int i = 0; i < acceptedsize; i++) {
      Value.encode(stream, encodedSCPNomination.accepted[i]);
    }
  }
  public static SCPNomination decode(XdrDataInputStream stream) throws IOException {
    SCPNomination decodedSCPNomination = new SCPNomination();
    decodedSCPNomination.quorumSetHash = Hash.decode(stream);
    int votessize = stream.readInt();
    decodedSCPNomination.votes = new Value[votessize];
    for (int i = 0; i < votessize; i++) {
      decodedSCPNomination.votes[i] = Value.decode(stream);
    }
    int acceptedsize = stream.readInt();
    decodedSCPNomination.accepted = new Value[acceptedsize];
    for (int i = 0; i < acceptedsize; i++) {
      decodedSCPNomination.accepted[i] = Value.decode(stream);
    }
    return decodedSCPNomination;
  }
}
