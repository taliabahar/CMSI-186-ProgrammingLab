/** ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 *  File name     :  SoccerSim.java
 *  Purpose       :
 *  @author       :  Talia Bahar
 *  Date written  :  2018-03-13
 *  Description   :
 *
 *  Notes         :  None right now.
 *  Warnings      :  None
 *  Exceptions    :  IllegalArgumentException when the input arguments are "hinky"
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~*/
 public class SoccerSim{
   private static final double fieldWidth                          = 500;
   private static final double fieldHeight                         = 500;
   private static final double DEFAULT_TIMESLICE_SECONDS        = 1;

   private static final double quadrant1Width                      = 250;
   private static final double quadrant1Height                     = 250;

   private static final double quadrant2Width                      = -250;
   private static final double quadrant2Height                     = 250;

   private static final double quadrant3Width                      = -250;
   private static final double quadrant3Height                     = -250;

   private static final double quadrant4Width                      = 250;
   private static final double quadrant4Height                     = -250;

  private Ball[] ballArray;
  private int numBalls;
  private static double timeSlice;
  private static final double ballRadius                    = 4.5;


  private static double [] pole = new double []{-300, 250}; ;



  // initial report
  // progress report @ ____
  // has collided (where and when) // centers <= 8.9 inches (ball radius is 4.5)

  // at rest
  // pole


  public SoccerSim(String[] args){
    numBalls = (int)(args.length/4);
    if(args.length % 4 == 1){
      timeSlice = Double.parseDouble(args[args.length-1]);
    }
    else if(args.length % 4 == 0) {
      timeSlice = DEFAULT_TIMESLICE_SECONDS;
    }
    else {
      throw new IllegalArgumentException("Wrong number of input values");
    }
    ballArray = new Ball[numBalls];
    int j=0;
    for (int i=0; i < ballArray.length; i+=4) {
      Double xpos = Double.parseDouble(args[i+0]);
      Double ypos = Double.parseDouble(args[i+1]);
      Double xvel = Double.parseDouble(args[i+2]);
      Double yvel = Double.parseDouble(args[i+3]);
      ballArray[j] = new Ball (xpos, ypos, xvel, yvel, timeSlice);
      j++;
    }

  }

  public boolean hasCollided() {
    for (Ball b1 : ballArray) {
      if (Math.sqrt(Math.pow(pole[0] - b1.getXLoc(),2) +(Math.pow(pole[1] - b1.getYLoc(), 2))) < ballRadius){
        return true;
      }
      for (Ball b2: ballArray) {
        if(Math.sqrt(Math.pow(b2.getXLoc() - b1.getXLoc(),2) +(Math.pow(b2.getYLoc() - b1.getYLoc(), 2))) < ballRadius){
          return true;
        }
      }
    }
    return false;
  }

  public boolean atRest() {
    for (Ball b : ballArray){
      if (!b.hasStopped()) {
        return true;
      }
    }
    return false;
  }


  public boolean ballIsOnField() {
    for (Ball b : ballArray) {
      if (b.getXLoc() > quadrant1Width || b.getXLoc() > quadrant2Width || b.getYLoc() > quadrant1Height || b.getXLoc() > quadrant3Height){
        return true;
      }
    }
    return false;
  }

  //
  // public double ballsMoving() {
  //
  // }
  //
  // public double calculateDistance(ball1, ball2) {
  //
  // }
  //
  // public String toString() {

  // }
  // if ball is at rest and collision hasn't happened its not possible




  public double validateBallXLocation( String argValue ) throws NumberFormatException {
    double newArgValue = Double.parseDouble(argValue);
    if(newArgValue < quadrant2Width || newArgValue > quadrant1Width){throw new NumberFormatException();}
     return newArgValue;
  }

  // do not want x and y location to be the same
  public double validateBallYLocation( String argValue ) throws NumberFormatException {
    double newArgValue = Double.parseDouble(argValue);
    if(newArgValue < quadrant3Height || newArgValue > quadrant4Width){throw new NumberFormatException();}
     return newArgValue;
  }

  // check if its bigger than the size of the field
  // check both since different directions
  // public double validateBallXVelocity( String argValue ) throws NumberFormatException {
  //   double newArgValue = Double.parseDouble(argValue);
  //   if(){throw new NumberFormatException();}
  //    return newArgValue;
  // }
  //
  // public double validateBallYVelocity( String argValue ) throws NumberFormatException {
  //   double newArgValue = Double.parseDouble(argValue);
  //   if(){throw new NumberFormatException();}
  //    return newArgValue;
  // }

  public static void main(String[] args) {
    System.out.println("hello world XD");
    // validate args
    // construct soccersim
    // construct timer
    // while moving and on field
    // prints tostring
    // collsion
    // tick
  }
}
