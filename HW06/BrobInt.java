/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  BrobInt.java
 * Purpose    :  Learning exercise to implement arbitrarily large numbers and their operations
 * @author    :  B.J. Johnson
 * Date       :  2017-04-04
 * Description:  @see <a href='http://bjohnson.lmu.build/cmsi186web/homework06.html'>Assignment Page</a>
 * Notes      :  None
 * Warnings   :  None
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Revision History
 * ================
 *   Ver      Date     Modified by:  Reason for change or modification
 *  -----  ----------  ------------  ---------------------------------------------------------------------
 *  1.0.0  2017-04-04  B.J. Johnson  Initial writing and begin coding
 *  1.1.0  2017-04-13  B.J. Johnson  Completed addByt, addInt, compareTo, equals, toString, Constructor,
 *                                     validateDigits, two reversers, and valueOf methods; revamped equals
 *                                     and compareTo methods to use the Java String methods; ready to
 *                                     start work on subtractByte and subtractInt methods
 *
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
// package BrobInt;
import java.util.Arrays;

// NEED JAVA DOCS TO BE GOOD
public class BrobInt {
    public static final BrobInt ZERO     = new BrobInt(  "0" );      /// Constant for "zero"
    public static final BrobInt ONE      = new BrobInt(  "1" );      /// Constant for "one"
    public static final BrobInt TWO      = new BrobInt(  "2" );      /// Constant for "two"
    public static final BrobInt THREE    = new BrobInt(  "3" );      /// Constant for "three"
    public static final BrobInt FOUR     = new BrobInt(  "4" );      /// Constant for "four"
    public static final BrobInt FIVE     = new BrobInt(  "5" );      /// Constant for "five"
    public static final BrobInt SIX      = new BrobInt(  "6" );      /// Constant for "six"
    public static final BrobInt SEVEN    = new BrobInt(  "7" );      /// Constant for "seven"
    public static final BrobInt EIGHT    = new BrobInt(  "8" );      /// Constant for "eight"
    public static final BrobInt NINE     = new BrobInt(  "9" );      /// Constant for "nine"
    public static final BrobInt TEN      = new BrobInt( "10" );      /// Constant for "ten"

   /// Some constants for other intrinsic data types
   ///  these can help speed up the math if they fit into the proper memory space
    public static final BrobInt MAX_INT  = new BrobInt( new Integer( Integer.MAX_VALUE ).toString() );
    public static final BrobInt MIN_INT  = new BrobInt( new Integer( Integer.MIN_VALUE ).toString() );
    public static final BrobInt MAX_LONG = new BrobInt( new Long( Long.MAX_VALUE ).toString() );
    public static final BrobInt MIN_LONG = new BrobInt( new Long( Long.MIN_VALUE ).toString() );

  /// These are the internal fields
    private String internalValue = "";        // internal String representation of this BrobInt
    private byte   sign          = 0;         // "0" is positive, "1" is negative
    private String reversed      = "";        // the backwards version of the internal String representation
    private byte[] byteVersion   = null;      // byte array for storing the string values; uses the reversed string

