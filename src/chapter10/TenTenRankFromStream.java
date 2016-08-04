package chapter10;

import lib.AssortedMethods;
import lib.LinkedListNode;

/**
 * Created by czl on 14/07/16.
 */
public class TenTenRankFromStream {

    public static LinkedListNode track(LinkedListNode head, int num) {
        if (head == null) {
            return null;
        }

        LinkedListNode curr = head;
        while (curr.data < num && curr.next != null) {
            curr = curr.next;
        }

        if (curr.next == null && num > curr.data) {
            // make tail
            curr.next = new LinkedListNode(num);
            curr.next.prev = curr;
        } else {
            LinkedListNode newNode = new LinkedListNode(num);
            if (curr.prev == null) {
                // make head
                newNode.next = curr;
                curr.prev = newNode;
                head = newNode;
            } else {
                // attach infront
                curr.prev.next = newNode;
                newNode.prev = curr.prev;
                newNode.next = curr;
                curr.prev = newNode;
            }
        }
        return head;
    }

    public static int getRank(LinkedListNode head, int element) {
        int i = 0;
        LinkedListNode cur = head;
        while (cur.next != null) {
            if (cur.data == element) {
                return i;
            }
            cur = cur.next;
            ++i;
        }
        return i;
    }


    public static void main(String[] args) {
        LinkedListNode stream = null;

        int size = 100;
        int[] list = AssortedMethods.randomArray(size, -100, 100);
        for (int i = 0; i < list.length; i++) {
            if (stream == null) {
                stream = new LinkedListNode(list[i]);
            } else {
                stream = track(stream, list[i]);
            }
        }

        int[] tracker = new int[size];
        for (int i = 0; i < list.length; i++) {
            int v = list[i];
            int rank1 = getRank(stream, list[i]);
            tracker[rank1] = v;
        }

        for (int i = 0; i < tracker.length - 1; i++) {
            if (tracker[i] != 0 && tracker[i + 1] != 0) {
                if (tracker[i] > tracker[i + 1]) {
                    System.out.println("ERROR at " + i);
                }
            }
        }
        System.out.println(stream.printForward());
        System.out.println("Array: " + AssortedMethods.arrayToString(list));
        System.out.println("Ranks: " + AssortedMethods.arrayToString(tracker));
    }
}
