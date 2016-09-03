package recursion;

import lib.AssortedMethods;

import java.util.Arrays;

/**
 * Created by czl on 03/09/16.
 */
public class MagicIndex {

    public static int findMagicIndex(int[] a) {
        return _findMagicIndex(a, 0, a.length - 1);
    }

    public static int _findMagicIndex(int[] a, int start, int end) {

        if (start == end) {
            return a[start] == start ? start : -1;
        }

        int mid = (end + start)/2;
        if (a[mid] == mid) {
            return mid;
        } else if (a[mid] > mid) {
            // search left
            return _findMagicIndex(a, start, mid);
        } else {
            // search right
            return _findMagicIndex(a, mid + 1, end);
        }
    }

    public static void main(String[] args) {
        int[] a = new int[] {-12, -8, -4, -2, 3, 5 , 7};
        System.out.println(findMagicIndex(a));
    }
}
