import org.stellar.base._;

object StellarExample {
  def main(args: Array[String]) {
    // Uncoment when using Public network
    //Network.usePublicNetwork();

    // GCXA7A4DQSJDDJSI3UADO7WK7XLQVSFD2PMDBPOTDPRY3LAKU5O7VEJN
    val sourceKeypair = Keypair.fromSecretSeed("SBBC3BQTSA5O7JFJQAQTWPVHZ2OCSDOXWQTZB7IA3WSIPIEZICHB2V6L");
    val destination = Keypair.fromAddress("GBXXA7ASHOWRMKAGKX32SFVUFDU4R34VXEC2HVHI23ZCU2NWW66UZSZJ");

    println(sourceKeypair.getSecretSeed());
    println(sourceKeypair.getAddress());

    var sourceAccount = new Account(sourceKeypair, 3009998980382720L);

    val operation = new PaymentOperation.Builder(destination, new AssetTypeNative(), 20000000).build();
    val transaction = new Transaction.Builder(sourceAccount)
      .addOperation(operation)
      .addMemo(Memo.text("Scala FTW!"))
      .build();

    transaction.sign(sourceKeypair);

    println(transaction.toEnvelopeXdrBase64());
  }
}
