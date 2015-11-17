import org.stellar.base.*;

public class StellarExample {
  public static void main(String [] args) {
    // Uncoment when using Public network
    //Network.usePublicNetwork();

    // GCXA7A4DQSJDDJSI3UADO7WK7XLQVSFD2PMDBPOTDPRY3LAKU5O7VEJN
    Keypair sourceKeypair = Keypair.fromSecretSeed("SBBC3BQTSA5O7JFJQAQTWPVHZ2OCSDOXWQTZB7IA3WSIPIEZICHB2V6L");
    Keypair destination = Keypair.fromAddress("GBXXA7ASHOWRMKAGKX32SFVUFDU4R34VXEC2HVHI23ZCU2NWW66UZSZJ");

    System.out.println(sourceKeypair.getSecretSeed());
    System.out.println(sourceKeypair.getAddress());

    Account sourceAccount = new Account(sourceKeypair, 3009998980382720L);

    PaymentOperation operation = new PaymentOperation.Builder(destination, new AssetTypeNative(), 20000000).build();
    Transaction transaction = new Transaction.Builder(sourceAccount)
      .addOperation(operation)
      .addMemo(Memo.text("Java FTW!"))
      .build();

    transaction.sign(sourceKeypair);

    System.out.println(transaction.toEnvelopeXdrBase64());
  }
}
