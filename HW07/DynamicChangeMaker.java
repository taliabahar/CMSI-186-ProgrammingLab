/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * File name  :  DynamicChangeMaker.java
 * Purpose    :  Program to ___________________
 * @author    :  Talia Bahar
 * Date       :  2018-04-24
 * Description:  This program provides ___________________
 *
 *
 * Notes      :  None
 * Warnings   :  None
 *  ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */
 import java.util.Arrays;

 public class DynamicChangeMaker {

public static int rowCount;
public static int columnCount;

 public static Tuple makeChangeWithDynamicProgramming(int[] denominations, int target) {
     int rowCount = denominations.length;
     int columnCount = target + 1;
     Tuple[][] theTable = new Tuple[rowCount][columnCount];
     Tuple denoms = new Tuple(denominations);
     for( int i = 0; i < rowCount; i++ ) {
         for( int j = 0; j < columnCount; j++ ) {
     if( j == 0 ) {
         theTable[i][j] = new Tuple(denoms.length());
     } else {
        if( denoms.getElement(i) > j ) {
            theTable[i][j] = Tuple.IMPOSSIBLE;
                if(theTable[i][j-denoms.getElement(i)].isImpossible()){
                    theTable[i][j] = Tuple.IMPOSSIBLE;
                } else {
                    theTable[i][j] = theTable[i][j-denoms.getElement(i)];
                }
           // if this is NOT row zero we need to look above to see if there is
           //  a better/non-impossible solution; if so, copy it down
           if( i != 0 ) {
               if(theTable[i-1][j].isImpossible()){
                   theTable[i][j] = Tuple.IMPOSSIBLE;
               } else if(theTable[i-1][j].total() < theTable[i][j].total()) {
                   theTable[i][j] = theTable[i-1][j];
               } else {
                   theTable[i][j] = new Tuple(denoms.length());
                   theTable[i][j].setElement(i,1);
               }
                 if(j - denoms.getElement(i) >= 0 ) {
                     if(theTable[i][j].isImpossible()){
                         theTable[i][j] = Tuple.IMPOSSIBLE;
                     } else {
                         theTable[i][j] = theTable[i][j-1];
                     }
                     if(i != 0){
                         if(theTable[i-1][j].isImpossible()){
                             theTable[i][j] = theTable[i][j];
                         } else if(theTable[i-1][j].total() < theTable[i][j].total()) {
                             theTable[i][j] = theTable[i-1][1];
                         }
                     }
                 }

               }
            }
        }
    }
    }
    // smallest output
    return new Tuple();
}
}






// validate method
