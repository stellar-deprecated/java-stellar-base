// Automatically generated on 2015-04-10T00:48:12-07:00
// DO NOT EDIT or your changes may be overwritten

package org.stellar.base.xdr;


import java.io.IOException;

// === xdr source ============================================================

//  struct CreateOfferSuccessResult
//  {
//      // offers that got claimed while creating this offer
//      ClaimOfferAtom offersClaimed<>;
//  
//      union switch (CreateOfferEffect effect)
//      {
//      case CREATE_OFFER_CREATED:
//      case CREATE_OFFER_UPDATED:
//          OfferEntry offer;
//      default:
//          void;
//      }
//      offer;
//  };

//  ===========================================================================
public class CreateOfferSuccessResult  {
  public CreateOfferSuccessResult () {}
  private ClaimOfferAtom[] offersClaimed;
  public ClaimOfferAtom[] getoffersClaimed() {
    return this.offersClaimed;
  }
  public void setoffersClaimed(ClaimOfferAtom[] value) {
    this.offersClaimed = value;
  }
  private CreateOfferSuccessResultOffer offer;
  public CreateOfferSuccessResultOffer getoffer() {
    return this.offer;
  }
  public void setoffer(CreateOfferSuccessResultOffer value) {
    this.offer = value;
  }
  public static void encode(XdrDataOutputStream stream, CreateOfferSuccessResult encodedCreateOfferSuccessResult) throws IOException{
    int offersClaimedsize = encodedCreateOfferSuccessResult.getoffersClaimed().length;
    stream.writeInt(offersClaimedsize);
    for (int i = 0; i < offersClaimedsize; i++) {
      ClaimOfferAtom.encode(stream, encodedCreateOfferSuccessResult.offersClaimed[i]);
    }
    CreateOfferSuccessResultOffer.encode(stream, encodedCreateOfferSuccessResult.offer);
  }
  public static CreateOfferSuccessResult decode(XdrDataInputStream stream) throws IOException {
    CreateOfferSuccessResult decodedCreateOfferSuccessResult = new CreateOfferSuccessResult();
    int offersClaimedsize = stream.readInt();
    decodedCreateOfferSuccessResult.offersClaimed = new ClaimOfferAtom[offersClaimedsize];
    for (int i = 0; i < offersClaimedsize; i++) {
      decodedCreateOfferSuccessResult.offersClaimed[i] = ClaimOfferAtom.decode(stream);
    }
    decodedCreateOfferSuccessResult.offer = CreateOfferSuccessResultOffer.decode(stream);
    return decodedCreateOfferSuccessResult;
  }

  public static class CreateOfferSuccessResultOffer {
    public CreateOfferSuccessResultOffer () {}
    CreateOfferEffect effect;
    public CreateOfferEffect getDiscriminant() {
      return this.effect;
    }
    public void setDiscriminant(CreateOfferEffect value) {
      this.effect = value;
    }
    private OfferEntry offer;
    public OfferEntry getoffer() {
      return this.offer;
    }
    public void setoffer(OfferEntry value) {
      this.offer = value;
    }
    public static void encode(XdrDataOutputStream stream, CreateOfferSuccessResultOffer encodedCreateOfferSuccessResultOffer) throws IOException {
      switch (encodedCreateOfferSuccessResultOffer.getDiscriminant()) {
    case CREATE_OFFER_CREATED:
    case CREATE_OFFER_UPDATED:
    OfferEntry.encode(stream, encodedCreateOfferSuccessResultOffer.offer);
    break;
    default:
    break;
    }
    }
    public static CreateOfferSuccessResultOffer decode(XdrDataInputStream stream) throws IOException {
      CreateOfferSuccessResultOffer decodedCreateOfferSuccessResultOffer = new CreateOfferSuccessResultOffer();
      switch (decodedCreateOfferSuccessResultOffer.getDiscriminant()) {
    case CREATE_OFFER_CREATED:
    case CREATE_OFFER_UPDATED:
    decodedCreateOfferSuccessResultOffer.offer = OfferEntry.decode(stream);
    break;
    default:
    break;
    }
      return decodedCreateOfferSuccessResultOffer;
    }

  }
}
