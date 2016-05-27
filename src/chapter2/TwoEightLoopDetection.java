package chapter2;

import java.util.HashSet;

import lib.LinkedListNode;

public class TwoEightLoopDetection {

	public TwoEightLoopDetection() {
		// TODO Auto-generated constructor stub
	}

	public static LinkedListNode detectLoop(LinkedListNode l) {

		HashSet<LinkedListNode> hs = new HashSet<>();

		LinkedListNode currNode = l;

		while (l != null) {
			if (hs.contains(l)) {
				return l;
			}

			hs.add(l);
			l = l.next;
		}

		return null;
	}

	public static void main(String[] args) {
		int list_length = 100;
		int k = 10;

		// Create linked list
		LinkedListNode[] nodes = new LinkedListNode[list_length];
		for (int i = 0; i < list_length; i++) {
			nodes[i] = new LinkedListNode(i, null, i > 0 ? nodes[i - 1] : null);
		}

		// Create loop;
		nodes[list_length - 1].next = nodes[list_length - k];

		LinkedListNode loop = detectLoop(nodes[0]);
		if (loop == null) {
			System.out.println("No Cycle.");
		} else {
			System.out.println(loop.data);
		}
	}
}
