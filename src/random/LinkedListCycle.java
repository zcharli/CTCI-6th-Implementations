package random;

/**
 * Created by cli on 11/6/2016.
 */
public class LinkedListCycle {

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public static boolean hasCycle(ListNode head) {
        ListNode current = head;
        if (head == null) {
            return false;
        }
        ListNode runner = head.next;

        while (runner.next != null) {
            if (runner == current) {
                return true;
            }
            current = current.next;
            runner = runner.next.next;
        }
        return false;
    }

    public static ListNode detectCycle(ListNode head) {
        ListNode current = head;
        if (head == null) {
            return null;
        }
        ListNode runner = head.next;
        if (runner == null) {
            return null;
        }
        while (runner != null && runner.next != null) {
            if (runner == current) {
                current = head;
                while (runner.next != current) {
                    current = current.next;
                    runner = runner.next;
                }
                return current;
            }
            current = current.next;
            runner = runner.next.next;
        }
        return null;
    }

    public static void main(String[] args) {
        ListNode noCyle = makeNoCyle();
        System.out.println(hasCycle(noCyle));
        ListNode cycleEven = makeCycleEven();
        System.out.println(hasCycle(cycleEven));
        ListNode cycleOdd = makeCyleOdd();
        System.out.println(hasCycle(cycleOdd));

        System.out.println(detectCycle(noCyle));
        System.out.println(detectCycle(cycleEven).val);
        System.out.println(detectCycle(cycleOdd).val);

    }

    public static ListNode makeCyleOdd() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);

        // no cycle
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = six;
        return one;
    }

    public static ListNode makeCycleEven() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);

        // no cycle
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = eight;
        eight.next = one;
        return one;
    }

    public static ListNode makeNoCyle() {
        ListNode one = new ListNode(1);
        ListNode two = new ListNode(2);
        ListNode three = new ListNode(3);
        ListNode four = new ListNode(4);
        ListNode five = new ListNode(5);
        ListNode six = new ListNode(6);
        ListNode seven = new ListNode(7);
        ListNode eight = new ListNode(8);

        // no cycle
        one.next = two;
        two.next = three;
        three.next = four;
        four.next = five;
        five.next = six;
        six.next = seven;
        seven.next = eight;
        return one;
    }
}
