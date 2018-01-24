/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Talia Bahar
 *  Date          :  2018-01-18
 *  Description   :  Lays out methods involving calendar days that will be used to calculate the days between
 *                   two dates in the CountTheDays program. Methods that compare dates, check for leap years,
 *                   check for invalid dates, turns dates into strings, and find the days between two dates are included. 
 *  Notes         :  None
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision History
 *  ----------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-01-02  B.J. Johnson  Initial writing and release
 *  @version 1.0.1  2017-12-25  B.J. Johnson  Updated for Spring 2018
 */
public class CalendarStuff {


   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;

   private static final int JANUARY   = 1;
   private static final int FEBRUARY  = JANUARY   + 1;
   private static final int MARCH     = FEBRUARY  + 1;
   private static final int APRIL     = MARCH     + 1;
   private static final int MAY       = APRIL     + 1;
   private static final int JUNE      = MAY       + 1;
   private static final int JULY      = JUNE      + 1;
   private static final int AUGUST    = JULY      + 1;
   private static final int SEPTEMBER = AUGUST    + 1;
   private static final int OCTOBER   = SEPTEMBER + 1;
   private static final int NOVEMBER  = OCTOBER   + 1;
   private static final int DECEMBER  = NOVEMBER  + 1;


   private static int[]    days        = {0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };



   public static boolean isLeapYear( long year ) {
      return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
   }

   public static long daysInMonth( long month, long year ) {
     return ((isLeapYear(year) && month == FEBRUARY) ? days[(int)month]+1 : days[(int)month]);
   }



   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return ((month1 == month2) && (day1 == day2) && (year1 == year2));
   }

   public static int compareDate( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      if (dateEquals(month1, day1, year1, month2, day2, year2)) {
        return 0;
      }
      else if (year1 < year2) {
        return -1;
      }
      else if (year1 > year2) {
        return 1;
      }
      else if (month1 < month2){
        return -1;
      }
      else if (month1 > month2) {
        return 1;
      }
      else if (day1 < day2){
        return -1;
      }
      else {
        return 1;
      }
   }

   public static boolean isValidDate( long month, long day, long year ) {
      int leapYear = 0;
      if (month<1 || month > 12){
        return false;
      }
      if (year <= 0){
        return false;
      }
      if (isLeapYear(year) && month == FEBRUARY) {
        leapYear = 1;
      }
      if (day <= 0 || day > days[(int)month] + leapYear){
        return false;
      }
      return true;
   }

   public static String toMonthString( int month ) {
      switch( month ) {
            case 1:  return "January";
            case 2:  return  "February";
            case 3:  return  "March";
            case 4:  return  "April";
            case 5:  return  "May";
            case 6:  return  "June";
            case 7:  return  "July";
            case 8:  return  "August";
            case 9:  return  "September";
            case 10: return  "October";
            case 11: return  "November";
            case 12: return  "December";
            default: throw new IllegalArgumentException( "Illegal month value given to 'toMonthString()'." );
      }
   }

   public static String toDayOfWeekString( int day ) {
      switch( day ) {
           case 1:  return "Sunday";
           case 2:  return "Monday";
           case 3:  return "Tuesday";
           case 4:  return "Wednesday";
           case 5:  return "Thursday";
           case 6:  return "Friday";
           case 7:  return "Saturday";
           default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }


   public static long daysBetween( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      long daysInBetween = 0;
      long m1 = month1;
      long m2 = month2;
      long d1 = day1;
      long d2 = day2;
      long y1 = year1;
      long y2 = year2;
      int compare = compareDate(month1, day1, year1, month2, day2, year2);
      if (compare == 0) {
        return daysInBetween = 0;
      }
      if (compare == 1){
        m1 = month2;
        m2 = month1;
        d1 = day2;
        d2 = day1;
        y1 = year2;
        y2 = year1;
      }
        while(d1 != d2) {
          daysInBetween++;
          d1++;
          if(d1 > daysInMonth(m1,y1)){
            d1 = 1;
            m1++;
            if(m1 > 12){
              m1=1;
              y1++;
            }
          }
        }
        while(m1 != m2) {
          daysInBetween += daysInMonth(m1, y1);
          m1++;
          if(m1 > 12){
            m1=1;
            y1++;
          }
        }
        while(y1 != y2) {
          y1++;
          if(isLeapYear(y1) && m1 > 2){
            daysInBetween += 366;
          }
          else {
            daysInBetween += 365;
          }
        }
      return daysInBetween;
   }
}
