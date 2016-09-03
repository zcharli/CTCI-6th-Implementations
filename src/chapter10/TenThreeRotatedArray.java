package chapter10;

/**
 * Created by cli on 7/11/16.
 */
public class TenThreeRotatedArray {

    public static int search(int[] a, int n) {
        if (a == null || a.length <= 1)
            return 0;

        return search(a, 0, a.length - 1, n);
    }

    public static int search(int[] a, int left, int right, int n) {
        // Can't handle duplicates at the moment
        int mid = (right + left) / 2;
        if (a[left] == n) {
            return left;
        }
        if (a[right] == n) {
            return right;
        }
        if (a[mid] == n) {
            return mid;
        }

        // Find which side is smaller
        if (a[left] < a[mid]) {
            // Left is order propertly
            if (n < a[mid] && a[left] <= n) {
                return search(a, left, mid - 1, n);
            } else {
                return search(a, mid + 1, right, n);
            }
        } else if (a[left] > a[mid]) {
            // Right is ordered propertly
            if (n > a[mid] && a[right] >= n) {
                return search(a, mid + 1, right, n);
            } else {
                return search(a, left, mid - 1, n);
            }
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] a = { 15, 16, 19, 20, 25, 1, 3, 4, 5, 7, 10, 14 };

        System.out.println(search(a, 2));
        System.out.println(search(a, 3));
        System.out.println(search(a, 4));
        System.out.println(search(a, 1));
        System.out.println(search(a, 25));
    }
}
