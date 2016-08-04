package chapter10;

import lib.AssortedMethods;

/**
 * Created by czl on 12/07/16.
 */
public class TenEightFindDuplicates {

    public static void checkDuplicates(int[] a, int max) {
        if ( a == null || a.length == 0) {
            return;
        }
        byte[] checker = new byte[(max / 8) + 1];

        for (int i = 0; i < a.length; i++) {

            int index = a[i] / 8;
            int mask = (1 << (a[i] % 8));
            if ((checker[index] & mask) == mask ) {
                System.out.println(a[i]);
            } else {
                checker[index] |= (1 << (a[i] % 8));
            }
        }
        
    }
    
    public static void main(String[] args) {
        int[] array = AssortedMethods.randomArray(30, 1, 30);
        System.out.println(AssortedMethods.arrayToString(array));
        checkDuplicates(array, 30);
    }
}
