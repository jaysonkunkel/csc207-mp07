
/**
 * Allows us to interact with the blockchain through repeated user input in a menu.
 * 
 * @author Jayson Kunkel
 * @author Jonathan Wang
 */
public class BlockChainDriver {

  /**
   * Takes a single command-line argument, the initial non-negative dollar amount that Alexis starts
   * with. Creates a blockchain with the initial amount and then repeatedly: 1. Prints out the
   * contents of the blockchain. 2. Reads in a command from the user. 3. Executes that command,
   * potentially updating the blockchain and reporting back to the user.
   */

  public static void printMenu() {
    System.out.println("mine");
    System.out.println("append");
    System.out.println("remove");
    System.out.println("check");
    System.out.println("report");
    System.out.println("help");
    System.out.println("quit");
  }

  public static void main(String[] args) throws Exception {
    while (true) {   
      if (args.length > 1) {
        System.err.println("error");
      }

      BlockChain blockchain = new BlockChain(Integer.parseInt(args[0]));
       
      blockchain.toString();
      printMenu();  

        switch (args[0]) {
          case "mine":
            break;

          case "append":
            break;

          case "remove":
            break;

          case "check":
            break;

          case "report":
            break;

          case "help":
            break;
            
          case "quit":
            break;

          default:
            System.out.println("Invalid input");
        
        
      } // main(String[])
    }
  }
} // class BlockChainDriver
