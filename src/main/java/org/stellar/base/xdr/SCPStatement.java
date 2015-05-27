// Automatically generated on 2015-05-27T10:24:45-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct SCPStatement
//  {
//      uint64 slotIndex;   // i
//      SCPBallot ballot;   // b
//      Hash quorumSetHash; // D
//  
//      union switch (SCPStatementType type)
//      {
//      case PREPARING:
//          struct
//          {
//              SCPBallot excepted<>; // B_c
//              SCPBallot* prepared;  // p
//          } prepare;
//      case PREPARED:
//      case COMMITTING:
//      case COMMITTED:
//          void;
//      }
//      pledges;
//  };

//  ===========================================================================
public class SCPStatement  {
  public SCPStatement () {}
  private Uint64 slotIndex;
  public Uint64 getslotIndex() {
    return this.slotIndex;
  }
  public void setslotIndex(Uint64 value) {
    this.slotIndex = value;
  }
  private SCPBallot ballot;
  public SCPBallot getballot() {
    return this.ballot;
  }
  public void setballot(SCPBallot value) {
    this.ballot = value;
  }
  private Hash quorumSetHash;
  public Hash getquorumSetHash() {
    return this.quorumSetHash;
  }
  public void setquorumSetHash(Hash value) {
    this.quorumSetHash = value;
  }
  private SCPStatementPledges pledges;
  public SCPStatementPledges getpledges() {
    return this.pledges;
  }
  public void setpledges(SCPStatementPledges value) {
    this.pledges = value;
  }
  public static void encode(XdrDataOutputStream stream, SCPStatement encodedSCPStatement) throws IOException{
    Uint64.encode(stream, encodedSCPStatement.slotIndex);
    SCPBallot.encode(stream, encodedSCPStatement.ballot);
    Hash.encode(stream, encodedSCPStatement.quorumSetHash);
    SCPStatementPledges.encode(stream, encodedSCPStatement.pledges);
  }
  public static SCPStatement decode(XdrDataInputStream stream) throws IOException {
    SCPStatement decodedSCPStatement = new SCPStatement();
    decodedSCPStatement.slotIndex = Uint64.decode(stream);
    decodedSCPStatement.ballot = SCPBallot.decode(stream);
    decodedSCPStatement.quorumSetHash = Hash.decode(stream);
    decodedSCPStatement.pledges = SCPStatementPledges.decode(stream);
    return decodedSCPStatement;
  }

  public static class SCPStatementPledges {
    public SCPStatementPledges () {}
    SCPStatementType type;
    public SCPStatementType getDiscriminant() {
      return this.type;
    }
    public void setDiscriminant(SCPStatementType value) {
      this.type = value;
    }
    private SCPStatementPrepare prepare;
    public SCPStatementPrepare getprepare() {
      return this.prepare;
    }
    public void setprepare(SCPStatementPrepare value) {
      this.prepare = value;
    }
    public static void encode(XdrDataOutputStream stream, SCPStatementPledges encodedSCPStatementPledges) throws IOException {
      switch (encodedSCPStatementPledges.getDiscriminant()) {
    case PREPARING:
    SCPStatementPrepare.encode(stream, encodedSCPStatementPledges.prepare);
    break;
    case PREPARED:
    case COMMITTING:
    case COMMITTED:
    break;
    }
    }
    public static SCPStatementPledges decode(XdrDataInputStream stream) throws IOException {
      SCPStatementPledges decodedSCPStatementPledges = new SCPStatementPledges();
      switch (decodedSCPStatementPledges.getDiscriminant()) {
    case PREPARING:
    decodedSCPStatementPledges.prepare = SCPStatementPrepare.decode(stream);
    break;
    case PREPARED:
    case COMMITTING:
    case COMMITTED:
    break;
    }
      return decodedSCPStatementPledges;
    }

    public static class SCPStatementPrepare {
      public SCPStatementPrepare () {}
      private SCPBallot[] excepted;
      public SCPBallot[] getexcepted() {
        return this.excepted;
      }
      public void setexcepted(SCPBallot[] value) {
        this.excepted = value;
      }
      private SCPBallot prepared;
      public SCPBallot getprepared() {
        return this.prepared;
      }
      public void setprepared(SCPBallot value) {
        this.prepared = value;
      }
      public static void encode(XdrDataOutputStream stream, SCPStatementPrepare encodedSCPStatementPrepare) throws IOException{
        int exceptedsize = encodedSCPStatementPrepare.getexcepted().length;
        stream.writeInt(exceptedsize);
        for (int i = 0; i < exceptedsize; i++) {
          SCPBallot.encode(stream, encodedSCPStatementPrepare.excepted[i]);
        }
        if (encodedSCPStatementPrepare.prepared != null) {
        SCPBallot.encode(stream, encodedSCPStatementPrepare.prepared);
        }
      }
      public static SCPStatementPrepare decode(XdrDataInputStream stream) throws IOException {
        SCPStatementPrepare decodedSCPStatementPrepare = new SCPStatementPrepare();
        int exceptedsize = stream.readInt();
        decodedSCPStatementPrepare.excepted = new SCPBallot[exceptedsize];
        for (int i = 0; i < exceptedsize; i++) {
          decodedSCPStatementPrepare.excepted[i] = SCPBallot.decode(stream);
        }
        int preparedPresent = stream.readInt();
        if (preparedPresent != 0) {
        decodedSCPStatementPrepare.prepared = SCPBallot.decode(stream);
        }
        return decodedSCPStatementPrepare;
      }

    }
  }
}
