package chapter17;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Created by czl on 05/09/16.
 */
public class SeventeenNineteenMissingTwo {

    public static int missingOne(int[] a) {
        int n = a.length + 1;
        int expectedSum = (n*(n + 1))/2;
        int actualSum = Arrays.stream(a).sum();
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int max = 100;
        int x = 8;
        int len = max - 1;
        int count = 0;
        int[] array = new int[len];
        for (int i = 1; i <= max; i++) {
            if (i != x) {
                array[count] = i;
                count++;
            }
        }
        System.out.println(x);
        int solution = missingOne(array);

        System.out.println(solution);
    }
}
