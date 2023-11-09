import java.io.PrintWriter;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
/**
 * Allows us to interact with the blockchain through repeated user input in a menu.
 * 
 * @author Jayson Kunkel
 * @author Jonathan Wang
 */
public class BlockChainDriver {

  /**
   * Takes a single command-line argument, the initial non-negative dollar amount that Alexis starts
   * with. Creates a blockchain with the initial amount and then repeatedly: 
   * 1. Prints out the contents of the blockchain. 
   * 2. Reads in a command from the user. 
   * 3. Executes that command, potentially updating the blockchain and reporting back to the user.
   */
  public static void main(String[] args) throws Exception {

    // check if the command-line argument is valid
    initialCheck(args);

    // create a PrintWriter and Scanner
    PrintWriter pen = new PrintWriter(System.out, true);
    Scanner scan = new Scanner(System.in);

    // create a new blockchain from the initial dollar amount
    BlockChain bc = new BlockChain(Integer.parseInt(args[0]));

    // stores a user-input command
    String command = "";

    // while user-given command is not quit
    while (!command.equals("quit")) {   

      // print out the contents of the blockchain
      pen.println(bc.toString());
      // print the command menu
      printMenu(pen);  
      // get next command from user
      command = scan.nextLine();
      // perform corresponding action to command
      handleCommand(bc, command, pen, scan);

    } // while
  } // main(String[])

  /**
   * Checks that the single command-line argument is a non-negative integer and throws an error if not.
   * This is the inital value that Alexis starts with in the blockchain.
   */
  public static void initialCheck(String[] args) throws Exception{
    if (args.length != 1 || Integer.parseInt(args[0]) < 0) {
      throw new Exception("Error: Please enter a non-negative dollar amount.");
    } // if
  } // initialCheck(String[])

  /**
   * Prints the valid command options and their actions.
   */
  public static void printMenu(PrintWriter pen) {
    pen.println("Valid commands:");
    pen.println("\tmine: discovers the nonce for a given transaction");
    pen.println("\tappend: appends a new block onto the end of the chain");
    pen.println("\tremove: removes the last block from the end of the chain");
    pen.println("\tcheck: checks that the block chain is valid");
    pen.println("\treport: reports the balances of Alexis and Blake");
    pen.println("\thelp: prints this list of commands");
    pen.println("\tquit: quits the program");
  } // printMenu(PrintWriter)

  /**
   * Given a user-input command, perform the appropriate action on the blockchain using a switch statement.
   */
  public static void handleCommand(BlockChain bc, String command, PrintWriter pen, Scanner scan){

    boolean hasMined = false;

    switch (command) {

      // discoveres the nonce for a given transaction
      case "mine":
        pen.println("Amount transferred? ");
        int amt = scan.nextInt();
        try{
          Block b = bc.mine(amt);
          hasMined = true;
          pen.println("amount = " + amt + ", nonce = " + b.getNonce());
        } catch (NoSuchAlgorithmException e){
          pen.println("Error: Could not mine block");
        } // try...catch
        break;

      // appends a mined block to the blockchain
      case "append":
        if(!hasMined){
          pen.println("Error: Block must be mined before it can be appended");
        } // if
        else{
          pen.println("Amount transferred? ");
          int amount = scan.nextInt();
          pen.println("Nonce? ");
          int nonce = scan.nextInt();
          bc.append(new Block(bc.getSize()-1, amount, bc.last.block.getHash(), nonce));
        } // else
        break;

      // removes the last block from the blockchain
      case "remove":
        bc.removeLast();
        break;

      // checks if the blocks in the blockchain are consistent and valid and prints result
      case "check":
        if(bc.isValidBlockChain()){
          pen.println("Chain is valid!");
        } // if
        else{
          pen.println("Chain is invalid!");
        } // else
        break;

      // prints Alexis' and Blake's balances
      case "report":
        bc.printBalances();
        break;

      // prints the menu of commands
      case "help":
        printMenu(pen);
        break;

      // quits the program and closes pen and scan
      case "quit":
        pen.close();
        scan.close();

      // invalid input; does nothing
      default:
        pen.println("Error: Invalid input");
        break;
      }// switch(String)
  } // andleCommand(PrintWriter, String)

} // class BlockChainDriver
