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


public double tick() {
   totalSeconds += timeSlice;
   return totalSeconds;
}

public double getTotalSeconds() {
   return totalSeconds;
}

public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
  double newArgValue = Double.parseDouble(argValue);
  if(newArgValue < 0 || newArgValue > 1800){return new IllegalArgumentException;}
   return newArgValue;
}

public String toString() {
   return  Double.toString(Math.floor(getTotalSeconds() / 3600))  + " hours : " + Double.toString(Math.floor((getTotalSeconds() / 60) % 60)) +  " minutes : " + Double.toString(getTotalSeconds() % 60) + " seconds";
}


}
