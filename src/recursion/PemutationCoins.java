package recursion;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by czl on 04/09/16.
 */
public class PemutationCoins {

    public static <T> List<List<T>> powerset(List<T> original) {
        List<List<T>> powerset = new ArrayList<>();

        if (original.size() == 0) {
            powerset.add(original);
            return powerset;
        }

        T head = original.get(0);
        for (List<T> subset : powerset(original.subList(1, original.size() - 1))) {
            List<T> newSet = new ArrayList<>(subset);
            newSet.add(head);
            powerset.add(newSet);
            powerset.add(subset);
        }

        return powerset;
    }

    public static <T> List<List<T>> powersetIterative(List<T> original) {
        List<List<T>> powerset = new ArrayList<>();
        powerset.add(new ArrayList<>());

        for (T e : original) {
            List<List<T>> newSet = new ArrayList<>();
            for (List<T> subsets : powerset) {
                List<T> subset = new ArrayList<>(subsets);
                subset.add(e);
                newSet.add(subset);
            }
            powerset.addAll(newSet);
        }
        return powerset;
    }

    public static <T> List<List<T>> permutation(List<T> original) {
        List<List<T>> permutations = new ArrayList<>();

        if (original.size() == 1) {
            List<T> basePerm = new ArrayList<>(original);
            permutations.add(basePerm);
            return permutations;
        }

        for (int i = 0; i < original.size(); i++) {
            List<T> newPerm = new ArrayList<>(original);
            T head = newPerm.remove(i);
            List<List<T>> newPermutations = permutation(newPerm);
            for (List<T> permutation : newPermutations) {
                List<T> perm = new ArrayList<>(permutation);
                perm.add(head);
                permutations.add(perm);
            }
        }

        return permutations;
    }

    public static int countWays(int n) {
        int[][] memo = new int[n + 1][4];
        int[] denom = new int[] {25, 10, 5, 1};
        return _countWays(n, denom, 0, memo);
    }

    private static int _countWays(int curAmt, int[] denoms, int denomIdx, int[][] memo) {

        if (curAmt <= 0) {
            return 0;
        }

        if (denomIdx == 3) {
            return 1;
        }

        if (memo[curAmt][denomIdx] != 0) {
            return memo[curAmt][denomIdx];
        }

        int denomAmt = denoms[denomIdx];
        int changeWays = 0;

        for (int i = 0; i * denomAmt <= curAmt; ++i) {
            int amtLeft = curAmt - (i * denomAmt);
            changeWays += _countWays(amtLeft, denoms, denomIdx + 1, memo);
        }

        memo[curAmt][denomIdx] = changeWays;

        return changeWays;
    }

    public static void main(String[] args) {

        System.out.println(countWays(98));

    }
































}
