import java.util.Scanner;

/**
 * This program calculates the distance between 2 points of location based on
 * user input of latitudes and longitudes.
 */
public class Distance {
    //Define global constants
    static final double RADIUS_EARTH = 6372.795;    //Radius of the earth
    static final double MILES_PER_KM = 0.62137119;  //# of miles per kilometer
    /**
     * Main method for distance calculation program. It calls for the user to
     * input data and calculates the central angle between the two points. It then
     * calculates the distance between the two points and prints the result in miles.
     */
    public static void main(String[] args) {
        Scanner console = new Scanner (System.in);
        double centralAngle = central_angle(console);
        distance(centralAngle);
    }
    /**
     * Prompt user for longitude and latitude points. Calculate central angle.
     * @param console The Scanner object to retrieve user inputs
     * @return the central angle in radians
     */
    public static double central_angle (Scanner console) {
        //user input
        System.out.println("Please input the location in 'DDD MM SS Direction' format.\n");
        System.out.println("Latitude 1?");
        double lat1 = get_lat(console);
        System.out.println("Longitude 1?");
        double long1 = get_long(console);
        System.out.println("Latitude 2?");
        double lat2 = get_lat(console);
        System.out.println("Longitude 2?");
        double long2 = get_long(console);
        //Calculating the central angle
        double centralAngle = Math.acos(Math.sin(lat1)*Math.sin(lat2) 
            + Math.cos(lat1)*Math.cos(lat2)*Math.cos(long1-long2));
        return centralAngle;
    }    
    /**
     * Allows user to input latitude point in DDD MM SS Direction format.
     * @param console The Scanner object to retrieve user inputs
     * @return the latitude point in radians
     */    
    public static double get_lat (Scanner console) {
        boolean error = false;          //test for user error
        int DDD = console.nextInt();    //integer degrees
            if (DDD<0 || DDD>90) {
                error = true;  
            }
        int MM = console.nextInt();     //integer minutes
            if (MM<0 || MM>59) {
                error = true;  
            }
        int SS = console.nextInt();     //integer seconds
            if (SS<0 || SS>59) {
                error = true;  
            }
        char charDirection = Character.toLowerCase(console.next().charAt(0));
                                        //Direction: N or S
        int Direction = 0;  //Converts charDirection into positive or negative              
        if (charDirection == 110) {             //N
            Direction = 1;
        } else if (charDirection == 115) {      //S       
            Direction = -1;
        } else {
            error = true;
        }
        double point = 0;
        if (error==false) {
            point = Math.toRadians(Direction * (DDD + MM/60.0 + SS/3600.0));     
        } else {
            System.out.println("\nPlease check your input and re-run the program. Latitude degrees only");
            System.out.println("range from 0 to 90. MM and SS represent integer minute and second");
            System.out.println("respectively, ranging from 0 to 59. And Direction is either N or S.");
            System.exit(0);     //user asked to try again if there is input error
        }
        return point;
    }
    /**
     * Allows user to input longitude point in DDD MM SS Direction format.
     * @param console The Scanner object to retrieve user inputs
     * @return the longitude point in radians
     */    
    public static double get_long (Scanner console) {
        boolean error = false;          //test for user error
        int DDD = console.nextInt();    //integer degrees
            if (DDD<0 || DDD>180) {
                error = true;  
            }
        int MM = console.nextInt();     //integer minutes
            if (MM<0 || MM>59) {
                error = true;  
            }
        int SS = console.nextInt();     //integer seconds
            if (SS<0 || SS>59) {
                error = true;  
            }
        char charDirection = Character.toLowerCase(console.next().charAt(0));
                                        //Direction: E or W
        int Direction = 0;  //Converts charDirection into positive or negative              
        if (charDirection == 101) {             //E
            Direction = 1;
        } else if (charDirection == 119) {      //W        
            Direction = -1;
        } else {
            error = true;
        }
        double point = 0;
        if (error==false) {
            point = Math.toRadians(Direction * (DDD + MM/60.0 + SS/3600.0));     
        } else {
            System.out.println("\nPlease check your input and re-run the program. Longitude degrees only");
            System.out.println("range from 0 to 180. MM and SS represent integer minute and second");
            System.out.println("respectively, ranging from 0 to 59. And Direction is either E or W.");
            System.exit(0);     //user asked to try again if there is input error
        }
        return point;
    }
    /**
     * Calculates the distance and print result in miles.
     * @param centralAngle the central angle between two points of location
     * @return the distance in miles
     */
    public static double distance (double centralAngle) {
        double distance = centralAngle*RADIUS_EARTH;    //result in kilometers
        distance = distance*MILES_PER_KM;               
        distance = Math.round(distance*100)/100.0;      
        System.out.println("\nThe distance between the two points is " + distance + " mi.");
        return distance;    
    }
}