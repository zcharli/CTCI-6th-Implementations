package chapter8;


/**
 * Created by cli on 6/26/16.
 */
public class EightElevenCoins {

    public static int[] denom = new int[]{25, 10, 5, 1};

    public static int findCoins(int amt) {

        int[][] memo = new int[amt + 1][denom.length]; // calculate the number of ways to make `amt - n` coins

        return findCoins(memo, amt, 0);
    }

    public static int findCoins(int[][] memo, int max, int denomIdx) {

        if (denomIdx >= denom.length - 1) {
            return 1;
        }

        if (memo[max][denomIdx] != 0) {
            return memo[max][denomIdx];
        }

        int denomAmt = denom[denomIdx];
        int changeWays = 0;

        for (int i = 0; i * denomAmt <= max; i++) {
            int amtLeft = max - i * denomAmt;
            changeWays += findCoins(memo, amtLeft, denomIdx + 1);
        }

        memo[max][denomIdx] = changeWays;

        return changeWays;
    }

    public static void main(String[] args) {
        int combos = findCoins(98);


        System.out.println(combos);
    }
}