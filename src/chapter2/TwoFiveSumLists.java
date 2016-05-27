package chapter2;

import lib.LinkedListNode;

public class TwoFiveSumLists {

	public TwoFiveSumLists() {
		// TODO Auto-generated constructor stub
	}
	
	public static LinkedListNode sumList(LinkedListNode a, LinkedListNode b) {
		int numA = linkedListToInt(a);
		int numB = linkedListToInt(b);
		
		int sum = numA + numB;
		char[] sumChar = (new Integer(sum)).toString().toCharArray();
		if(sumChar.length == 0) {
			return null;
		}
		LinkedListNode head = new LinkedListNode(Integer.parseInt(""+sumChar[0]), null, null);
		LinkedListNode currNode = head;
		for (int i = 1; i < sumChar.length; i++) {
			currNode.next = new LinkedListNode(Integer.parseInt(""+sumChar[i]), null, null);
			currNode.next.setPrevious(currNode);
			currNode = currNode.next;
		}

		return head;
	}
	
	public static int linkedListToInt(LinkedListNode node) {
		int value = 0;
		if (node.next != null) {
			value = 10 * linkedListToInt(node.next);
		}
		return value + node.data;
	}
	
	public static void main(String[] args) {
		LinkedListNode lA1 = new LinkedListNode(9, null, null);
		LinkedListNode lA2 = new LinkedListNode(9, null, lA1);
		LinkedListNode lA3 = new LinkedListNode(9, null, lA2);
		
		LinkedListNode lB1 = new LinkedListNode(1, null, null);
		LinkedListNode lB2 = new LinkedListNode(0, null, lB1);
		LinkedListNode lB3 = new LinkedListNode(0, null, lB2);	
		
		LinkedListNode list3 = sumList(lA1, lB1);
		
		System.out.println("  " + lA1.printForward());		
		System.out.println("+ " + lB1.printForward());			
		System.out.println("= " + list3.printForward());	
		
		int l1 = linkedListToInt(lA1);
		int l2 = linkedListToInt(lB1);
		int l3 = linkedListToInt(list3);
		
		System.out.print(l1 + " + " + l2 + " = " + l3 + "\n");
		System.out.print(l1 + " + " + l2 + " = " + (l1 + l2));		
	}
}
