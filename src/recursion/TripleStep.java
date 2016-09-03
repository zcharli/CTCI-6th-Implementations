package recursion;

import java.util.HashMap;

/**
 * Created by czl on 03/09/16.
 */
public class TripleStep {

    public static int countSteps(int stairs) {
        if ( stairs < 0 ) {
            return 0;
        } else if ( stairs == 0) {
            return 1;
        }

        return countSteps(stairs - 3) + countSteps(stairs - 2) + countSteps(stairs - 1);
    }

    public static HashMap<Integer, Integer> memo = new HashMap<>();

    public static int countStepsEfficient(int stairs) {

        return _countStepsEfficient(stairs);
    }

    private static int _countStepsEfficient(int stairs) {
        if ( stairs < 0 ) {
            return 0;
        }

        if (stairs == 0) {
            return 1;
        }

        if (memo.containsKey(stairs)) {
            return memo.get(stairs);
        } else {
            int three = _countStepsEfficient(stairs - 3);
            int two = _countStepsEfficient(stairs - 2);
            int one = _countStepsEfficient(stairs - 1);
            memo.put(stairs, three + two + one);
            return memo.get(stairs);
        }
    }

    public static void main(String[] args) {

        System.out.println(countSteps(19));
        System.out.println(countStepsEfficient(33));
    }
}
