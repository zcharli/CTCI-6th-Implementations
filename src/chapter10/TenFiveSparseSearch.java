package chapter10;

/**
 * Created by czl on 11/07/16.
 */
public class TenFiveSparseSearch {

    public static int search(String[] a, int start, int end, String e) {
        // Binary search that
        while (start <= end) {
            int mid = (start + end) / 2;
            int nextLeft = mid + 1;
            int nextRight = mid - 1;

            if (a[mid].isEmpty()) {
                // if not empty, check mid before spanning out
                while (true) {
                    // find a non-empty string
                    if (nextLeft > start && !a[nextLeft].isEmpty()) {
                        mid = nextLeft;
                        break;
                    } else if (nextRight < end && !a[nextRight].isEmpty()) {
                        mid = nextRight;
                        break;
                    } else if (nextLeft == end && nextRight == 0) {
                        return -1;
                    }
                    nextLeft = nextLeft - 1 < 0 ? 0 : nextLeft - 1;
                    nextRight = nextRight + 1 > end ? end : nextRight + 1;
                }
            }

            if (a[mid].compareTo(e) < 0) {
                start = mid + 1;
            } else if (a[mid].compareTo(e) > 0) {
                end = mid - 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    public static int search(String[] a, String e) {
        if (a == null || a.length == 0) {
            return -1;
        }
        return search(a, 0, a.length - 1, e);
    }

    public static void main(String[] args) {
        String[] stringList = {"apple", "", "", "banana", "", "", "bandana", "carrot", "duck", "", "", "eel", "", "flower"};
        search(stringList, "bandana");
        for (String s : stringList) {
        	String cloned = new String(s);
        	System.out.println("<" + cloned + "> " + " appears at location " + search(stringList, cloned));
        }
    }
}
