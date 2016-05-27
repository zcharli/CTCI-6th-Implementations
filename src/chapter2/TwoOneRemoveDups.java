package chapter2;

import lib.LinkedListNode;

public class TwoOneRemoveDups {

	public TwoOneRemoveDups() {
		// TODO Auto-generated constructor stub
	}
	
	public static void removeDups(LinkedListNode head) {
		
		LinkedListNode currNode = head;
		
		while(currNode != null) {
			int data = currNode.data;
			
			LinkedListNode prevNode = currNode;
			LinkedListNode nextNode = currNode.next;
			
			while(nextNode != null) {
 				if(nextNode.data == data) {
					prevNode.next = nextNode.next;
				} else {
					prevNode = nextNode;
				}
				
				nextNode = prevNode.next;
			}
			
			currNode = currNode.next;
		}
		
	}
	
	public static void main(String[] args) {	
		LinkedListNode first = new LinkedListNode(0, null, null); //AssortedMethods.randomLinkedList(1000, 0, 2);
		LinkedListNode head = first;
		LinkedListNode second = first;
		for (int i = 1; i < 8; i++) {
			second = new LinkedListNode(i % 2, null, null);
			first.setNext(second);
			second.setPrevious(first);
			first = second;
		}
		System.out.println(head.printForward());
		removeDups(head);
		System.out.println(head.printForward());
	}
}
