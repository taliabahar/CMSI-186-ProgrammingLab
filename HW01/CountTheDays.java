/**
 *  File name     :  CountTheDays.java
 *  Purpose       :  Provides a class to produce the days between two given dates.
 *  Author        :  Talia Bahar
 *  Date          :  2018-23-18
 *  Description   :  Given two dates this program will convert those strings into a long
 *                   so they can be used in the daysBetween method defined in CalendarStuff.java
 *                   to calculate the days between the two dates.
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class CountTheDays {
  public static void main (String [] args){
    long[] stringToLong = new long[6];
    for(int i=0; i < 6; i++){
      stringToLong[i] = Long.parseLong(args[i]);
  }
  System.out.println(CalendarStuff.daysBetween(stringToLong[0],stringToLong[1],stringToLong[2],stringToLong[3],stringToLong[4],stringToLong[5]));
 }
}
