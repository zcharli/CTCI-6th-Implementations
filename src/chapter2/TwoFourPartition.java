package chapter2;

import lib.LinkedListNode;

public class TwoFourPartition {

	public TwoFourPartition() {
		// TODO Auto-generated constructor stub
	}

	public static LinkedListNode partition(LinkedListNode l, int x) {
//		LinkedListNode currNode = l;
//		LinkedListNode tempNode = null;
//		while(currNode != null) {
//			
//			if(currNode.data > x) {
//				// bubble node up 
//				tempNode = currNode;
//				while(tempNode.next != null) {
//					if(tempNode.next != null && tempNode.next.data < x) {
//						tempNode = tempNode.next;
//					} else {
//						break;
//					}
//				}
//				// back is greater than x
//				LinkedListNode front = tempNode.next;
//				
//				if(currNode.prev == null) {
//					front.prev = null;
//					front.next = currNode.next;
//					currNode.next.prev = front;
//				} else {
//					LinkedListNode forwardSwitch = front.next;
//					LinkedListNode backwardSwitch = front.prev;
//					front.prev = currNode.prev;
//					front.next = currNode.next;
//					currNode.prev.next = front;
//					currNode.next.prev = front;
//					currNode.next = forwardSwitch;
//					currNode.prev = backwardSwitch;
//					forwardSwitch.prev = currNode;
//					backwardSwitch.next = currNode;
//				}
//				currNode = front;
//			} else if (currNode.data < x) {
//				tempNode = currNode;
//				while(tempNode.prev != null) {
//					if(tempNode.prev != null && tempNode.prev.data > x) {
//						tempNode = tempNode.prev;
//					} else {
//						break;
//					}
//				}
//				// back is less than x
//				LinkedListNode back = tempNode.prev; 
//				if(back.next == null) {
//					
//				} else {
//					LinkedListNode backNext = back.next;
//					LinkedListNode backPrev = back.prev;
//					
//					back.next = currNode.next;
//					back.prev = currNode.prev;
//					back.next.prev = back;
//					back.prev.next = back;
//					
//					currNode.next = backNext;
//					currNode.prev = backPrev;
//					currNode.next.prev = currNode;
//					currNode.prev.next = currNode;
//					currNode = back;
//				}
//			}
//			currNode = currNode.next;
//		}
//		return l;
		
		LinkedListNode head = l;
		
		LinkedListNode currNode = l;
		
		while(currNode != null) {
			
			if(currNode.data < x && currNode != head) {
				LinkedListNode continueOff = currNode.next;
				// move to the head
				if(currNode.next != null) {
					currNode.next.prev = currNode.prev;
				} else {
					currNode.prev.next = null;
				}
				
				if(currNode.prev != null) {
					currNode.prev.next = currNode.next;
				} else {
					// Can only happen if currNode is the first node. We can leave it as it is
				}
				
				currNode.next = head;
				head.prev = currNode;
				currNode.prev = null;
				head = currNode;
				currNode = continueOff;
			} else {
				currNode = currNode.next;
			}
		}
		
		return head;
	}
	
	public static void main(String[] args) {
		int length = 20;
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
		
		LinkedListNode head = nodes[0];
		System.out.println(head.printForward());
		
		LinkedListNode h = partition(head, 5);
		System.out.println(h.printForward());
	}
}
