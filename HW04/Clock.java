/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Clock.java
 *  Purpose       :  Provides a class defining methods for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  @author       :  Talia Bahar
 *  Date written  :  2017-03-11
 *  Description   :  This class provides a bunch of methods which may be useful for the ClockSolver class
 *                   for Homework 4, part 1.  Includes the following:
 *
 *  Notes         :  None right now.  I'll add some as they occur.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  Revision Histor
 *  ---------------
 *            Rev      Date     Modified by:  Reason for change/modification
 *           -----  ----------  ------------  -----------------------------------------------------------
 *  @version 1.0.0  2017-02-28  B.J. Johnson  Initial writing and release
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~ */

public class Clock {
  /**
   *  Class field definintions go here
   */
   private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
   private static final double INVALID_ARGUMENT_VALUE = -1.0;
   private static final double MAXIMUM_DEGREE_VALUE = 360.0;
   private static final double HOUR_HAND_DEGREES_PER_SECOND = 0.00834;
   private static final double MINUTE_HAND_DEGREES_PER_SECOND = 0.1;
   private static double totalSeconds = 0;
   private static double targetAngle = 0;
   private static double timeSlice = 0;


  /**
   *  Constructor goes here
   */
   public Clock(){
      this.timeSlice = 60;
   }

   public Clock(double angle, double timeSlice) {
     this.targetAngle = angle % 360;
     this.timeSlice = timeSlice;
   }

  /**
   *  Methods go here
   *
   *  Method to calculate the next tick from the time increment
   *  @return double-precision value of the current clock tick
   */
   public double tick() {
      totalSeconds += timeSlice;
      return totalSeconds;
   }

  /**
   *  Method to validate the angle argument
   *  @param   argValue  String from the main programs args[0] input
   *  @return  double-precision value of the argument
   *  @throws  NumberFormatException
   */
   public double validateAngleArg( String argValue ) throws NumberFormatException {
     double newArgValue = Double.parseDouble(argValue);
     if(newArgValue < 0 || newArgValue > 360){throw new NumberFormatException();}
      return newArgValue;
   }

  /**
   *  Method to validate the optional time slice argument
   *  @param  argValue  String from the main programs args[1] input
   *  @return double-precision value of the argument or -1.0 if invalid
   *  note: if the main program determines there IS no optional argument supplied,
   *         I have elected to have it substitute the string "60.0" and call this
   *         method anyhow.  That makes the main program code more uniform, but
   *         this is a DESIGN DECISION, not a requirement!
   *  note: remember that the time slice, if it is small will cause the simulation
   *         to take a VERY LONG TIME to complete!
   */
   public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
     double newArgValue = Double.parseDouble(argValue);
     if(newArgValue < 0 || newArgValue > 1800){return INVALID_ARGUMENT_VALUE;}
      return newArgValue;
   }

  /**
   *  Method to calculate and return the current position of the hour hand
   *  @return double-precision value of the hour hand location
   */
   public double getHourHandAngle() {
      return totalSeconds * HOUR_HAND_DEGREES_PER_SECOND;
   }

  /**
   *  Method to calculate and return the current position of the minute hand
   *  @return double-precision value of the minute hand location
   */
   public double getMinuteHandAngle() {
      return totalSeconds * MINUTE_HAND_DEGREES_PER_SECOND;
   }

  /**
   *  Method to calculate and return the angle between the hands
   *  @return double-precision value of the angle between the two hands
   */
   public double getHandAngle() {
     double angleBetween = Math.abs(getHourHandAngle()-getMinuteHandAngle()) % 360;
     if (angleBetween > 180) {
       return 360 - angleBetween;
     }
     return angleBetween;
   }

  /**
   *  Method to fetch the total number of seconds
   *   we can use this to tell when 12 hours have elapsed
   *  @return double-precision value the total seconds private variable
   */
   public double getTotalSeconds() {
      return totalSeconds;
   }

  /**
   *  Method to return a String representation of this clock
   *  @return String value of the current clock
   */
   public String toString() {
      return "Target Angle found at " + targetAngle + " degrees =  " + Double.toString(Math.floor(getTotalSeconds() / 3600))  + " hours : " + Double.toString((getTotalSeconds() / 60) % 60) +  " minutes : " + Double.toString(getTotalSeconds() % 60) + " seconds";
   }


  /**
   *  The main program starts here
   *  remember the constraints from the project description
   *  @see  http://bjohnson.lmu.build/cmsi186web/homework04.html
   *  be sure to make LOTS of tests!!
   *  remember you are trying to BREAK your code, not just prove it works!
   */
   public static void main( String args[] ) {
      if (args.length == 0){
        timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
      }
      else {
        timeSlice = Double.parseDouble(args[1]);
      }
      System.out.println( "\nCLOCK CLASS TESTER PROGRAM\n" +
                          "--------------------------\n" );
      System.out.println( "  Creating a new clock: " );
      Clock clock = new Clock(Double.parseDouble(args[0]),Double.parseDouble(args[1]));
      System.out.println( "    New clock created: " + clock.toString() );
      System.out.println( "    Testing validateAngleArg()....");
      System.out.print( "      sending '  0 degrees', expecting double value   0.0" );
      try { System.out.println( (0.0 == clock.validateAngleArg( "0.0" )) ? " - got 0.0" : " - no joy" ); }
      catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      System.out.println(clock.getTotalSeconds());
      int numSeconds = 0;
      for (int i=0;i<10;i++) {
        numSeconds += Double.parseDouble(args[1]);
        clock.tick();
        try { System.out.println( (numSeconds == clock.getTotalSeconds()) ? " - got the right amount of seconds" : " - no joy" ); }
        catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
      }
     System.out.println(clock.getTotalSeconds());
     try { System.out.println( (100 == clock.validateTimeSliceArg("100")) ? " - 100 is a valid time slice" : " - 100 is not a valid time slice" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
     try { System.out.println( (23.4 == clock.validateTimeSliceArg("23.4")) ? " - 23.4 is a valid time slice" : " - 23.4 is not a valid time slice" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
     try { System.out.println( (-50 == clock.validateTimeSliceArg("-50")) ? " - -50 is a valid time slice" : " - -50 is not a valid time slice" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
     try { System.out.println( (-1 == clock.validateTimeSliceArg("2000")) ? " - 2000 is not a valid time slice" : " not correct output" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
     try { System.out.println( (-1 == clock.validateTimeSliceArg("abc")) ? " - abc is a valid time slice" : " - abc is not a valid time slice" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }

     try { System.out.println( (100 == clock.validateAngleArg("100")) ? " - 100 is a valid angle" : " - 100 is not a valid angle" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
     try { System.out.println( (23.4 == clock.validateAngleArg("23.4")) ? " - 23.4 is a valid angle" : " - 23.4 is not a valid angle" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
     try { System.out.println( (-50 == clock.validateAngleArg("-50")) ? " - -50 is a valid angle" : " - -50 is not a valid angle" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
     try { System.out.println( (450 == clock.validateAngleArg("450")) ? " - 450 is a valid angle" : " - 450 is not a valid angle" ); }
     catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }




// wanna check hand stuff

   }
}
