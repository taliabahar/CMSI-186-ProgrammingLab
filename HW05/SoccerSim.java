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

   private static final double Q1_AND_Q4_WIDTH                     = 250;
   private static final double Q2_AND_Q3_WIDTH                     = -250;

   private static final double Q1_AND_Q2_HEIGHT                    = 250;
   private static final double Q3_AND_Q4_HEIGHT                    = -250;


   private static double timeSlice;
   private static final double DEFAULT_TIMESLICE_SECONDS           = 1;
   private Timer timer;


   private Ball[] ballArray;
   private int numBalls;
   private static final double ballRadius                          = 4.5;

   private


  private static double [] pole = new double []{-300, 250};



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
    timer = new Timer(timeSlice);
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
        return false;
      }
    }
    return true;
  }

  public boolean ballIsOnField() {
    for (Ball b : ballArray) {
      if (b.getXLoc() > Q1_AND_Q4_WIDTH || b.getXLoc() < Q2_AND_Q3_WIDTH || b.getYLoc() > Q1_AND_Q2_HEIGHT || b.getXLoc() > Q3_AND_Q4_HEIGHT){
        return false;
      }
    }
    return true;
  }

public void run() {
  for (Ball b : ballArray){
    b.move();
  }
  timer.tick();
}
  // An initial report that gives the locations of all objects, including the initial velocity of each ball.
  // After every time slice, a report showing the location and velocity of every ball.
  // A final report indicating the simulated time of the first collision, the objects involved and their locations;
  //   or, the message NO COLLISION IS POSSIBLE, giving the simulated time at which the program made that discovery.
  public String toString() {
    for (Ball b : ballArray){
    System.out.println("PORGRESS REPORT @: " + timer.totalSeconds() + b.toString());
    }
  }
  // if ball is at rest and collision hasn't happened its not possible
  // initial report
  // progress report @ ____
  // has collided (where and when)

  public void validateBallXLocation() throws NumberFormatException {
    for (Ball b1 : ballArray) {
      if (b1.getXLoc() > Q1_AND_Q4_WIDTH ||b1.getXLoc() < Q2_AND_Q3_WIDTH){
        throw new NumberFormatException("Ball's X position is off the field");
      }
      for (Ball b2 : ballArray) {
        if (b1.getXLoc() == b2.getXLoc()){
          throw new NumberFormatException("Ball's starting X positions can not be the same");
        }
      }
    }
  }

  public void validateBallYLocation() throws NumberFormatException {
    for (Ball b1 : ballArray) {
      if (b1.getYLoc() > Q1_AND_Q2_HEIGHT ||b1.getYLoc() < Q3_AND_Q4_HEIGHT){
        throw new NumberFormatException("Balls' Y position is off the field");
      }
      for (Ball b2 : ballArray) {
        if (b1.getYLoc() == b2.getYLoc()){
          throw new NumberFormatException("Balls' starting Y positions  can not be the same");
        }
      }
    }
  }

  public void validateBallXVelocity() throws NumberFormatException {
    for (Ball b : ballArray) {
        if(b.getXVel() > Q1_AND_Q4_WIDTH || b.getXVel() < Q2_AND_Q3_WIDTH){
         throw new NumberFormatException("Ball's X velocity sends ball off the field");
       }
    }
  }

  public void validateBallYVelocity() throws NumberFormatException {
    for (Ball b : ballArray) {
        if(b.getYVel() > Q1_AND_Q2_HEIGHT || b.getYVel() < Q3_AND_Q4_HEIGHT){
         throw new NumberFormatException("Ball's Y velocity sends ball off the field");
       }
    }
  }

  public static void main(String[] args) {
    SoccerSim sse = new SoccerSim(args);
    try {
      sse.validateBallXLocation();
      sse.validateBallYLocation();
      sse.validateBallXVelocity();
      sse.validateBallYVelocity();
      for (Ball b : ballArray){
      System.out.println("INITIAL REPORT: " + b.toString());
      }
      while(!sse.atRest() || sse.ballIsOnField() || !sse.hasCollided()) {
        sse.toString()
         sse.run();
      }
      System.out.println("COLLSION @:");
    }
    catch( NumberFormatException nfe ){System.out.println(nfe);}
    System.exit( 0 );
 }
}
