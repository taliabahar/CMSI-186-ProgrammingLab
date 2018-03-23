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
  private static final double FRICTION_VALUE                = .01;
  private static final double DEFAULT_TIMESLICE_IN_SECONDS  = 1.0;
  private static double timeSlice                           = 0;

  private static final double DEFAULT_X_LOC                 = 10;
  private static final double DEFAULT_Y_LOC                 = 10;
  private static final double DEFAULT_X_MOVE                = 1;
  private static final double DEFAULT_Y_MOVE                = 1;

  private static double ballXLoc;
  private static double ballXVel;
  private static double ballYLoc;
  private static double ballYVel;





// calculation has to divide that in half

// velocity is in feet BUT when the velocity is <= 1 inch per second
// ball radius is in inches
// positive reals do not include 0
// non-negative reals do include 0

  public Ball() {
    ballXLoc = DEFAULT_X_LOC;
    ballYLoc = DEFAULT_Y_LOC;
    ballXVel = DEFAULT_X_MOVE;
    ballYVel = DEFAULT_Y_MOVE;
    timeSlice = DEFAULT_TIMESLICE_IN_SECONDS;
  }

  public Ball(double xloc, double yloc, double xmove, double ymove, double timeSlice) {
    ballXLoc = xloc;
    ballYLoc = yloc;
    ballXVel = xmove;
    ballYVel = ymove;
    this.timeSlice = timeSlice;
  }

  public void move() {
    // distance = velocity * time + 1/2 * acceleration * t ^ 2
    // ballXLoc += ballXVel * timeSlice + 0.5 * FRICTION_VALUE * Math.pow(timeSlice, 2);
    // ballYLoc += ballYVel * timeSlice + 0.5 * FRICTION_VALUE * Math.pow(timeSlice, 2);
    ballXLoc += ballXVel;
    ballYLoc += ballYVel;
    ballXVel = ballXVel - ((ballXVel * FRICTION_VALUE) * timeSlice);
    ballYVel = ballYVel - ((ballYVel * FRICTION_VALUE) * timeSlice);
    if ((ballXVel/12) < 1) {
      ballXVel = 0;
    }
    if ((ballYVel/12) < 1) {
      ballYVel = 0;
    }
  }

public boolean hasStopped() {
  return ballXVel/12 < .083 && ballYVel/12 < .083;
}
// for velocity: vi * Math.pow(0.99, timeSlice)

  public double getXLoc(){
    return ballXLoc;
  }
  public double getYLoc(){
    return ballYLoc;
  }
  public double getXVel(){
    return ballXVel;
  }
  public double getYVel(){
   return ballYVel;
 }

  public String toString(){
    return "X Location: " + ballXLoc + " Y Location: " + ballYLoc + " X Velocity: " + ballXVel + " Y Velocity: " + ballYVel;
  }

// test to String
}
