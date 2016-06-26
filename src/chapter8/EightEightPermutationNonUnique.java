package chapter8;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by cli on 6/19/16.
 */
public class EightEightPermutationNonUnique {

    public static HashSet<String> findPermutationNoDuplications(String s, int i) {
        HashSet<String> perms = new HashSet<>();

        if (i == s.length() - 1) {
            perms.add(s);
            return perms;
        }

        HashSet<String> currPerms = findPermutationNoDuplications(s, i + 1);

        for (String string : currPerms) {
            for (int j = i; j < string.length() - 1; j++) {
                char[] strDecomp = string.toCharArray();
                char temp = strDecomp[i];
                // swap chars
                strDecomp[i] = strDecomp[j + 1];
                strDecomp[j + 1] = temp;
                perms.add(new String(strDecomp));
            }
        }
        perms.addAll(currPerms);
        return perms;
    }

    public static void main(String[] args) {
        String s = "aabbccc";
        HashSet<String> perms = findPermutationNoDuplications(s, 0);
        for (String str : perms) {
            System.out.println(str);
        }
        System.out.println(perms.size());
    }
}
