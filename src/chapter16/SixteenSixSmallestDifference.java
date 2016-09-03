package chapter16;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by czl on 03/08/16.
 */
public class SixteenSixSmallestDifference {

    public static int findSmallestDifference(int[] a1, int[] a2) {
        List<Integer> a1List = Arrays.stream(a1)
                                     .boxed()
                                     .collect(Collectors.toList());
        List<Integer> a2List = Arrays.stream(a2)
                                     .boxed()
                                     .collect(Collectors.toList());
        Collections.sort(a1List);
        Collections.sort(a2List);

        int index1 = 0;
        int index2 = 0;
        int smallestDiff = Integer.MAX_VALUE;
        int curDiff = 0;
        while( index1 < a1List.size() && index2 < a2List.size() ) {
            curDiff = Math.abs(a1List.get(index1) - a2List.get(index2));
            if (curDiff < smallestDiff) {
                if (curDiff == 0)
                    return 0;
                smallestDiff = curDiff;
            }
            if (a1List.get(index1) < a2List.get(index2)) {
                index1++;
            } else {
                index2++;
            }
        }

        return curDiff;
    }

    public static void main(String[] args) {
        int[] array1 = {1,15,20,45,60};
        int[] array2 = {4,5,6,8,62};
        int diffA = findSmallestDifference(array1, array2);
        System.out.println(diffA);
    }
}
