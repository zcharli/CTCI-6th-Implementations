package chapter2;

import lib.AssortedMethods;
import lib.LinkedListNode;

public class TwoTwoReturnkthLast {

	public TwoTwoReturnkthLast() {
		// TODO Auto-generated constructor stub
	}
	
	public static LinkedListNode getKthLast(LinkedListNode l, int i) {
		
		LinkedListNode currNode = l;
		LinkedListNode runnerNode = l;
		
		// Run the runner first
		
		while(i-- > 0) {
			if(runnerNode == null) {
				System.out.println("Less than " + i + " elements, thus cannot get "+i+"-th last");
				return null;
			}
			runnerNode = runnerNode.next;
		}
		
		while(runnerNode != null) {
			runnerNode = runnerNode.next;
			currNode = currNode.next;
		}
		
		return currNode;
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3, 4, 5, 6};
		LinkedListNode head = AssortedMethods.createLinkedListFromArray(array);
		for (int i = 0; i <= array.length + 1; i++) {
			LinkedListNode n = getKthLast(head, i);
			if(n != null) {
				System.out.println(n.data);
			}
		}
	}
}
