package chapter4;

import java.util.LinkedList;
import java.util.Queue;

import lib.BTreePrinter;
import lib.TreeNode;

public class FourFourCheckBalanced {

	public FourFourCheckBalanced() {
		// TODO Auto-generated constructor stub
	}
	
	@SuppressWarnings("unused")
	public static boolean isTreeBalanced(TreeNode root) {
		// Will BFS traverse this tree holding an expected nodes at the fringe.
		// When the expected is not met, loop is given one more chance to finish.
		// If it does not finish, then we know tree is unbalanced
		int curDepth = 0;
		int elementsTilNextDepth = 1;
		int nextDepthElements = 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		boolean oneMoreLevel = false;
		int expected = 2;
		while(!q.isEmpty()) {
			
			TreeNode currNode = q.poll();
			
			if(currNode.right != null) {
				q.add(currNode.right);
				nextDepthElements++;
			}
			if(currNode.left != null) {
				q.add(currNode.left);
				nextDepthElements++;
			}

			if(--elementsTilNextDepth <= 0 && !q.isEmpty()) {
				// Moved to a new depth
				elementsTilNextDepth = nextDepthElements;
				if(nextDepthElements != expected) {
					// No more depth difference by one
					if(oneMoreLevel) {
						return false;
					}
					oneMoreLevel = true;
				}
				expected *= 2;
				nextDepthElements = 0;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args) {
		int[] array = {0, 1, 2, 3, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);

		BTreePrinter.printNode(root);
		System.out.println("Is balanced? " + isTreeBalanced(root));
		
		root.insertInOrder(4); // Add 4 to make it unbalanced
		BTreePrinter.printNode(root);
		System.out.println("Is balanced? " + isTreeBalanced(root));
	}
}
