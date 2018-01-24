/**
 *  File name     :  CalendarStuff.java
 *  Purpose       :  Provides a class with supporting methods for CountTheDays.java program
 *  Author        :  B.J. Johnson (prototype)
 *  Date          :  2017-01-02 (prototype)
 *  Author        :  Talia Bahar
 *  Date          :  2018-01-18
 *  Description   :  ADD DESCRIPTION HERE
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

  /**
   * A listing of the days of the week, assigning numbers; Note that the week arbitrarily starts on Sunday
   */
   private static final int SUNDAY    = 0;
   private static final int MONDAY    = SUNDAY    + 1;
   private static final int TUESDAY   = MONDAY    + 1;
   private static final int WEDNESDAY = TUESDAY   + 1;
   private static final int THURSDAY  = WEDNESDAY + 1;
   private static final int FRIDAY    = THURSDAY  + 1;
   private static final int SATURDAY  = FRIDAY    + 1;
  // you can finish the rest on your own

  /**
   * A listing of the months of the year, assigning numbers; I suppose these could be ENUMs instead, but whatever
   */
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

  // you can finish these on your own, too

  /**
   * An array containing the number of days in each month
   *  NOTE: this excludes leap years, so those will be handled as special cases
   *  NOTE: this is optional, but suggested
   */
   private static int[]    days        = {0,31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };
  /**
   * The constructor for the class
   */
   public CalendarStuff () {
      System.out.println( "Constructor called..." );  // WHY DO WE NEED
   }

  /**
   * A method to determine if the year argument is a leap year or not<br />
   * Leap years are every four years, except for even-hundred years, except for every 400
   * @param    year  long containing four-digit year
   * @return         boolean which is true if the parameter is a leap year
   */
   public static boolean isLeapYear( long year ) {
      return ((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0);
   }

  /**
   * A method to calculate the days in a month, including leap years
   * @param    month long containing month number, starting with "1" for "January"
   * @param    year  long containing four-digit year; required to handle Feb 29th
   * @return         long containing number of days in referenced month of the year
   * notes: remember that the month variable is used as an index, and so must
   *         be decremented to make the appropriate index value
   */
   public static long daysInMonth( long month, long year ) {
     if (isLeapYear(year) && month == FEBRUARY) {
       return days[(int)month]+1;
     }
     else {
       return days[(int)month];
     }
   }
  //  return ((isLeapYear(year) && month == FEBRUARY) ? days[month]+1 : days[month]);


  /**
   * A method to determine if two dates are exactly equal
   * @param    month1 long    containing month number, starting with "1" for "January"
   * @param    day1   long    containing day number
   * @param    year1  long    containing four-digit year
   * @param    month2 long    containing month number, starting with "1" for "January"
   * @param    day2   long    containing day number
   * @param    year2  long    containing four-digit year
   * @return          boolean which is true if the two dates are exactly the same
   */
   public static boolean dateEquals( long month1, long day1, long year1, long month2, long day2, long year2 ) {
      return ((month1 == month2) && (day1 == day2) && (year1 == year2));
   }

  /**
   * A method to compare the ordering of two dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          int    -1/0/+1 if first date is less than/equal to/greater than second
   */
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
  /**
   * A method to return whether a date is a valid date
   * @param    month long    containing month number, starting with "1" for "January"
   * @param    day   long    containing day number
   * @param    year  long    containing four-digit year
   * @return         boolean which is true if the date is valid
   * notes: remember that the month and day variables are used as indices, and so must
   *         be decremented to make the appropriate index value
   */
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
  //  invalid year, not a number( a string )?
  // Or add an if esle if you want to make the system print out which part of date is not equal

  /**
   * A method to return a string version of the month name
   * @param    month long   containing month number, starting with "1" for "January"
   * @return         String containing the string value of the month (no spaces)
   */
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

  /**
   * A method to return a string version of the day of the week name
   * @param    day int    containing day number, starting with "1" for "Sunday"
   * @return       String containing the string value of the day (no spaces)
   */
   public static String toDayOfWeekString( int day ) {
      switch( day - 1 ) {
           case 1:  return "Sunday";
           case 2:  return "Monday";
           case 3:  return "Tuesday";
           case 4:  return "Wednesday";
           case 5:  return "Thursday";
           case 6:  return "Friday";
           case 7:  return"Saturday";
           default: throw new IllegalArgumentException( "Illegal day value given to 'toDayOfWeekString()'." );
      }
   }

  /**
   * A method to return a count of the total number of days between two valid dates
   * @param    month1 long   containing month number, starting with "1" for "January"
   * @param    day1   long   containing day number
   * @param    year1  long   containing four-digit year
   * @param    month2 long   containing month number, starting with "1" for "January"
   * @param    day2   long   containing day number
   * @param    year2  long   containing four-digit year
   * @return          long   count of total number of days
   */
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
