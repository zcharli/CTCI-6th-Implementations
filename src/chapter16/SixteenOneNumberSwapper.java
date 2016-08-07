package chapter16;

/**
 * Created by cli on 7/17/16.
 */
public class SixteenOneNumberSwapper {

    public static void swap(int a, int b) {
        System.out.println(a + " " + b);
        a = a ^ b; // Find the bits that are different
        b = a ^ b; // Set bits to 1 in b that are not different between a and b
        a = a ^ b; // a is different bits between a and b, b is equal to a, undo step 1 to gain the value of b
        System.out.println(a + " " + b);
    }

    public static void main(String[] args) {
        swap(10,6);
    }
}
