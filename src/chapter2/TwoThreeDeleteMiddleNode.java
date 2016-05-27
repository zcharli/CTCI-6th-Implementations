package chapter2;

import lib.AssortedMethods;
import lib.LinkedListNode;

public class TwoThreeDeleteMiddleNode {

	public TwoThreeDeleteMiddleNode() {
		// TODO Auto-generated constructor stub
	}

	public static void deleteMiddle(LinkedListNode l) {
		if(l == null || l.next == null || l.prev == null) // Not middle condition
			return;
		l.prev.next = l.next;
		l.next.prev = l.prev;
	}
	
	public static void main(String[] args) {
		LinkedListNode head = AssortedMethods.randomLinkedList(10, 0, 10);
		System.out.println(head.printForward());
		deleteMiddle(head.next.next.next.next); // delete node 4
		System.out.println(head.printForward());
	}
}
