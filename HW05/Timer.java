public class Timer {

  private static double totalSeconds                            = 0;
  private static double timeSlice                               = 0;
  private static final double DEFAULT_TIMESLICE_IN_SECONDS      = 1;
  private static final double INVALID_ARGUMENT_VALUE = -1.0;



public double tick() {
   totalSeconds += timeSlice;
   return totalSeconds;
}

public double getTotalSeconds() {
   return totalSeconds;
}

public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
  double newArgValue = Double.parseDouble(argValue);
  if(newArgValue < 0 || newArgValue > 1800){return INVALID_ARGUMENT_VALUE;}
   return newArgValue;
}

}

// CAN USE CLOCK CLASS FROM BEFORE
// NEED TO KNOW HOW TO INCREMENT TOTAL SECONDS, TICK, REPRESENT TOTAL TIME
// totalSeconds, tick, toString
