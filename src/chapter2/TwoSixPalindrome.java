package chapter2;

import lib.LinkedListNode;

public class TwoSixPalindrome {

	public TwoSixPalindrome() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean isPalindrome(LinkedListNode l) {
		if(l == null)
			return false;
		LinkedListNode head = l;
		
		LinkedListNode tail = l;
		int len = 1;
		while(tail.next != null) {
			len += 1;
			tail = tail.next;
		}
		
		if(tail == head) {
			return true;
		}
		if(len == 2) {
			return head.data == tail.data;
		}
		
		int steps = len / 2;
		while(steps-- > 0) {
			if(head.data != tail.data) {
				return false;
			}
			head = head.next;
			tail = tail.prev;
		}
				
		return true;
	}
	
	public static void main(String[] args) {
		int max = 11;
		for (int length = 1; length < max; length++) {
			LinkedListNode[] nodes = new LinkedListNode[length];
			for (int i = 0; i < length; i++) {
				nodes[i] = new LinkedListNode(i >= length / 2 ? length - i - 1 : i, null, null);
			}
			
			for (int i = 0; i < length; i++) {
				if (i < length - 1) {
					nodes[i].setNext(nodes[i + 1]);
				}
				if (i > 0) {
					nodes[i].setPrevious(nodes[i - 1]);
				}
			}
			for (int i = -1; i < length; i++) {
				if (i >= 0) {
					nodes[i].data++; // Ruin palindrome
				}
				
				LinkedListNode head = nodes[0];
				System.out.println(head.printForward());
				boolean resultA = isPalindrome(head);
				boolean resultB = isPalindrome(head);
				boolean resultC = isPalindrome(head);
				System.out.println("A: " + resultA);
//				System.out.println("B: " + resultB);
//				System.out.println("C: " + resultC);
				if (resultA != resultB || resultB != resultC) {
					System.out.println("ERROR");
					length = max;
					break;
				}
				if (i >= 0) {
					nodes[i].data--;
				}
			}
		}
	}
}
