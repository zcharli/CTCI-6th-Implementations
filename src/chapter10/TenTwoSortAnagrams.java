package chapter10;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

/**
 * Created by cli on 7/10/16.
 */
public class TenTwoSortAnagrams {

    public static void sortAnagram(String[] a) {
        HashMap<String, List<String>> anagrams = new HashMap<>();

        for (int i = 0; i < a.length; i++) {
            String k = sortWord(a[i]);
            if (!anagrams.containsKey(k)) {
                anagrams.put(k, new ArrayList<>());
            }
            anagrams.get(k).add(a[i]);
        }

        int index = 0;
        for (String key : anagrams.keySet()) {

            List<String> words = anagrams.get(key);

            for (String anagram : words) {
                a[index++] = anagram;
            }
        }
    }

    public static String sortWord(String s) {
        char[] chars = s.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static void main(String[] args) {
        String[] array = {"apple", "banana", "carrot", "ele", "duck", "papel", "tarroc", "cudk", "eel", "lee"};
        sortAnagram(array);
        System.out.println(Arrays.toString(array));
    }
}
