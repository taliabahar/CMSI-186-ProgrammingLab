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
  private static final double INVALID_ARGUMENT_VALUE        = -1.0;
  private static final double FRICTION_VALUE                = .99;

  private static final double DEFAULT_X_LOC                 = 10;
  private static final double DEFAULT_Y_LOC                 = 10;
  private static final double DEFAULT_X_MOVE                = 1;
  private static final double DEFAULT_Y_MOVE                = 1;

  private static double ballXLoc                            = 0;
  private static double ballXVel                            = 0;
  private static double ballYLoc                            = 0;
  private static double ballYVel                            = 0;


  private static double fieldWidth                          = 500;
  private static double fieldHeight                         = 500;

  private static double quadrant1Width                      = 250;
  private static double quadrant2Width                      = -250;
  private static double quadrant3Width                      = -250;
  private static double quadrant4Width                      = 250;

// calculation has to divide that in half

// update velocity every time slice
// velocity is in feet BUT when the velocity is <= 1 inch per second
// ball radius is in inches
// constructor in ball only has one ball
// constructor in soccersim has multiple
// positive reals do not include 0
// non-negative reals do include 0

  public Ball() {
    ballXLoc = DEFAULT_X_LOC;
    ballYLoc = DEFAULT_Y_LOC;
    ballXVel = DEFAULT_X_MOVE;
    ballYVel = DEFAULT_Y_MOVE;
  }

  public Ball(double xloc, double yloc, double xmove, double ymove) {
    ballXLoc = xloc;
    ballYLoc = yloc;
    ballXVel = xmove;
    ballYVel = ymove;
  }

  public double validateBallXLocation( String argValue ) throws NumberFormatException {
    double newArgValue = Double.parseDouble(argValue);
    if(newArgValue < 0 || newArgValue > fieldWidth){throw new NumberFormatException();}
     return newArgValue;
  }

  public double validateBallYLocation( String argValue ) throws NumberFormatException {
    double newArgValue = Double.parseDouble(argValue);
    if(newArgValue < 0 || newArgValue > fieldHeight){throw new NumberFormatException();}
     return newArgValue;
  }

  public double validateBallVelocity( String argValue ) throws NumberFormatException {
    double newArgValue = Double.parseDouble(argValue);
    if(){throw new NumberFormatException();}
     return newArgValue;
  }
  // check if its bigger than the size of the field
  // do not want x and y location to be the same

  // public double[] calculateLocation(){
  //     double[] location = {ballXLoc, ballYLoc};
  //     return location;
  // }
  //
  // public double[] calculateVelocity(){
  //   double[] velocity = {ballXVel, ballYVel};
  //   return velocity;
  // }
  public void move(double timeSlice) {
    // distance = velocity * time + 1/2 * acceleration * t ^ 2
    ballXLoc += ballXVel * timeSlice + 0.5 * FRICTION_VALUE * Math.pow(timeSlice, 2);
    ballYLoc += ballYVel * timeSlice + 0.5 * FRICTION_VALUE * Math.pow(timeSlice, 2);
    ballXVel *= FRICTION_VALUE * timeSlice;
    ballYVel *= FRICTION_VALUE * timeSlice;
    if (Math.abs(ballXVel) < 1) {
      ballXVel = 0;
    }
    if (Math.abs(ballYVel) < 1) {
      ballYVel = 0;
    }
  }



  public String toString(){
    return "";
    // "Location x : ii Location y : ii Velcotiy x : ii Velocity y : ii"
// returns current location and currrent velocity
  }


}
