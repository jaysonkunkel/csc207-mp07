
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
  Block first;
  Block last;

  /**
   * A structure to hold the information of each block in the blockchain.
   */
  static class Node{
    Block block;
    Node next;
  } // class Node

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Creates a BlockChain of a single block with the given initial amount.
   */
  public BlockChain(int initial){
    // ignore prevHash
    // STUB
  } // BlockChain(int)

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Mines a new candidate block to be added to the end of the blockchain.
   * The returned block must be valid to be added.
   */
  public Block mine(int amount){
    // STUB
    return null;
  } // mine(int)

  /**
   * Returns the size of the blockchain.
   */
  public int getSize(){
    // STUB
    // check the number of the last block in the chain
    return 0;
  } // getSize()

  /**
   * Adds this block to the list. Throws an IllegalArgumentException if this block is invalid.
   */
  public void append(Block blk) throws IllegalArgumentException{
    // STUB
  } // append(Block)

  /**
   * Removes the last block from the chain and returns true.
   * If the chain only has one block, does nothing and returns false.
   */
  public boolean removeLast(){
    // STUB
    return true;
  } // removeLast()

  /**
   * Returns the hash of the last block in the chain.
   */
  public Hash getHash(){
    // STUB
    return null;
  } // getHash()

  /**
   * Walks the blockchain and returns true if all blocks are valid, false otherwise.
   */
  public boolean isValidBlockChain(){
    // STUB
    return true;
  } // isValidBlockChain()

  /**
   * Prints Alexis’s and Blake’s respective balances in the form Alexis: <amt>, Blake: <amt> on a single line, e.g., Alexis: 300, Blake: 0.
   */
  public void printBalances(){
    // STUB
  } // printBalances()
  
  /**
   * Returns a string representation of the blockchain: the string representation of each block, earliest to latest, one per line.
   */
  public String toString(){
    // STUB
    return "not yet implemented";
  } // toString()

} // class BlockChain
