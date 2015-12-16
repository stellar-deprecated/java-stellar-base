import org.stellar.base.CreateAccountOperation;
import org.stellar.base.Keypair;
import org.stellar.base.Memo;
import org.stellar.base.Transaction;
import org.stellar.sdk.Account;
import org.stellar.sdk.Server;
import org.stellar.sdk.SubmitTransactionResponse;

import java.io.IOException;

/**
 * This example illustrates how to create a new account using an existing account.  The new account
 * receives 20 lumens from the existing account.
 *
 * @author Myrle Krantz
 */
public class CreateAccount {
  public static void main(String[] args) throws IOException {
    final Server stellarServer = new Server("https://horizon-testnet.stellar.org");

    // Create a public and private key for the new account.
    final Keypair newAccountKeyPair = Keypair.random();
    System.out.println("New account secret seed: " + newAccountKeyPair.getSecretSeed());

    // Get the private key and the ledger number for the existing account.
    final Keypair existingAccountKeyPair = Keypair.fromSecretSeed("SBB4LZ6OHDFNPGOBVUW3VJ4YJPYMJ7GJM5RBYTD6L7YPMSUGVMLLI5LK");
    final Account existingAccount = stellarServer.accounts().account(existingAccountKeyPair);

    //Create a transaction for the existing account which sets up the new account.
    final Transaction.Builder transactionBuilder = new Transaction.Builder(existingAccount);

    final CreateAccountOperation createAccountOperation =
        new CreateAccountOperation.Builder(newAccountKeyPair, Integer.toString(20)).
            setSourceAccount(existingAccountKeyPair).build();

    transactionBuilder.addOperation(createAccountOperation);
    transactionBuilder.addMemo(Memo.text("ExampleAccount"));

    final Transaction createAccountTransaction = transactionBuilder.build();

    //Sign this transaction with the new account, and then submit it to the stellar server.
    createAccountTransaction.sign(existingAccountKeyPair);

    System.out.println("Creating account...");
    final SubmitTransactionResponse createAccountResponse = stellarServer.submitTransaction(createAccountTransaction);
    printResponse(createAccountResponse);
  }

  /**
   * Output the contents of the response to the console.
   * @param response the response from the Stellar Server
   */
  private static void printResponse(final SubmitTransactionResponse response)
  {
    System.out.println("Successful? " + response.isSuccess());
    System.out.println("Ledger# " + response.getLedger());

    final SubmitTransactionResponse.Extras extras = response.getExtras();
    if (extras != null) {
      System.out.println("TransactionResult: " + extras.getResultXdr());
      System.out.println("TransactionEnvelope: " + extras.getEnvelopeXdr());
    }
    else {
      if (!response.isSuccess()) {
        System.out.println("Extras = null"); //Extras are always null if the response is a success.
      }
    }
  }
}
