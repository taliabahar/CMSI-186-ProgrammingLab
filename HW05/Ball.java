/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Talia Bahar
 *  Date written  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *
 *  Notes         :  None right now.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
public class Ball {
  private static final double DEFAULT_TIME_SLICE_IN_SECONDS = 60.0;
  private static final double INVALID_ARGUMENT_VALUE = -1.0;
  private static final double FRICATION_VALUE               = .99;
  private static double DEFAULT_X1_LOC                      = 10;
  private static double DEFAULT_Y1_LOC                      = 10;
  private static double DEFAULY_X1_MOVE                     = 1;
  private static double DEFAULY_Y1_MOVE                     = 1;
  private static double DEFAULT_X2_LOC                      = -10;
  private static double DEFAULT_Y2_LOC                      = 10;
  private static double DEFAULY_X2_MOVE                     = 1;
  private static double DEFAULY_Y2_MOVE                     = 1;
  private static double totalSeconds                        = 0;
  private static double timeSlice                           = 0;

  private static double BallX1Loc                           = 0;
  private static double BallX1Vel                           = 0;
  private static double BallY1Loc                           = 0;
  private static double BallY1Vel                           = 0;
  private static double BallX2Loc                           = 0;
  private static double BallX2Vel                           = 0;
  private static double BallY2Loc                           = 0;
  private static double BallY2Vel                           = 0;

  private static double fieldWidth                           = 500;
  private static double fieldHeight                          = 500;




// validate location
// has collided
// calculate location
// update velocity every time slice

  // constructor
  public Ball() {
    BallX1Loc = DEFAULT_X1_LOC;
    BallY1Loc = DEFAULT_Y1_LOC;
    BallX1Vel = DEFAULT_X1_MOVE;
    BallY1Vel = DEFAULY_Y1_MOVE;
    BallX2Loc = DEFAULT_X2_LOC;
    BallY2Loc = DEFAULT_Y2_LOC;
    BallX2Vel = DEFAULY_X2_MOVE;
    BallY2Vel = DEFAULY_Y2_MOVE;
    timeSlice = DEFAULT_TIME_SLICE_IN_SECONDS;
  }

  public Ball(double x1loc, double y1loc, double x1move, double y1move, double x2loc, double y2loc, double x2move, double y2move, double timeSlice) {
    BallX1Loc = x1loc;
    BallY1Loc = y1loc;
    BallX1Vel = x1move;
    BallY1Vel = y1move;
    BallX2Loc = x2loc;
    BallY2Loc = y2loc;
    BallX2Vel = x2move;
    BallY2Vel = y2move;
    timeSlice = this.timeSlice;
  }


  public double tick() {
     totalSeconds += timeSlice;
     return totalSeconds;
  }

  public double validateBallXLocation( String xloc ) throws NumberFormatException {
    double newXloc = Double.parseDouble(xloc);
    if(newXloc < 0 || newXloc > fieldWidth){throw new NumberFormatException();}
     return newXloc;
  }
  public double validateBallXLocation( String yloc ) throws NumberFormatException {
    double newYloc = Double.parseDouble(yloc);
    if(newYloc < 0 || newYloc > fieldHeight){throw new NumberFormatException();}
     return newYloc;
  }

  public double validateTimeSliceArg( String argValue ) throws NumberFormatException {
    double newArgValue = Double.parseDouble(argValue);
    if(newArgValue < 0 || newArgValue > 1800){return INVALID_ARGUMENT_VALUE;}
     return newArgValue;
  }


}
