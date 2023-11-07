import java.util.Arrays;
import java.lang.Byte;

/**
 * A wrapper class that wraps up a byte array and includes some byte array operations.
 *  
 * @author Jayson Kunkel
 * @author Jonathan Wang
 */
public class Hash{

  // +--------+------------------------------------------------------
  // | Fields |
  // +--------+

  /**
   * The hash as an array of bytes.
   */
  public byte[] data;

  // +--------------+------------------------------------------------
  // | Constructors |
  // +--------------+

  /**
   * Constructs a new Hash object that contains the given hash, as an array of bytes.
   */
  public Hash(byte[] data){
    this.data = data;
  } // Hash (byte[])

  // +---------+-----------------------------------------------------
  // | Methods |
  // +---------+

  /**
   * Returns the hash contained in this object.
   */
  public byte[] getData(){
    return this.data;
  } // getData()

  /**
   * Returns true if the first three indices of this hash contain zeroes, false otherwise.
   */
  public boolean isValid(){
    // return this.toString().substring(0, 6).equals("000000");

    // STUB
    return true;
  } // isValid()

  /**
   * Returns the string representation of this hash as a string of hexadecimal digits, with two digits per byte.
   */
  public String toString(){
    String result = "";

    // for each byte in this data
    for(int i = 0; i < this.data.length; i++){
      // convert the byte to an unsigned integer
      int hash = Byte.toUnsignedInt(this.data[i]);
      // write the byte to result string in hexadecimal
      result += String.format("%x", hash);
    } // for

    return result;
  } // toString()

  /**
   * Returns true if this hash is structurally equal to the argument, false otherwise
   */
  public boolean equals(Object other){
    // check if other is a hash
    if(other instanceof Hash == false){
      return false;
    } // if
    else{
      // cast other to type hash and compare the two Hash objects' arrays
      Hash o = (Hash) other;
      return Arrays.equals(this.data, o.getData());
    } // else
  } // equals(Object)

} // class Hash