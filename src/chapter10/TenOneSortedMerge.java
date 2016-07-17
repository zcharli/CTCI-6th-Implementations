package chapter10;

import java.util.Arrays;

/**
 * Created by cli on 7/10/16.
 */
public class TenOneSortedMerge {

    public static void merge(int[] a, int[] b) {

        if (b == null || b.length == 0 || a == null || a.length < b.length)
            return;

        int a_start = a.length - 1;
        int b_start = b.length - 1;
        int currIdx = a_start;

        // Move idx
        while (a[a_start] == 0) {
            --a_start;
        }

        while (currIdx != -1) {
            if (currIdx == 0) {
                if (a[a_start] > b[b_start]) {
                    a[currIdx] = b[b_start];
                } else {
                    a[currIdx] = a[a_start];
                }
            } else if (a[a_start] > b[b_start]) {
                a[currIdx] = a[a_start];
                if (a_start - 1 >= 0) {
                    --a_start;
                }
            } else {
                a[currIdx] = b[b_start];
                if (b_start - 1 >= 0) {
                    --b_start;
                }
            }
            --currIdx;
        }
    }

    public static void main(String[] args) {
        int[] a = {2, 3, 4, 5, 6, 8, 10, 100, 0, 0, 0, 0, 0, 0};
        int[] b = {1, 4, 6, 7, 7, 7};
        merge(a, b);
        System.out.println(Arrays.toString(a));
    }
}

