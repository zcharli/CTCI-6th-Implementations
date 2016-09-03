package recursion;

import java.util.Stack;

/**
 * Created by czl on 03/09/16.
 */
public class TowerOfHanoi {

    public static void solve(int disks, int source, int target, int auxiliary, Stack<Integer>[] towers) {
        if (disks <= 0) {
            return;
        }

        solve(disks - 1, source, auxiliary, target, towers);

        towers[target].add(towers[source].pop());
        print(towers);
        solve(disks - 1, auxiliary, target, source, towers);
    }

    public static void print(Stack<Integer>[] hanoi) {
        for (int i = 0; i < hanoi.length; i++) {
            for(Integer e : hanoi[i]) {
                System.out.print(e + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        int n = 3;

        Stack<Integer> left = new Stack<>();
        for (int i = n; i > 0; --i) {
            left.add(i);
        }
        Stack<Integer> mid = new Stack<>();
        Stack<Integer> right = new Stack<>();

        Stack<Integer>[] hanoi = new Stack[]{left, mid, right};

        solve(n, 0, 2, 1, hanoi);
        System.out.println(count);
    }
}
