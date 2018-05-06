/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Program to optimize change making process by calculating the
 *               minimum number of coins needed to form any given amount of money.
 * @author    :  Talia Bahar
 * Date       :  2018-04-24
 * Description:  This program provides one main method (makeChangeWithDynamicProgramming)
 *               and 3 helper methods to validate the arguments given to the main. makeChangeWithDynamicProgramming
 *               takes in a sequence of coin denominations [distinct positive integers in no particular order] and
 *               an arbritrary amount of money [a non-negative integer]. It then creates a table with those values
 *               and goes through every value to output the minimum coins needed to reach the target.
 *               If change cannot be made the program will putput IMPOSSIBLE.
 *
 * Notes      :  None
 * Warnings   :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
public final class DynamicChangeMaker {

  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * Method to calculate the minimum number of coins needed to make the target amount
  * @param denominations  int[] of the denominations
  * @param target  the given amount
  * @return the tuple of the minimum number of coins
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
      if (negativeCheck(denominations)) {return Tuple.IMPOSSIBLE;}
      if (duplicateCheck(denominations)) {return Tuple.IMPOSSIBLE;}
      if (validateTarget(target)) {return Tuple.IMPOSSIBLE;}

      int rowCount = denominations.length;
      int columnCount = target + 1;
      Tuple[][] theTable = new Tuple[rowCount][columnCount];

      fillColZero(theTable, denominations);

      for (int i = 0; i < rowCount; i++) {
          for (int j = 1; j < columnCount; j++) {
              if (denominations[i] > j) {
                  theTable[i][j] = Tuple.IMPOSSIBLE;
              } else {
                  theTable[i][j] = new Tuple(denominations.length);
                  theTable[i][j].setElement(i, 1);
                  if (theTable[i][j - denominations[i]].isImpossible()){
                      theTable[i][j] = Tuple.IMPOSSIBLE;
                  } else {
                      theTable[i][j] = theTable[i][j].add(theTable[i][j - denominations[i]]);
                  }
              }
              if (i != 0) {
                  if (!(theTable[i-1][j].isImpossible())) {
                      if(theTable[i][j].isImpossible()) {
                          theTable[i][j] = theTable[i-1][j];
                      }
                      if (theTable[i-1][j].total() < theTable[i][j].total()) {
                          theTable[i][j] = theTable[i-1][j];
                      }
                  }
              }
          }
      }
      Tuple result = theTable[rowCount-1][columnCount-1];
      return result;
  }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * Method to initialize comlumn 0 with coin-length tuples filled with zeros
  * @param theTable  theTable of tuples
  * @param denominations  int[] of the denominations
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private static void fillColZero(Tuple[][] theTable, int[] denominations) {
      for (int i = 0; i < denominations.length; i++) {
          theTable[i][0] = new Tuple(denominations.length);
      }
  }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * Method to check if denominations are negative.
  * @param denominations  int[] of the denominations
  * @return true if a denomination is negative
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private static boolean negativeCheck(int[] denominations) {
      for (int i = 0; i < denominations.length; i++) {
          if (denominations[i] <= 0) {
              return true;
          }
      }
      return false;
  }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * Method to check for duplicate denominations.
  * @param denominations  int[] of the denominations
  * @return true if there exists a duplicate denomination
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private static boolean duplicateCheck(int[] denominations) throws IllegalArgumentException {
      for (int i = 0; i < denominations.length; i++) {
          for (int j = i + 1 ; j < denominations.length; j++) {
              if ( ((denominations[i]) == (denominations[j])) &&  (i != j)) {
                  return true;
              }
          }
      }
      return false;
  }


  /** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
  * Method to check if target is valid. Targets cannot be negative.
  * @param target the given amount
  * @return false if value is positive
  *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
  private static boolean validateTarget(int target)  {
      return target < 0;
  }
}
