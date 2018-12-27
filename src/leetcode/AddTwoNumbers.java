package leetcode;

import java.util.List;

/**
 * @author cli
 * @since 1/13/18
 */
public class AddTwoNumbers
{
	public static class ListNode
	{
		int val;
		ListNode next;
		
		ListNode(int x)
		{
			val = x;
		}
	}
	
	public static ListNode addTwoNumbers(ListNode l1, ListNode l2)
	{
		ListNode curNode1 = l1;
		ListNode curNode2 = l2;
		
		boolean shouldCarryOver = false;
		
		ListNode answer = null;
		ListNode currentAnswerStack = null;
		while (curNode1 != null || curNode2 != null)
		{
			int node1 = 0;
			if (curNode1 != null)
			{
				node1 = curNode1.val;
				curNode1 = curNode1.next;
			}
			
			int node2 = 0;
			if (curNode2 != null)
			{
				node2 = curNode2.val;
				curNode2 = curNode2.next;
			}
			
			int stackAnswer = node1 + node2;
			
			if (shouldCarryOver) // apply the last carry over.
			{
				stackAnswer += 1;
			}
			
			shouldCarryOver = stackAnswer >= 10;
			if (shouldCarryOver)
			{
				stackAnswer -= 10;
			}
			
			ListNode stackAnswerNode = new ListNode(stackAnswer);
			if (answer == null)
			{
				answer = stackAnswerNode;
				currentAnswerStack = answer;
			}
			else
			{
				currentAnswerStack.next = stackAnswerNode;
				currentAnswerStack = stackAnswerNode;
			}
		}
		
		if (shouldCarryOver)
		{
			currentAnswerStack.next = new ListNode(1);
		}
		
		return answer;
	}
	
	public static void main(String[] args)
	{
		// Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
		// Output: 7 -> 0 -> 8
		// Explanation: 342 + 465 = 807.
		
		ListNode two = new ListNode(2);
		ListNode four = new ListNode(4);
		ListNode three = new ListNode(3);
		two.next = four;
		four.next = three;
		
		ListNode five = new ListNode(5);
		ListNode six = new ListNode(6);
		ListNode four2 = new ListNode(4);
		five.next = six;
		six.next = four2;
		
		ListNode answer = addTwoNumbers(two, five);
		ListNode curNode = answer;
		while (curNode != null)
		{
			System.out.print(curNode.val);
			curNode = curNode.next;
		}
	}
}
