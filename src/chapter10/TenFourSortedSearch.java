package chapter10;

import lib.Listy;

/**
 * Created by czl on 11/07/16.
 */
public class TenFourSortedSearch {

    public static int search(Listy l, int e) {
        int i = 1;
        int c = l.elementAt(i);
        while (c != -1 && l.elementAt(i) < e) {
            i = i << 1;
            c = l.elementAt(i);
        }
        int s = binarySearch(l, i / 2, i, e);
        return l.elementAt(s) == e ? s : -1;
    }

    public static int binarySearch(Listy l, int start, int end, int e) {
        int mid = (start + end) / 2;
        if (start >= end || l.elementAt(mid) == e) {
            return mid;
        } else if(l.elementAt(mid) > e) {
            return binarySearch(l, start, mid -1, e);
        } else {
            return binarySearch(l, mid + 1, end, e);
        }
    }


    public static void main(String[] args) {
        int[] array = {1, 2, 4, 5, 6, 7, 9, 10, 11, 12, 13, 14, 16, 18, 39, 40};
        Listy list = new Listy(array);
        for (int a : array) {
            System.out.println(a + ": " +search(list, a));
        }
        System.out.println(search(list, 41));
    }
}
