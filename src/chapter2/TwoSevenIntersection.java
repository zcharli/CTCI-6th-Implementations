package chapter2;

import lib.AssortedMethods;
import lib.LinkedListNode;

public class TwoSevenIntersection {

	public TwoSevenIntersection() {
		// TODO Auto-generated constructor stub
	}
	
	public static LinkedListNode findIntersection(LinkedListNode l, LinkedListNode r) {
		
		LinkedListNode currNodeL = l;
		// O(N^2)
		while(currNodeL != null) {
			LinkedListNode currNodeR = r;
			while(currNodeR != null) {
				if(currNodeR == currNodeL) {
					return currNodeR;
				}
				currNodeR = currNodeR.next;
			}
			currNodeL = currNodeL.next;
		}
		
		return null;
	}
	
	public static void main(String[] args) {
		/* Create linked list */
		int[] vals = {-1, -2, 0, 1, 2, 3, 4, 5, 6, 7, 8};
		LinkedListNode list1 = AssortedMethods.createLinkedListFromArray(vals);
		
		int[] vals2 = {12, 14, 15};
		LinkedListNode list2 = AssortedMethods.createLinkedListFromArray(vals2);
		
		list2.next.next = list1.next.next.next.next;
		
		System.out.println(list1.printForward());
		System.out.println(list2.printForward());
		
		
		LinkedListNode intersection = findIntersection(list1, list2);
		
		System.out.println(intersection.printForward());
	}
}
