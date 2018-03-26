/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Timer.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Talia Bahar
 *  Date written  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *
 *  Notes         :  None right now.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Timer {

  private static double totalSeconds                            = 0;
  private static double timeSlice                               = 0;
  private static final double DEFAULT_TIMESLICE_IN_SECONDS      = 1;
  private static final double INVALID_ARGUMENT_VALUE            = -1.0;

public Timer (double timeSlice) {
  this.timeSlice = timeSlice;
}

public Timer () {
   timeSlice = DEFAULT_TIMESLICE_IN_SECONDS;
}

public double tick() {
   totalSeconds += timeSlice;
   return totalSeconds;
}

public double getTotalSeconds() {
   return totalSeconds;
}

public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
  try{
    double newArgValue = Double.parseDouble(argValue);
    if(newArgValue < 0 || newArgValue > 1800 || Double.isNaN(newArgValue)){return INVALID_ARGUMENT_VALUE;}
    return newArgValue;
  } catch(Exception e) {
    return INVALID_ARGUMENT_VALUE;
  }
}

public String toString() {
   return  Double.toString(Math.floor(getTotalSeconds() / 3600))  + " hours : " + Double.toString(Math.floor((getTotalSeconds() / 60) % 60)) +  " minutes : " + Double.toString(getTotalSeconds() % 60) + " seconds";
}

public static void main(String[] args) {
  System.out.println( "\nTIMER CLASS TESTER PROGRAM\n" +
                      "--------------------------\n" );
  System.out.println( "  Creating a new timer: " );
  Timer timer = new Timer();
  System.out.println( "  New timer created: " + timer.toString());
  System.out.println(timer.getTotalSeconds());
  int numSeconds = 0;
  for (int i=0;i<10;i++) {
    numSeconds += timeSlice;
    timer.tick();
    try { System.out.println( (numSeconds == timer.getTotalSeconds()) ? " - got the right amount of seconds" : " - no joy" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
  }
  System.out.println(timer.getTotalSeconds());
  try { System.out.println( (100 == timer.validateTimeSliceArg("100")) ? " - 100 is a valid time slice" : " - 100 is not a valid time slice" ); }
  catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
  try { System.out.println( (23.4 == timer.validateTimeSliceArg("23.4")) ? " - 23.4 is a valid time slice" : " - 23.4 is not a valid time slice" ); }
  catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
  try { System.out.println( (-50 == timer.validateTimeSliceArg("-50")) ? " - -50 is a valid time slice" : " - -50 is not a valid time slice" ); }
  catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
  try { System.out.println( (-1 == timer.validateTimeSliceArg("2000")) ? " - 2000 is not a valid time slice" : " not correct output" ); }
  catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
  try { System.out.println( (-1 == timer.validateTimeSliceArg("abc")) ? " - abc is not a valid time slice" : " - abc is a valid time slice" ); }
  catch( NumberFormatException nfe ) { System.out.println ( " - Exception thrown: " + nfe.toString() ); }
  }
}
