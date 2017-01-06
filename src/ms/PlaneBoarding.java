package ms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cli on 12/20/2016.
 */
public class PlaneBoarding {

    public static String convertCooordinates(int x, int y) {
        if (y > 26) {
            System.out.println("Cheap exeception, y is too large");
            System.exit(0);
        }

        char fromInteger = (char)(y + 65);
        return String.format("%c%d", fromInteger, x + 1);
    }

    public static void main(String[] args) {
        List<List<String>> lazyPaths = new ArrayList<>();

        Scanner sc = new Scanner(System.in);
        String line = "";
        List<String> test = new ArrayList<>();

        while(!(line = sc.nextLine()).equals("")) {
            test.add(line);
        }

        if (test.size() < 3) System.out.println("Invalid params");

        int width = Integer.parseInt(test.get(0));
        int height = Integer.parseInt(test.get(1));
        int[][] plane = new int[height][width];
        int lastLazyHeight = 0;
        String[] passengers = test.get(2).split(",");
        int numLazy = 0;
        for (int i = 0; i < passengers.length; i++) {
            boolean isLazy = passengers[i].charAt(passengers[i].length() - 1) == '*';
            if (isLazy) {
                numLazy++; // ID of the lazy person, also will indicate index in lazyPaths, remmember to decrement 1
                // find first available stop
                for (int j = lastLazyHeight; j < height; j++) {
                    boolean found = false;
                    for (int k = 0; k < width; k++) {
                        if (plane[j][k] == 0) {
                            // seat the new lazy flier here
                            plane[j][k] = numLazy;
                            List<String> lazyPath = new ArrayList<>();
                            lazyPath.add(convertCooordinates(j,k));
                            lazyPaths.add(lazyPath);
                            found = true;
                            lastLazyHeight = j;
                            break;
                        }
                    }
                    if (found) {
                        break;
                    }
                }
            } else {
                int passengerRow = passengers[i].charAt(0) - 65;
                int passengerHeight = Integer.parseInt(passengers[i].substring(1, passengers[i].length())) - 1;

                if (plane[passengerHeight][passengerRow] != 0) {
                    // kick the passenger forward
                    int indexOfPassenger = plane[passengerHeight][passengerRow] - 1;
                    List<String> lazyPath = lazyPaths.get(indexOfPassenger);
                    for (int j = passengerHeight; j < height; j++) {
                        boolean found = false;
                        for (int k = 0; k < width; k++) {
                            if (plane[j][k] == 0) {
                                // move the lazy flier here
                                lazyPath.add(convertCooordinates(j,k));
                                lastLazyHeight = k;
                                found = true;
                                plane[j][k] = plane[passengerHeight][passengerRow];
                                break;
                            }
                        }
                        if (found) {
                            break;
                        }
                    }
                }
                plane[passengerHeight][passengerRow] = -1;
            }
        }

        for (int i = 0; i < lazyPaths.size(); i++) {
            for (int j = 0; j < lazyPaths.get(i).size(); j++) {
                System.out.print(lazyPaths.get(i).get(j));
                if (j < lazyPaths.get(i).size() - 1) {
                    System.out.print(",");
                }
            }
            System.out.print("\n");
        }


    }
}
