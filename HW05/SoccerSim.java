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

   private boolean anyCollision = false;


  private static double [] pole = new double []{-100, 100};



  public SoccerSim(String[] args){
    numBalls = (int)(args.length/4);
    ballArray = new Ball[numBalls];
    if(args.length % 4 == 1){
      timeSlice = Double.parseDouble(args[args.length-1]);
      int j=0;
      for (int i=0; i < args.length-1; i+=4) {
        Double xpos = Double.parseDouble(args[i+0]);
        Double ypos = Double.parseDouble(args[i+1]);
        Double xvel = Double.parseDouble(args[i+2]);
        Double yvel = Double.parseDouble(args[i+3]);
        ballArray[i/4] = new Ball (xpos, ypos, xvel, yvel, timeSlice);
        j++;
      }
    }
    else if(args.length % 4 == 0) {
      timeSlice = DEFAULT_TIMESLICE_SECONDS;
      int k=0;
      for (int i=0; i < args.length; i+=4) {
        Double xpos = Double.parseDouble(args[i+0]);
        Double ypos = Double.parseDouble(args[i+1]);
        Double xvel = Double.parseDouble(args[i+2]);
        Double yvel = Double.parseDouble(args[i+3]);
        ballArray[k] = new Ball (xpos, ypos, xvel, yvel, timeSlice);
        k++;
      }
    }
    else {
      throw new IllegalArgumentException("Wrong number of input values");
    }
    timer = new Timer(timeSlice);
  }

  public boolean hasCollided() {
    int count = 1;
    for (Ball b1 : ballArray) {
      if (Math.sqrt(Math.pow(pole[0] - b1.getXLoc(),2) + (Math.pow(pole[1] - b1.getYLoc(), 2))) < ballRadius){
        System.out.println("BALL " + count + " COLLIDED WITH POLE \n @  X:" + b1.getXLoc() + ", Y:" + b1.getYLoc() + " AT TIME " +timer.toString());
        anyCollision = true;
        return true;
      }
      for (Ball b2: ballArray) {
        if(Math.sqrt(Math.pow(b2.getXLoc() - b1.getXLoc(),2) + (Math.pow(b2.getYLoc() - b1.getYLoc(), 2))) < ballRadius && b1 != b2){
          System.out.println("*************************************************");
          System.out.println("COLLISION @ TIME " + timer.toString() + " \n @ POSITION X:" + b1.getXLoc() + ", Y:" + b1.getYLoc());
          anyCollision = true;
          return true;
        }
      }
      count++;
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
      if (b.getXLoc() > Q1_AND_Q4_WIDTH || b.getXLoc() < Q2_AND_Q3_WIDTH || b.getYLoc() > Q1_AND_Q2_HEIGHT || b.getXLoc() < Q3_AND_Q4_HEIGHT){
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

  public String toString() {
    String progressReport = "PROGRESS REPORT @ TIME: " + timer.toString() + "\n";
    int count = 1;
    for (Ball b : ballArray){
      progressReport += "BALL " + count + " => " + b.toString()+ "\n";
      count++;
    }
    return progressReport;
  }
  // if ball is at rest and collision hasn't happened its not possible
  // initial report
  // progress report @ ____
  // has collided (where and when)

  public void validateBallLocation() throws NumberFormatException {
    for (Ball b1 : ballArray) {
      if (b1.getXLoc() > Q1_AND_Q4_WIDTH ||b1.getXLoc() < Q2_AND_Q3_WIDTH){
        throw new NumberFormatException("Ball's X position is off the field");
      }
      if (b1.getYLoc() > Q1_AND_Q2_HEIGHT ||b1.getYLoc() < Q3_AND_Q4_HEIGHT){
        throw new NumberFormatException("Balls' Y position is off the field");
      }
      for (Ball b2 : ballArray) {
        if (b1.getXLoc() == b2.getXLoc() && b1.getYLoc() == b2.getYLoc() && b1 != b2){
          throw new NumberFormatException("Ball's starting  positions can not be the same");
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
      sse.validateBallLocation();
      sse.validateBallXVelocity();
      sse.validateBallYVelocity();
      System.out.println("INITIAL REPORT");
      for (Ball b : sse.ballArray){
        System.out.println(b);
      }
      System.out.println("*************************************************");
      while(!sse.atRest() && sse.ballIsOnField() && !sse.hasCollided()) {
        sse.hasCollided();
        sse.run();
        System.out.println(sse.toString());
      }
      if (!sse.anyCollision) {
        System.out.println("*************************************************");
        System.out.println("NO COLLISION POSSIBLE");
      }
    }
    catch( NumberFormatException nfe ){System.out.println(nfe);}
    System.exit( 0 );
 }
}
