/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  Ball.java
 *  Purpose       :  Provides a class defining methods for the SoccerSim class
 *  @author       :  Talia Bahar
 *  Date written  :  2018-03-13
 *  Description   :  This class provides a bunch of methods which may be useful for the SoccerSim class
 *
 *  Notes         :  None right now.
 *  Warnings      :  None
 *  Exceptions    :  None
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
import java.text.DecimalFormat;

public class Ball {
  DecimalFormat df = new DecimalFormat("#0.00");
  private static final double FRICTION_VALUE                      = .01;
  private static final double DEFAULT_TIMESLICE_IN_SECONDS        = 1.0;
  private static double timeSlice                                 = 0;

  private static final double DEFAULT_X_LOC                       = 10;
  private static final double DEFAULT_Y_LOC                       = -10;
  private static final double DEFAULT_X_MOVE                      = 5;
  private static final double DEFAULT_Y_MOVE                      = 5;
  private static final double VELOCITY_AT_1INCH_PERSECOND         = .083;

  private double ballXLoc;
  private double ballXVel;
  private double ballYLoc;
  private double ballYVel;

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
    ballXLoc += ballXVel;
    ballYLoc += ballYVel;
    ballXVel = ballXVel - ((ballXVel * (FRICTION_VALUE)));
    ballYVel = ballYVel - ((ballYVel * (FRICTION_VALUE)));
    if ((Math.abs(ballXVel) * 12) <= 1.0) {
      ballXVel = 0;
    }
    if ((Math.abs(ballYVel) * 12) <= 1.0) {
      ballYVel = 0;
    }
  }

public boolean hasStopped() {
  return ballXVel <= VELOCITY_AT_1INCH_PERSECOND && ballYVel <= VELOCITY_AT_1INCH_PERSECOND;
}

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
    return "X-Location: " + df.format(ballXLoc) + " Y-Location: " + df.format(ballYLoc) + " X-Velocity: " + df.format(ballXVel) + " Y-Velocity: " + df.format(ballYVel);
  }

public static void main(String[] args) {
    System.out.println( "\nBALL CLASS TESTER PROGRAM\n" +
                        "--------------------------\n" );
    System.out.println( "  Creating a new ball: " );
    Ball ball = new Ball();
    System.out.println( "  New ball created: " + ball.toString());

    ball.move();
    System.out.println("Update: " + ball.toString());
    try { System.out.println( (15.0 == ball.ballXLoc) ? " move() for X-Location is working as intended" : " move() is not working" ); }
    catch( Exception e ) { System.out.println ( " -{} Exception thrown: " + e.toString() ); }
    try { System.out.println( (-5.0 == ball.ballYLoc) ? " move() for Y-Location is working as intended" : " move() is not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }

    ball.move();
    System.out.println("Update: " + ball.toString());
    try { System.out.println( (19.95 == ball.ballXLoc) ? " move() for X-Location is working as intended" : " move() is not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    try { System.out.println( (-0.05 <= ball.ballYLoc) ? " move() for Y-Location is working as intended" : " move() is not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    for (int i=0; i < 900; i++){
      ball.move();
    }
    System.out.println("Current X-Velocity is " + ball.getXVel());
    System.out.println("Current Y-Velocity is " + ball.getYVel());
    try { System.out.println( (ball.hasStopped()) ? " hasStopped() is working as intended" : " hasStopped() is not working" ); }
    catch( Exception e ) { System.out.println ( " - Exception thrown: " + e.toString() ); }
    }
}
