public class CountTheDays {
  public static void main (String [] args){
    long[] stringToLong = new long[6];
    for(int i=0; i < 6; i++){
      stringToLong[i] = Long.parseLong(args[i]);
  }
  System.out.println(CalendarStuff.daysBetween(stringToLong[0],stringToLong[1],stringToLong[2],stringToLong[3],stringToLong[4],stringToLong[5]));
 }
}
