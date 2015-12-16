import org.stellar.base.*;

public class StellarExample {
  public static void main(String [] args) {
    // Uncoment when using Public network
    //Network.usePublicNetwork();

    // GCXA7A4DQSJDDJSI3UADO7WK7XLQVSFD2PMDBPOTDPRY3LAKU5O7VEJN
    KeyPair sourceKeyPair = KeyPair.fromSecretSeed("SBBC3BQTSA5O7JFJQAQTWPVHZ2OCSDOXWQTZB7IA3WSIPIEZICHB2V6L");
    KeyPair destination = KeyPair.fromAddress("GBXXA7ASHOWRMKAGKX32SFVUFDU4R34VXEC2HVHI23ZCU2NWW66UZSZJ");

    System.out.println(sourceKeyPair.getSecretSeed());
    System.out.println(sourceKeyPair.getAddress());

    Account sourceAccount = new Account(sourceKeyPair, 3009998980382720L);

    PaymentOperation operation = new PaymentOperation.Builder(destination, new AssetTypeNative(), 20000000L).build();
    Transaction transaction = new Transaction.Builder(sourceAccount)
      .addOperation(operation)
      .addMemo(Memo.text("Java FTW!"))
      .build();

    transaction.sign(sourceKeyPair);

    System.out.println(transaction.toEnvelopeXdrBase64());
  }
}
