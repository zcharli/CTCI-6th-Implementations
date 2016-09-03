package recursion;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by czl on 03/09/16.
 */
public class PowerSet {

    public static <T> Set<Set<T>> generateSubsets(Set<T> s) {
        Set<Set<T>> current = new HashSet<>();

        if (s.size() == 0) {
            current.add(new HashSet<>());
            return current;
        }
        Set<T> subset = new HashSet<>(s);
        T head = subset.iterator().next();
        subset.remove(head);

        Set<Set<T>> subsets = generateSubsets(subset);
        for(Set<T> set : subsets) {
            Set<T> newSet = new HashSet<>(set);
            newSet.add(head);
            current.add(newSet);
            current.add(set);
        }
        return current;
    }

    public static <T> Set<Set<T>> generateSubsetIterative(Set<T> s) {
        Set<Set<T>> current = new HashSet<>();
        current.add(new HashSet<>());

        for(T e : s) {
            Set<Set<T>> newSets = new HashSet<>();
            for(Set<T> set : current) {
                Set<T> newSet = new HashSet<>(set);
                newSet.add(e);
                newSets.add(newSet);
            }
            current.addAll(newSets);
        }
        return current;
    }

    public static void main(String[] args) {
        Set<Integer> s = new HashSet<>();
        s.add(1);
        s.add(2);
        s.add(3);
        s.add(4);

        System.out.println(generateSubsets(s));
        System.out.println(generateSubsetIterative(s));
    }
}
