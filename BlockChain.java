import java.security.NoSuchAlgorithmException;


/**
 * Represents a singly-linked structure of Block objects.
 *
 * @author Jayson Kunkel
 * @author Jonathan Wang
 */
public class BlockChain {


  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+


  /**
   * Pointers to the first and last blocks in the blockchain.
   */
  Node first;
  Node last;


  /**
   * A structure to hold the information of each block in the blockchain.
   */
  static class Node {
    Block block;
    Node next;
  } // class Node


  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+


  /**
   * Creates a BlockChain of a single block with the given initial amount.
   *
   * @throws NoSuchAlgorithmException
   */
  public BlockChain(int initial) throws NoSuchAlgorithmException {
    // ignore prevHash
    Node node = new Node();
    node.block = new Block(0, initial, null); // Assuming the first block number is 1
    this.first = node;
    this.last = node;
  } // BlockChain(int)


  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+


  /**
   * Mines a new candidate block to be added to the end of the blockchain. The returned block must
   * be valid to be added.
   *
   * @throws NoSuchAlgorithmException
   */
  public Block mine(int amount) throws NoSuchAlgorithmException {
    Block newBlock = new Block(last.block.number + 1, amount, last.block.getHash()); // New block
    newBlock.mine(); // Mine the block
    
    return newBlock;
  } // mine(int)


  /**
   * Returns the size of the blockchain.
   */
  public int getSize() {
    Node curr = first;
    int blockCount = 0;

    while (curr != null) { // Loop through BlockChain
      blockCount++;
      curr = curr.next;
    }
    // check the number of the last block in the chain
    return blockCount;
  } // getSize()


  /**
   * Adds this block to the list. Throws an IllegalArgumentException if this block is invalid.
   */
  public void append(Block blk) throws IllegalArgumentException {
    if (blk.hash.isValid() && isValidBlockChain()) { // Check if hash & BlockChain are valid
      Node newNode = new Node(); // Instantiate newNode to be appended
      newNode.block = blk; // Set input Block

      if (last != null) {
        last.next = newNode; // Point to newNode
      } else {
        first = newNode; // If there are no other Blocks in the chain
      }

      last = newNode; // Set last to newNode
    } else { // Invalid Chain
      throw new IllegalArgumentException("Chain is invalid!");
    }
  } // append(Block)


  /**
   * Removes the last block from the chain and returns true. If the chain only has one block, does
   * nothing and returns false.
   */
  public boolean removeLast() {
    if (first == last) { // If there are no other Blocks in the chain
      return false;
    }

    Node curr = first;
    while (curr.next != last) { // Loop through the BlockChain
      curr = curr.next;
    }
    curr.next = null; // Remove the last
    last = curr; // Set curr to last
    return true;
  } // removeLast()


  /**
   * Returns the hash of the last block in the chain.
   */
  public Hash getHash() {
    if (last != null) {
      return last.block.hash; // Return hash
    } else {
      return null;
    }
  } // getHash()


  /**
   * Walks the blockchain and returns true if all blocks are valid, false otherwise.
   */
  public boolean isValidBlockChain() {
    Node curr = first;
    int origAmount = curr.block.getAmount(); // Get the inital cash value
    int bal1 = origAmount; // First person's balance
    int bal2 = 0; // Second person's balance
    while (curr.next != null) { // Loop through BlockChain
      curr = curr.next;
      Block block = curr.block;
      if (!block.hash.isValid()) { // If the hash value is not valid
        return false;
      }

      int transAmount = curr.block.getAmount(); // Initialize the amount after a transaction is made
      bal1 += transAmount;
      bal2 -= transAmount;

      if (bal1 + bal2 != origAmount || bal1 < 0 || bal2 < 0) { // If either balance is invalid
        return false;
      }
    }

    return true;
  } // isValidBlockChain()


  /**
   * Prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a
   * single line, e.g., Alexis: 300, Blake: 0.
   */
  public void printBalances() {
    Node curr = first;
    int amount = curr.block.getAmount(); // Get the cash value
    int alexisBal = amount; // Alexis' balance
    int blakeBal = 0; // Blake's balance

    while (curr.next != null) { // Loop through BlockChain
      curr = curr.next;
      amount = curr.block.getAmount(); // Update the amount after transaction
      if (amount > 0) { // Positive cash value check
        alexisBal -= amount;
        blakeBal += amount;
      } else if (amount < 0) { // Negative cash value check
        alexisBal += amount;
        blakeBal -= amount;
      }
    }

    System.out.println("Alexis: <" + alexisBal + ">, " + "Blake: <" + blakeBal + ">");
  } // printBalances()


  /**
   * Returns a string representation of the blockchain: the string representation of each block,
   * earliest to latest, one per line.
   */
  public String toString() {
    String str = "";

    Node curr = first;
    while (curr != null) {
      str += curr.block.toString() + "\n"; // Print each Block in the BlockChain
      curr = curr.next;
    }
    return str;
  } // toString()


} // class BlockChain
