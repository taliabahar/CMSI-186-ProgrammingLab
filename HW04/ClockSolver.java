/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  ClockSolver.java
 *  Purpose       :  The main program for the ClockSolver class
 *  @author       :  B.J. Johnson
 *  Date written  :  2017-02-28
 *  @author       :  Talia Bahar
 *  Date written  :  2018-03-11
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

public class ClockSolver {
  /**
   *  Class field definintions go here
   */
   private final double MAX_TIME_SLICE_IN_SECONDS  = 1800.00;
   private final double DEFAULT_TIME_SLICE_SECONDS = 60.0;
   private final static double EPSILON_VALUE       = 5;      // small value for double-precision comparisons
   private static double targetAngle               = 0;
   private static double timeSlice                 = 0;

  /**
   *  Constructor
   *  This just calls the superclass constructor, which is "Object"
   */
   public ClockSolver() {
      super();
   }

  /**
   *  Method to handle all the input arguments from the command line
   *   this sets up the variables for the simulation
   */
   public void handleInitialArguments( String args[] ) {
      System.out.println( "\n   Hello world, from the ClockSolver program!!\n\n" ) ;
      Clock c = new Clock();
      if( 0 == args.length ) {
         System.out.println( "   Sorry you must enter at least one argument\n" +
                             "   Usage: java ClockSolver <angle> [timeSlice]\n" +
                             "   Please try again..........." );
         System.exit( 1 );
      }
      if( 1 == args.length ) {
         c.validateAngleArg(args[0]);
         targetAngle = Double.parseDouble(args[0]);
         timeSlice = DEFAULT_TIME_SLICE_SECONDS;
      }
      if( 2 == args.length ) {
         c.validateAngleArg(args[0]);
         c.validateTimeSliceArg(args[1]);
         targetAngle = Double.parseDouble(args[0]);
         timeSlice = Double.parseDouble(args[1]);
      }
   }

   public static void main( String args[] ) {
      ClockSolver cse = new ClockSolver();
      double[] timeValues = new double[3];
      cse.handleInitialArguments( args );
      Clock clock = new Clock( cse.targetAngle, cse.timeSlice);
      int totalSecondsIn12Hours = 43200;
      System.out.println( "\n   Looking for angles of " + targetAngle + " degrees with a " + timeSlice + " second timeslice \n" ) ;
      while( clock.getTotalSeconds() < totalSecondsIn12Hours ) {
        if (clock.getTotalSeconds() == 712) {
           System.out.println(clock.getHandAngle() + " <=====> " + cse.targetAngle);

        }
         if (cse.targetAngle - clock.getHandAngle() <= EPSILON_VALUE && cse.targetAngle - clock.getHandAngle() >= 0 ||
             clock.getHandAngle() - cse.targetAngle <= EPSILON_VALUE && clock.getHandAngle() - cse.targetAngle >= 0) {
           System.out.println(clock.toString());
         }
         clock.tick();
      }
      System.exit( 0 );
   }
}
