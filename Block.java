/**
 * A structure containing the data in each node of the blockchain.
 * 
 * @author Jayson Kunkel
 * @author Jonathan Wang
 */
public class Block {
  
  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The number of the block in the blockchain.
   */
  int number;

  /**
   * The amount transferred between the two parties.
   */
  int amount;

  /**
   * The nonce value for this block.
   */
  long nonce;

  /**
   * The hash of the previous block in the blockchain.
   */
  Hash prevHash;

  /** 
   * The hash of this block.
   */
  Hash hash;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  public Block(int num, int amount, Hash prevHash){
    // this.number = num;
    // this.amount = amount;
    // this.prevHash = prevHash;

    //TODO: perform mining operation to find nonce
    // this.nonce = 0;
  } // Block(int, int, Hash)

  public Block(int num, int amount, Hash prevHash, long nonce){
    // this.number = num;
    // this.amount = amount;
    // this.prevHash = prevHash;
    // this.nonce = nonce;

   // TODO: generate hash
  } // Block(int, int, Hash, long) 

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Returns the number of this block.
   */
  public int getNum(){
    return this.number;
  } // getNum()

  /**
   * Returns the amount transferred that is recorded in this block.
   */
  public int getAmount(){
    return this.amount;
  } // getAmount()

  /**
   * Returns the nonce value of this block.
   */
  public long getNonce(){
    return this.nonce;
  } // getNonce()

  /**
   * Returns the hash of the previous block in the blockchain.
   */
  public Hash getPrevHash(){
    return this.prevHash;
  } // getPrevHash()

  /**
   * Returns the hash of this block.
   */
  public Hash getHash(){
    return this.hash;
  } // getHash()

  /** 
   * Returns a string representation of this block.
   */
  public String toString(){
    return "Block " + this.number + " (Amount: " + this.amount + ", Nonce: " + this.nonce + ", prevHash: " + this.prevHash + ", hash: " + this.hash + ")";
  } // toString()

} // class Block
