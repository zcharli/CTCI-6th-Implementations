package recursion;

import java.util.*;

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

    public static <T> List<List<T>> permutationFast(List<T> original) {
        List<List<T>> permutations = new ArrayList<>();
        _permutationFast(new ArrayList<>(original), 0, permutations);
        return permutations;
    }

    public static <T> void _permutationFast(List<T> original, int startIdx, List<List<T>> permutations) {

        for (int i = startIdx; i < original.size(); i++) {
            Collections.swap(original, i, startIdx);
            _permutationFast(original, startIdx + 1, permutations);
            Collections.swap(original, startIdx, i);
        }

        if (startIdx == original.size() - 1) {
            permutations.add(new ArrayList<>(original));
        }
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

        //System.out.println(countWays(98));

        List<Integer> list = new ArrayList<>();

        int max = 7;
        for (int i = 0; i < max; i++) {
            list.add(i);
        }

        long startTime = System.nanoTime();
        List<List<Integer>> longest = permutation(list);
        long endTime = System.nanoTime();
        long duration = (endTime - startTime);
        System.out.println(String.format("Charlies slow %d in %d ms", longest.size(), duration/1000000));

        long startTime2 = System.nanoTime();
        List<List<Integer>> longest2 = permutationFast(list);
        long endTime2 = System.nanoTime();
        long duration2 = (endTime2 - startTime2);
        System.out.println(String.format("Charlies fast %d in %d ms", longest2.size(), duration2/1000000));

        System.out.println(longest);
        System.out.println(longest2);

    }
































}
