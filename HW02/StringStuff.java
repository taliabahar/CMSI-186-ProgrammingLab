/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  StringStuff.java
 *  Purpose       :  Contains string manipulation methods
 *  Author        :  B.J. Johnson
 *  Date          :  2017-01-19
 *  Author        :  Talia Bahar
 *  Date          :  2018-01-30
 *  Description   :  This file presents a bunch of String-style helper methods.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-19  B.J. Johnson  Initial writing and release
 *  @version 1.1.0  2017-01-22  B.J. Johnson  Fill in methods to make the program actually work
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
import java.util.Set;
import java.util.LinkedHashSet;

public class StringStuff {
   public static boolean containsVowel( String s ) {
      String lower = s.toLowerCase();
      return (lower.contains("a") || lower.contains("e") || lower.contains("i") || lower.contains("o") || lower.contains("u") || lower.contains("y"));
   }

    public static String reverse( String s ) {
      String reversed = "";
      for(int i = s.length()-1; i >= 0; i--){
        reversed = reversed + s.charAt(i);
      }
       return reversed;
    }

   public static boolean isPalindrome( String s ) {
     String t = reverse(s);
     return (t.equals(s));
   }

   public static String evensOnly( String s ) {
     String evenLetters = "bdfhjlnprtvxzBDFHJLNPRTVXZ";
     String noOdds = "";
     for (int i=0; i < s.length(); i++){
     if(evenLetters.contains(Character.toString(s.charAt(i)))){
       noOdds = noOdds + (Character.toString(s.charAt(i)));
     }
   }
     return noOdds;
   }

   public static String oddsOnly( String s ) {
     String oddLetters = "acegikmoqsuwyACEGIKMOQSUWY";
     String noEvens = "";
     for (int i=0; i < s.length(); i++){
     if(oddLetters.contains(Character.toString(s.charAt(i)))){
       noEvens = noEvens + (Character.toString(s.charAt(i)));
     }
   }
   return noEvens;
  }

   public static String removeDupes( String s ) {
      String noDupes = "";
      for (int i=0; i < s.length(); i++) {
        if(!(noDupes.contains(Character.toString(s.charAt(i))))) {
          noDupes = noDupes + (Character.toString(s.charAt(i)));
        }
      }
      return noDupes;
     }


   public static String evensOnlyNoDupes( String s ) {
     return removeDupes(evensOnly(s));
   }

   public static String oddsOnlyNoDupes( String s ) {
     return removeDupes(oddsOnly(s));
   }

  /**
   * Main method to test the methods in this class
   *
   * @param args String array containing command line parameters
   */
   public static void main( String args[] ) {
      String blah = new String( "Blah blah blah" );
      String woof = new String( "BCDBCDBCDBCDBCD" );
      String pal1 = new String( "a" );
      String pal2 = new String( "ab" );
      String pal3 = new String( "aba" );
      String pal4 = new String( "amanaplanacanalpanama" );
      String pal5 = new String( "abba" );
      System.out.println( containsVowel( blah ) );
      System.out.println( containsVowel( woof ) );
      System.out.println( isPalindrome( pal1 ) );
      System.out.println( isPalindrome( pal2 ) );
      System.out.println( isPalindrome( pal3 ) );
      System.out.println( isPalindrome( pal4 ) );
      System.out.println( isPalindrome( pal5 ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REHEARSALSZ" ) );
      System.out.println( "evensOnly()        returns: " + evensOnly( "REhearSALsz" ) );
      System.out.println( "evensOnlyNoDupes() returns: " + evensOnlyNoDupes( "REhearSALsz" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "xylophones" ) );
      System.out.println( "oddsOnly()         returns: " + oddsOnly( "XYloPHonES" ) );
      System.out.println( "oddsOnlyNoDupes()  returns: " + oddsOnlyNoDupes( "XYloPHonES" ) );
      System.out.println( "reverse()          returns: " + reverse( "REHEARSALSZ" ) );
   }
}
