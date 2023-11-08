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
    node.block = new Block(1, initial, null); // Assuming the first block number is 1
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

    Block newBlock = new Block(last.block.number + 1, amount, last.block.getHash());
    newBlock.mine();
    Node newNode = new Node();
    newNode.block = newBlock;
    last.next = newNode;
    last = newNode;
    return newBlock;
  } // mine(int)

  /**
   * Returns the size of the blockchain.
   */
  public int getSize() {
    Node curr = first;
    int blockCount = 0;

    while (curr != null) {
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
    if (blk.isValid()) {
      Node newNode = new Node();
      newNode.block = blk;

      if (last != null) {
        last.next = newNode;
      } else {
        first = newNode;
      }

      last = newNode;
    } else {
      throw new IllegalArgumentException("Invalid Block");
    }
  } // append(Block)

  /**
   * Removes the last block from the chain and returns true. If the chain only has one block, does
   * nothing and returns false.
   */
  public boolean removeLast() {
    if (first == last) {
      return false;
    }

    Node curr = first;

    while (curr.next != last) {
      curr = curr.next;
    }
    curr.next = null;
    last = curr;
    return true;
  } // removeLast()

  /**
   * Returns the hash of the last block in the chain.
   */
  public Hash getHash() {
    if (last != null) {
      return last.block.hash;
    } else {
      return null;
    }
  } // getHash()

  /**
   * Walks the blockchain and returns true if all blocks are valid, false otherwise.
   */
  public boolean isValidBlockChain() {
    String hash = this.toString();

    if (hash.substring(0, 3).equals("000")) {
      return false;
    }

    return true;
  } // isValidBlockChain()

  /**
   * Prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a
   * single line, e.g., Alexis: 300, Blake: 0.
   */
  public void printBalances() {
    int alexisBal = 0;
    int blakeBal = 0;

    Node curr = first;
    while (curr != null) {
      int amount = curr.block.getAmount();

      if (amount > 0) {
        alexisBal -= amount;
        blakeBal += amount;
      } else if (amount < 0) {
        alexisBal += amount;
        blakeBal -= amount;
      }

      curr = curr.next;
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
      str.concat(curr.block.toString()).concat("\n");
      curr = curr.next;
    }
    return str;
  } // toString()

} // class BlockChain
