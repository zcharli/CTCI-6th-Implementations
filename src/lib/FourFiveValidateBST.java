package lib;

import java.util.ArrayList;
import java.util.Stack;

public class FourFiveValidateBST {
	
	public FourFiveValidateBST() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean validateBST(TreeNode root) {
		// In order traverse, the result should be sorted...
		if(root == null) {
			return false;
		}
		ArrayList<Integer> a_tester = new ArrayList<>();
		
		Stack<TreeNode> s = new Stack<>();
		
		s.add(root);
		TreeNode currNode = root;
		
		while(!s.empty()) {
			
			if(currNode != null) {
				s.add(currNode.left);
				currNode = currNode.left;
			} else {
				currNode = s.pop();
				if(currNode != null) {
					a_tester.add(currNode.data);
					currNode = currNode.right;
				}
			}
		}
		
		int i;
		for (i = 0; i < a_tester.size() - 1 && a_tester.get(i) < a_tester.get(i+1); i++) {}
		
		return i == a_tester.size() - 1;
	}
	
	/* Create a tree that may or may not be a BST */
	public static TreeNode createTestTree() {
		/* Create a random BST */
		TreeNode head = AssortedMethods.randomBST(10, -10, 10); 
		
		/* Insert an element into the BST and potentially ruin the BST property */
		TreeNode node = head;
		do {
			int n = AssortedMethods.randomIntInRange(-10, 10);
			int rand = AssortedMethods.randomIntInRange(0, 5);
			if (rand == 0) {
				node.data = n;
			} else if (rand == 1) {
				node = node.left;
			} else if (rand == 2) {
				node = node.right;
			} else if (rand == 3 || rand == 4) {
				break;
			}
		} while (node != null);	
		
		return head;
	}
	
	public static void main(String[] args) {
		/* Simple test -- create one */
		int[] array = {Integer.MIN_VALUE, 3, 5, 6, 10, 13, 15, Integer.MAX_VALUE};
		TreeNode node = TreeNode.createMinimalBST(array);
		//node.left.data = 6; // "ruin" the BST property by changing one of the elements
		node.print();
		boolean isBst = validateBST(node);
		System.out.println(isBst);
		
		/* More elaborate test -- creates 100 trees (some BST, some not) and compares the outputs of various methods. */
		/*for (int i = 0; i < 100; i++) {
			TreeNode head = createTestTree();
			
			// Compare results 
			boolean isBst1 = checkBST(head);
			boolean isBst2 = checkBSTAlternate(head);
			
			if (isBst1 != isBst2) {
				System.out.println("*********************** ERROR *******************");
				head.print();
				break;
			} else {
				System.out.println(isBst1 + " | " + isBst2);
				head.print();
			}
		}*/
	}
}
