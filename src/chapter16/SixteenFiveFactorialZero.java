package chapter16;

/**
 * Created by czl on 03/08/16.
 */
public class SixteenFiveFactorialZero {

    public static int trailingZeros(int factorial) {
        int count = 0;
        for (int i = 5; i <= factorial; i++) {
            int curFactorial = i;
            while (curFactorial % 5 == 0) {
                count++;
                curFactorial /= 5;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        for (int i = 1; i < 100; i++) {
            System.out.println(i + "! has " + trailingZeros(i) + " zeros");
        }
    }
}
