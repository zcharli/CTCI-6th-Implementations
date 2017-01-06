package ms;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by cli on 12/20/2016.
 */
public class Order {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int length = Integer.parseInt(sc.nextLine());

        String line = "";
        List<String> test = new ArrayList<>();
        char[][] results = new char[length][length];
        int i = 0;
        while(!(line = sc.nextLine()).equals("")) {
            results[i++] = line.toCharArray();
        }

        for (int j = 0; j < length; j++) {
        }
    }
}