  /**
   *  Constructor takes a string and assigns it to the internal storage, checks for a sign character
   *   and handles that accordingly;  it then checks to see if it's all valid digits, and reverses it
   *   for later use
   *  @param  value  String value to make into a BrobInt
   */
   public BrobInt(String value) {
        internalValue = value;
        if (value.charAt(0) == '-') {
            sign = 1;
            value = value.replace("-", "");
        } else {
            sign = 0;
            value = value.replace("+", "");
        }
        int count = 0;
        int length = value.length();
        byteVersion = new byte[length];
        for (int i= value.length()-1; i >= 0; i--) {
            byteVersion[count] = (byte)(value.charAt(i) - 48);
            reversed += value.charAt(i);
            count++;
        }
        validateDigits();
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to validate that all the characters in the value are valid decimal digits
   *  @return  boolean  true if all digits are good
   *  @throws  IllegalArgumentException if something is hinky
   *  note that there is no return false, because of throwing the exception
   *  note also that this must check for the '+' and '-' sign digits
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean validateDigits() throws IllegalArgumentException{
       for (byte b : byteVersion) {
           if(b > 9 || b < 0){
               System.out.println(b);
               throw new IllegalArgumentException( "\n        The entered value is not a valid decimal digit." );
           }
       }
       return true;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of this BrobInt
   *  @return BrobInt that is the reverse of the value of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt reverser() {
       if (this.sign == 1) {
           return new BrobInt("-" + this.reversed);
       }
       else {
           return new BrobInt(this.reversed);
       }
    }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to reverse the value of a BrobIntk passed as argument
   *  Note: static method
   *  @param  gint         BrobInt to reverse its value
   *  @return BrobInt that is the reverse of the value of the BrobInt passed as argument
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt reverser( BrobInt gint ) {
       if (gint.sign == 1) {
           return new BrobInt("-" + gint.reversed);
       }
       else {
           return new BrobInt(gint.reversed);
       }
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to add the value of a BrobIntk passed as argument to this BrobInt using byte array
   *  @param  gint         BrobInt to add to this
   *  @return BrobInt that is the sum of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt addByte( BrobInt gint ) {
       String result = "";
       BrobInt larger = (Math.max(this.byteVersion.length, gint.byteVersion.length) == this.byteVersion.length) ? this.copy() : gint.copy();
       BrobInt smaller = (Math.max(this.byteVersion.length, gint.byteVersion.length) == this.byteVersion.length) ? gint.copy() : this.copy();
       byte[] sum = new byte[larger.byteVersion.length + 1];
       byte carry = 0;
       if (gint.sign == this.sign) {
           for (int i=0; i < larger.byteVersion.length; i++){
               if( i < smaller.byteVersion.length) {
                   sum[i] = (byte)((larger.byteVersion[i] + smaller.byteVersion[i] + carry) % 10);
                   carry = (larger.byteVersion[i] + smaller.byteVersion[i] + carry > 9) ? (byte)1 : (byte)0;
               }
               else {
                   sum[i] = (byte)((larger.byteVersion[i] + carry) % 10);
                   carry = (larger.byteVersion[i] + carry > 9) ? (byte)1 : (byte)0;

               }
           }
           if (carry > 0) {
               sum[sum.length - 1] = carry;
           }

           for (int i=sum.length-1; i >= 0; i--) {
               result += sum[i];
           }
           result = result.replaceFirst("^0+(?!$)", "");
           if (gint.sign == 1) {
               result = "-" + result;
           }

       }
       else {
          return this.subtractByte(gint);
       }

       return new BrobInt(result);
   }

   /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
    *  Method to
    *  @param  gint         BrobInt to add to this
    *  @return
    *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int isLarger(BrobInt gint) {
       if (this.byteVersion.length > gint.byteVersion.length) {
           return 1;
       }
       if (gint.byteVersion.length > this.byteVersion.length) {
           return -1;
       }
       for(int i = this.byteVersion.length-1; i >= 0; i--) {
           if(this.byteVersion[i] > gint.byteVersion[i]) {
               return 1;
           }
           if(gint.byteVersion[i] > this.byteVersion[i]) {
               return -1;
           }
       }
       return 0;

   }
  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to subtract the value of a BrobIntk passed as argument to this BrobInt using bytes
   *  @param  gint         BrobInt to subtract from this
   *  @return BrobInt that is the difference of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt subtractByte( BrobInt gint ) {
       String result = "";
       BrobInt s1 = this.copy();
       BrobInt s2 = gint.copy();
       int large = this.isLarger(gint);
       BrobInt larger;
       BrobInt smaller;

       if(large >= 0) {
           larger = this.copy();
           smaller = gint.copy();
       } else {
           larger = gint.copy();
           smaller = this.copy();
           if (larger.sign == 0) {
               larger.sign = 1;
           } else {
               larger.sign = 0;
           }
       }
       byte[] difference = new byte[larger.byteVersion.length + 1];
       int check = 0;
       //  ONLY ARG(2ND) IS NEGATIVE [ 4-(-4) = 4 + 4 = 8]
       if ((s2.sign == 1) && (s1.sign == 0)){
           s2.sign = 0;
           return s1.addByte(s2);
       }
       //  ONLY THIS(1ST) IS NEGATIVE [ (-4)-2 = -4 + -2 = -6]
       if ((s2.sign == 0) && (s1.sign == 1)) {
           s2.sign = 1;
           return s1.addByte(s2);
       }
       //  BOTH NEGATIVE [(-5)-(-4) = -5 + 4 = -1 ~ TAKES SIGN OF BIGGEST VALUE]
           for(int i=0; i < larger.byteVersion.length; i++){
               if( i < smaller.byteVersion.length) {
                   check = larger.byteVersion[i] - smaller.byteVersion[i];
                   if (check < 0 ){
                       larger.byteVersion[i+1] -=  1;
                       difference[i] = (byte)((check + 10 ));
                   } else {
                       difference[i] = (byte)(check);
                   }
               } else {
                   if (larger.byteVersion[i] == -1 && i+1 != larger.byteVersion.length) {
                       larger.byteVersion[i+1] -= 1;
                       difference[i] = (byte)(9);
                   } else {
                       difference[i] = larger.byteVersion[i];
                   }
               }

           }
           for (int j=difference.length-1; j >= 0; j --) {
               result += difference[j];
           }
           result = result.replaceFirst("^0+(?!$)", "");
           if (larger.sign == 1) {
               result = "-" + result;
           }
           System.out.println(result);
           return new BrobInt(result);
       }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to multiply the value of a BrobIntk passed as argument to this BrobInt
   *  @param  gint         BrobInt to multiply by this
   *  @return BrobInt that is the product of the value of this BrobInt and the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt multiply( BrobInt gint ) {
       String result = "";
       byte[] smaller;
       byte[] larger;
       byte carry = 0;
       if(this.byteVersion.length > gint.byteVersion.length){
           larger = new byte[this.byteVersion.length];
           smaller = new byte[gint.byteVersion.length];
           for (int i=0; i < this.byteVersion.length; i++) {
               larger[i] = this.byteVersion[i];
           }
           for (int i=0; i < gint.byteVersion.length; i++) {
               smaller[i] = gint.byteVersion[i];
           }
       }
       else {
           larger = new byte[gint.byteVersion.length];
           smaller = new byte[this.byteVersion.length];
           for (int i=0; i < gint.byteVersion.length; i++) {
               larger[i] = gint.byteVersion[i];
           }
           for (int i=0; i < this.byteVersion.length; i++) {
               smaller[i] = this.byteVersion[i];
           }
       }

       byte[] product = new byte[smaller.length + larger.length + 1];
       for (int i=0; i < product.length; i ++) {
           product[i] = 0;
       }
       for (int i=0; i < smaller.length; i++){
           int k = i;
           for (int j = 0; j < larger.length; j++){
               product[k] = (byte)((larger[j] * smaller[i]) + product[k]);
               if(product[k] > 9){
                   carry = (byte)(product[k] / 10);
                   product[k] = (byte)(product[k] % 10);
               } else {
                   carry = 0;
               }
               product[k+1] += carry;

               k++;
           }
       }

    for (int i=product.length-1; i >= 0; i--) {
        result += product[i];
    }
    result = result.replaceFirst("^0+(?!$)", "");
    if (gint.sign != this.sign) {
        result = "-" + result;
    }
    return new BrobInt(result);
}

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to divide the value of this BrobIntk by the BrobInt passed as argument
   *  @param  gint         BrobInt to divide this by
   *  @return BrobInt that is the dividend of this BrobInt divided by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt divide( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to get the remainder of division of this BrobInt by the one passed as argument
   *  @param  gint         BrobInt to divide this one by
   *  @return BrobInt that is the remainder of division of this BrobInt by the one passed in
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public BrobInt remainder( BrobInt gint ) {
      throw new UnsupportedOperationException( "\n         Sorry, that operation is not yet implemented." );
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to compare a BrobInt passed as argument to this BrobInt
   *  @param  gint  BrobInt to add to this
   *  @return int   that is one of neg/0/pos if this BrobInt precedes/equals/follows the argument
   *  NOTE: this method performs a lexicographical comparison using the java String "compareTo()" method
   *        THAT was easy.....
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public int compareTo( BrobInt gint ) {
      return (internalValue.compareTo( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to check if a BrobInt passed as argument is equal to this BrobInt
   *  @param  gint     BrobInt to compare to this
   *  @return boolean  that is true if they are equal and false otherwise
   *  NOTE: this method performs a similar lexicographical comparison as the "compareTo()" method above
   *        also using the java String "equals()" method -- THAT was easy, too..........
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public boolean equals( BrobInt gint ) {
      return (internalValue.equals( gint.toString() ));
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a BrobInt given a long value passed as argument
   *  @param  value         long type number to make into a BrobInt
   *  @return BrobInt  which is the BrobInt representation of the long
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static BrobInt valueOf( long value ) throws NumberFormatException {
      BrobInt gi = null;
      try {
         gi = new BrobInt( new Long( value ).toString() );
      }
      catch( NumberFormatException nfe ) {
         System.out.println( "\n  Sorry, the value must be numeric of type long." );
      }
      return gi;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to return a String representation of this BrobInt
   *  @return String  which is the String representation of this BrobInt
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public String toString() {
      String byteVersionOutput = "";
      for( int i = 0; i < byteVersion.length; i++ ) {
         byteVersionOutput = byteVersionOutput.concat( Byte.toString( byteVersion[i] ) );
      }
      byteVersionOutput = new String( new StringBuffer( byteVersionOutput ).reverse() );
      return internalValue;
   }

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  Method to display an Array representation of this BrobInt as its bytes
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public void toArray( byte[] d ) {
      System.out.println( Arrays.toString( d ) );
   }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
   *  the main method redirects the user to the test class
   *  @param  args  String array which contains command line arguments
   *  note:  we don't really care about these
   *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
   public static void main(String[] args) {
      System.out.println( "\n  Hello, world, from the BrobInt program!!\n" );
      System.out.println( "\n   You should run your tests from the BrobIntTester...\n" );
    //   String input = "-1093";
      BrobInt bi = new BrobInt("0001563847928379482");
      System.out.println(bi);
    //   for(byte b: byteVersion) {
    // System.out.println(b+ " ");
    // }
    // System.out.println(Arrays.toString(byteVersion));

    //   System.out.println(bi.toString());
    //   System.exit( 0 );
   }

   public BrobInt copy() {
       return new BrobInt(this.toString());
   }
}
