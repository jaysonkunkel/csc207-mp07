import java.util.Arrays;
import java.lang.Byte;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.ByteBuffer;

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

  /**
   * Creates a new Block object with given number, transation amount, and the hash of the previous block.
   * Then, mines for the nonce value and current hash.
   * If this block is the first in the chain, prevHash is null.
   */
  public Block(int num, int amount, Hash prevHash){
    this.number = num;
    this.amount = amount;

    // the first block has no previous hash
    if(num == 0){
      this.prevHash = null;
    } // if
    else{
      this.prevHash = prevHash;
    } // else

    // mine for the nonce value and generate the hash
    mine();
  } // Block(int, int, Hash)

  /**
   * Creates a new Block object with given number, amount transferred, hash of the previous block,
   * and nonce value. Then, generates the current hash.
   * If this block is the first in the chain, prevHash is null.
   */
  public Block(int num, int amount, Hash prevHash, long nonce){
    this.number = num;
    this.amount = amount;

    // the first block has no previous hash
    if(num == 0){
      this.prevHash = null;
    } // if
    else{
      this.prevHash = prevHash;
    } // else

    this.nonce = nonce;

    // calculate the current hash
    this.hash = new Hash(calculateHash(num, amount, prevHash, nonce));
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

  /**
   * Returns the hash of a block, represented by a byte array.
   * 
   * @param number - The number of the block
   * @param data - The transaction amount
   * @param prevHash - The hash of the previous block in the chain. 
   *  If the block to hash is the first in the chain, prevHash is not used for the current hash.
   * @param nonce - The nonce value of the block.
   * 
   * @return A byte array that represents the hash of the block.
   */
  public static byte[] calculateHash(int number, int data, Hash prevHash, long nonce){
    try {
      // create a new MessageDigest object
      MessageDigest md = MessageDigest.getInstance("sha-256");

      // update the MessageDigest with the block number, transation amount, previous hash, and nonce value
      md.update(ByteBuffer.allocate(Integer.BYTES).putInt(number).array());
      md.update(ByteBuffer.allocate(Integer.BYTES).putInt(data).array());
      // only include the previous hash if it exists
      if(prevHash != null){
        md.update(prevHash.data);
      } // if
      md.update(ByteBuffer.allocate(Long.BYTES).putLong(nonce).array());

      // convert into a hash and return the hash
      byte[] hash = md.digest();
      return hash;

    } catch (NoSuchAlgorithmException e) {
      System.err.println("Error: algorithm not found");
      return null;
    } // try...catch
  } // calculateHash(int)

  /**
   * Mines for a nonce value to calculate this hash.
   */
  public void mine(){
    long nonce;
    // goes through each possible long value
    for(long i = 0; i < Long.MAX_VALUE; i++){
      nonce = i;
      // create a new Hash object from the calculated hash byte array
      Hash hash = new Hash(calculateHash(this.number, this.amount, this.prevHash, nonce));

      // a hash is valid if its first 3 indices are zeroes
      if(hash.isValid()){
        this.nonce = nonce;
        this.hash = hash;
        break;
      } // if
    } // for
  } // mine()

} // class Block
