package random;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by czl on 05/09/16.
 */
public class Calculator {

    public static int calculate(String eq) {
        if (eq == null || eq.length() < 4) {
            return 0;
        }

        char[] characters = eq.toCharArray();
        List<Integer> nums = new ArrayList<>();
        char operand = characters[1];
        int[] referenceBox = new int[1];
        for (int i = 2; i < characters.length; i++) {
            if (characters[i] == ' ') {
                continue;
            }
            if (characters[i] == '(') {
                int brackets = calculate(eq.substring(i));
                nums.add(brackets);
                int skipIndexes = findNextClosingBracketIndex(characters, i);
                i += skipIndexes;
            } else if (isDigit(characters[i])) {
                int numIndexesSkipped = determineNumber(characters, i, referenceBox);
                nums.add(referenceBox[0]);
                i += numIndexesSkipped;
            } else if (characters[i] == ')') {
                return applyOperand(operand, nums);
            }
        }
        return -1;
    }

    public static int findNextClosingBracketIndex(char[] chars, int start) {
        if (chars == null) {
            return 0;
        }
        int numOpens = 1; // when this goes to zero, then we break;
        int runs = 0;
        while (numOpens != 0) {
            start++; // start running
            if (chars[start] == '(') {
                numOpens++;
            } else if (chars[start] == ')') {
                numOpens--;
            }
            runs++;
        }
        return runs;
    }

    public static int determineNumber(char[] chars, int start, int[] referenceBox) {
        if (chars == null || chars.length == 0 || referenceBox == null || referenceBox.length < 1) {
            return 0;
        }

        int digits = 0;
        int runIdx = start;
        while (isDigit(chars[runIdx])) {
            ++digits;
            ++runIdx;
        }
        int answer = 0;
        int multiples = 1;
        for (int i = start + digits - 1; i >= start; i--) {
            answer += (chars[i] - 48) * multiples;
            multiples *= 10;
        }
        referenceBox[0] = answer;
        return digits - 1;
    }

    public static int applyOperand(char op, List<Integer> numbers) {
        int acc = 0;

        switch (op) {
            case '+':
                return numbers.stream().mapToInt(Integer::intValue).sum();
            case '*':
                return numbers.stream().mapToInt(Integer::intValue).reduce((x, y)->(x * y)).getAsInt();
            case '/':
                return numbers.stream().mapToInt(Integer::intValue).reduce((x, y)->(x / y)).getAsInt();
            case '-':
                return numbers.stream().mapToInt(Integer::intValue).reduce((x, y)->(x - y)).getAsInt();
        }
        return acc;
    }


    public static boolean isDigit(char c) {
        return c >= 48 && c <= 57;
    }

    public static void main(String[] args) {
        String eq1 = "(+ 3 3)";
        String eq2 = "(+ 1 12 (- 17 3) 5 (* 2 8 (/ 120 4)) 46)";

        int sol = calculate(eq2);
        System.out.println(sol);
    }
}
