package chapter8;

import com.sun.deploy.util.StringUtils;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * Created by cli on 6/19/16.
 */
public class EightNineParenthesis {

    public static ArrayList<String> validParenthesis(int n) {

        ArrayList<String> parens = new ArrayList<>();
        validParenthesis(n, 1, parens);
        return parens;
    }

    public static void validParenthesis(int max, int numberOfParenthesis, ArrayList<String> buildUp) {
        // Trying a bottom up solution

        if (numberOfParenthesis == 0) {
            return;
        } else if (numberOfParenthesis == 1) {

            buildUp.add("()");
            if (numberOfParenthesis < max) {
                validParenthesis(max, numberOfParenthesis + 1, buildUp);
            }
        } else {
            HashSet<String> checker = new HashSet<>();

            for (String parens : buildUp) {

                // Apply add right
                String addedRight = "()" + parens;
                if (!checker.contains(addedRight)) {
                    checker.add(addedRight);
                }

                // Apply add center
                ArrayList<Integer> openIndexes = findAllOpeningBracketIndexes(parens);
                for (Integer matchBeginIndx : openIndexes) {
                    // Try to insert a bracket at each opening '(' we can find
                    String first = parens.substring(0, matchBeginIndx + 1);
                    String last = parens.substring(matchBeginIndx + 1, parens.length());
                    String newMatching = first + "()" + last;

                    if (!checker.contains(newMatching)) {
                        checker.add(newMatching);
                    }
                }
            }

            // Clear the junkies (parenthesis count less than numberOfParenthesis)
            buildUp.clear();
            buildUp.addAll(checker);

            if (numberOfParenthesis != max) {
                validParenthesis(max, numberOfParenthesis + 1, buildUp);
            } else {
                return;
            }
        }
    }

    public static ArrayList<Integer> findAllOpeningBracketIndexes(String s) {
        char[] toChar = s.toCharArray();
        ArrayList<Integer> openingBracIdx = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (toChar[i] == '(') {
                openingBracIdx.add(i);
            }
        }
        return openingBracIdx;
    }

    public static void main(String[] args) {
        ArrayList<String> l = validParenthesis(3);
        for (String s : l) {
            System.out.println(s);
        }
    }
}
