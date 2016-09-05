package chapter17;

import lib.AssortedMethods;

/**
 * Created by czl on 04/09/16.
 */
public class SeventeenSixteenTheMasseuse {
    public static int c = 0;
    public static int maxMinutes(int[] appointments) {
        c = 0;
        return _maxMinutes(appointments, 0);
    }

    public static int _maxMinutes(int[] app, int appIdx) {
        c++;
        // (2^n)
        int len = app.length - 1;
        if (appIdx == len) {
            return app[appIdx];
        } else if (appIdx > len) {
            return 0;
        }

        int takeCurrent = app[appIdx] + _maxMinutes(app, appIdx + 2);
        int takeNext = app[appIdx + 1] + _maxMinutes(app, appIdx + 3);
        if (takeCurrent > takeNext) {
            return takeCurrent;
        } else {
            return takeNext;
        }
    }

    public static int maxMinutesMemo(int[] app) {
        c = 0;
        int[] memo = new int[app.length];
        return _maxMinutesMemo(app, 0, memo);
    }

    public static int _maxMinutesMemo(int[] app, int appIdx, int[] memo) {
        c++;
        int len = app.length - 1;
        if (appIdx == len) {
            return app[appIdx];
        } else if (appIdx > len) {
            return 0;
        }

        if (memo[appIdx] != 0) {
            return memo[appIdx];
        }

        int takeCurrent = app[appIdx] + _maxMinutesMemo(app, appIdx + 2, memo);
        int takeNext = app[appIdx + 1] + _maxMinutesMemo(app, appIdx + 3, memo);
        int max = Math.max(takeCurrent, takeNext);
        memo[appIdx] = max;
        return max;
    }

    public static int maxMinutesG(int[] massages) {
        c=0;
        return _maxMinutesG(massages, 0);
    }

    public static int _maxMinutesG(int[] massages, int index) {
        c++;
        if (index >= massages.length) { // Out of bounds
            return 0;
        }

		/* Best with this reservation. */
        int bestWith = massages[index] + _maxMinutesG(massages, index + 2);

		/* Best without this reservation. */
        int bestWithout = _maxMinutesG(massages, index + 1);

		/* Return best of this subarray, starting from index. */
        return Math.max(bestWith, bestWithout);
    }

    public static void main(String[] args) {
//        int[] massages = {2*15, 1*15, 4*15, 5*15, 3*15, 1*15, 1*15, 3*15};
//        System.out.println(maxMinutes(massages));
//        System.out.println(c);
//        System.out.println(maxMinuteMemo(massages));
//        System.out.println(c);
//        System.out.println(maxMinutesG(massages));
//        System.out.println(c);

        int cutOff = 5;
        int numTests = 100;
//        for (int i = 1; i < cutOff; i++) {
//            int[] massages = generateRandomArray(i);
//            int maxA = maxMinutes(massages);
//            int maxB = maxMinutesMemo(massages);
//            int maxC = maxMinutesG(massages);
//            int[] list = sumEveryOther(massages);
//            if (maxA != list[0] && maxA != list[1]) {
//                System.out.println(AssortedMethods.arrayToString(massages));
//                System.out.println(maxA + ", " + maxB + ", " + maxC);
//            }
//
//            if (maxA != maxB || maxB != maxC) {
//                System.out.println("Error at " + i + ": " + maxA + ", " + maxB + ", " + maxC);
//            }
//        }

        for (int i = 25; i < 26; i++) {
            int[] massages = generateRandomArray(i);
            int maxB = maxMinutes(massages);
            System.out.println(c);
            int maxC = maxMinutesMemo(massages);
            System.out.println(c);
            int maxD = maxMinutesG(massages);
            System.out.println(c);
            int[] list = sumEveryOther(massages);
            if (maxB != list[0] && maxB != list[1]) {
                System.out.println(AssortedMethods.arrayToString(massages));
                System.out.println(maxB + ", " + maxC + ", " + maxD);
            }
            if (maxB != maxC || maxC != maxD) {
                System.out.println("Error at " + i + ": " + maxB + ", " + maxC + ", " + maxD);
            }
        }
        System.out.println("All tests have run.");
    }

    public static int[] generateRandomArray(int size) {
        int[] array = AssortedMethods.randomArray(size, 1, 10);
        for (int i = 0; i < array.length; i++) {
            array[i] *= 15;
        }
        return array;
    }

    public static int[] sumEveryOther(int[] array) {
        int first = 0;
        for (int i = 0; i < array.length; i += 2) {
            first += array[i];
        }

        int second = 0;
        for (int i = 1; i < array.length; i += 2) {
            second += array[i];
        }

        int[] a = {first, second};
        return a;
    }
}
