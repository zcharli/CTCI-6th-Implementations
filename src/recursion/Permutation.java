package recursion;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by cli on 9/3/2016.
 */
public class Permutation {

    public static <T> List<List<T>> computePermutation(List<T> input) {
        List<List<T>> perms = new ArrayList<>();
        List<T> newPerm;
        if (input == null) {
            return perms;
        }
        if (input.size() == 1) {
            newPerm = new ArrayList<>();
            newPerm.addAll(input);
            perms.add(newPerm);
            return perms;
        }

        for (int i = 0; i < input.size(); i++) {
            newPerm = new ArrayList<>(input);
            T pivot = newPerm.remove(i);
            List<List<T>> subPermutation = computePermutation(newPerm);
            for(List<T> permutations : subPermutation) {
                List<T> retComposedPerms = new ArrayList<>(permutations);
                retComposedPerms.add(pivot);
                perms.add(retComposedPerms);
            }
        }
        return perms;
    }

    public static void computePermutationsFastDuplicate(int remaining, String currentString, Map<Character, Integer> freqTable, List<String> results) {
        if (freqTable == null || freqTable.size() == 0) {
            return;
        }

        if (remaining == 0) {
            results.add(currentString);
            return;
        }

        for (Character key : freqTable.keySet()) {
            int amount = freqTable.get(key);
            if (amount > 0) {
                freqTable.put(key, amount - 1);
                computePermutationsFastDuplicate(remaining - 1, currentString + key, freqTable, results);
                freqTable.put(key, amount);
            }
        }
    }

    public static List<String> computeCharacterPermutationsFastDuplicate(String input) {
        List<Character> chars = input.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        List<String> strings = new ArrayList<>();
        Map<Character, Integer> freqTable = new HashMap<>();
        for (Character c : chars) {
            if (!freqTable.containsKey(c)) {
                freqTable.put(c, 1);
            } else {
                freqTable.put(c, freqTable.get(c) + 1);
            }
        }
        computePermutationsFastDuplicate(chars.size(), "", freqTable, strings);
        return strings;
    }

    public static List<String> computeCharacterPermutation(String input) {
        List<Character> chars = input.chars().mapToObj(c -> (char)c).collect(Collectors.toList());
        List<String> strings = new ArrayList<>();
        for (List<Character> string : computePermutation(chars)) {
            String perm = string.stream().map(c -> c.toString()).reduce((acc, y) -> acc + y).get();
            strings.add(perm);
        }
        return strings;
    }

    public static void main(String[] args) {
        String input = "back";
        List<String> ret = computeCharacterPermutation(input);
        Set<String> duplicateChecker = new HashSet<>();
        for (String perm : ret) {
            duplicateChecker.add(perm);
            System.out.println(perm);
        }
        duplicateChecker = new HashSet<>();
        ret = computeCharacterPermutationsFastDuplicate(input);
        for (String perm : ret) {
            duplicateChecker.add(perm);
            System.out.println(perm);
        }
        System.out.println(ret.size() + " unique: " + duplicateChecker.size());
    }
}
