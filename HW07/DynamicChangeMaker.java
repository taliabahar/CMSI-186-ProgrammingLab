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
// split to get rows
// .getElement....
// if y > x = impossible
// theTable[denominations.getElement(row)][...]


public static int rowCount;
public static int columnCount;

 public static String makeChangeWithDynamicProgramming(int[] denominations, int target) {
     int rowCount = denominations.length;
     int columnCount = target + 1;
     Tuple[][] theTable = new Tuple[rowCount][columnCount];
     Tuple denoms = new Tuple(denominations);
     for( int i = 0; i < rowCount; i++ ) {
         for( int j = 0; j < columnCount; j++ ) {
     if( j == 0 ) {
         theTable[i][j] = new Tuple(denoms.length());
     } else {
        // if we can't take one of the denominations out of the value of "j"
        //  impossible, at least temporarily
        if( denoms.getElement(i) > j ) {
            theTable[i][j] = Tuple.IMPOSSIBLE;
           // look backward to see if there is a valid/impossible solution
           //  if there is, copy it over and add/replace the one that is there
            // if( some_check_to_see_if_we_are_ABLE_to_look_backwards ) {
                if(theTable[i][j-denoms.getElement(i)].isImpossible()){
                    theTable[i][j] = Tuple.IMPOSSIBLE;
                } else {
                    theTable[i][j] = theTable[i][j-denoms.getElement(i)];
                }
            // }

           // if this is NOT row zero we need to look above to see if there is
           //  a better/non-impossible solution; if so, copy it down
            if( i != 0 ) {

              // if the cell above is impossible, basically do nothing since
              //  this the current cell is already IMPOSSIBLE

              // else if the cell above has a total that is less than the current
              //  cell, copy it down
               }
            }
        }
    }
    }
}
}


        // ELSE -- we *CAN* take one current denomination out
//     } else {
//
//            // make a new tuple with a one in the current demonimation index
//            theTable[i][j] = new Tuple(denoms.length);
//            theTable[i][j] = t[i][j].setElement(i,1);
//
//
//            // look backward to see if there is a valid/impossible solution
//             if( (j - denominations[i]) >= 0 ) {
//
//               // if it's IMPOSSIBLE, mark the current cell IMPOSSIBLE, too
//
//               // else, add the previous cell to the current cell
//             }
//
//            // if this is NOT row zero we need to look above to see if there is
//            //  a better/non-impossible solution; if so, copy it down
//             if( i != 0 ) {
//
//               // if the cell above is impossible, basically do nothing since
//               //  this the current cell is already IMPOSSIBLE
//
//               // else if the cell above has a total that is less than the current
//               //  cell, copy it down
//                }
//             }
//          }
//       }
//    }
// }



// validate method
