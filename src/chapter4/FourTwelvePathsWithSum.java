package chapter4;

import java.util.Stack;

import lib.TreeNode;

public class FourTwelvePathsWithSum {

	public FourTwelvePathsWithSum() {
		// TODO Auto-generated constructor stub
		
		
		// WRONG
	}

	public static int countPathSums(TreeNode root, int sum) {
		
		int totalPaths = 0;
		
		Stack<TreeNode> s = new Stack<>();
		TreeNode currNode = root;
		while(currNode != null) {
			s.push(currNode);
			currNode = currNode.left;
		}
		
		while(!s.isEmpty()) {
			currNode = s.pop();
			// Try to get sum with this node
			if(countPath(currNode, sum, 0) > 0) {
				totalPaths++;
			}
			
			if(currNode.right != null) {
				currNode = currNode.right;
				while(currNode != null) {
					s.add(currNode);
					currNode = currNode.left;
				}
			}
		}
		
		
		return totalPaths;
	}
	
	public static int countPath(TreeNode node, int sum, int currentSum) {
		if(node == null) {
			return 0;
		}
		
		if(currentSum > sum) {
			return 0;
		}
		
		if(currentSum == sum) {
			return 1;
		}
		
		int leftTree = countPath(node.left, sum, node.data + currentSum);
		int rightTree = countPath(node.right, sum, node.data + currentSum);
		
		return leftTree + rightTree;
	}
	
	public static void main(String [] args) {
		
		TreeNode root = new TreeNode(5);
		root.left = new TreeNode(3);		
		root.right = new TreeNode(1);
		root.left.left = new TreeNode(-8);
		root.left.right = new TreeNode(8);
		root.right.left = new TreeNode(2);
		root.right.right = new TreeNode(6);	
		System.out.println(countPathSums(root, 0));
		
		/*TreeNode root = new TreeNode(-7);
		root.left = new TreeNode(-7);
		root.left.right = new TreeNode(1);
		root.left.right.left = new TreeNode(2);
		root.right = new TreeNode(7);
		root.right.left = new TreeNode(3);
		root.right.right = new TreeNode(20);
		root.right.right.left = new TreeNode(0);
		root.right.right.left.left = new TreeNode(-3);
		root.right.right.left.left.right = new TreeNode(2);
		root.right.right.left.left.right.left = new TreeNode(1);
		System.out.println(countPathsWithSum(root, -14));
		
		TreeNode root = new TreeNode(0);
		root.left = new TreeNode(0);
		root.right = new TreeNode(0);
		root.right.left = new TreeNode(0);
		root.right.left.right = new TreeNode(0);
		root.right.right = new TreeNode(0);
		System.out.println(countPathSums(root, 0));
		System.out.println(countPathSums(root, 4));
		
		boolean isWorking = true;
		while (isWorking) {
			int min = -20;
			int max = 20;
			int size = 20;
			TreeNode root = AssortedMethods.randomBST(size, min, max);
		
			for (int targetSum = Math.min(-1, min * size - 10); targetSum <= Math.max(100, max * size + 10); targetSum++) {
				int answerA = QuestionA.countPathsWithSum(root, targetSum);
				int answerB = QuestionB.countPathsWithSum(root, targetSum);
				if (answerA > 0 || answerB > 0) {
					System.out.println(targetSum + ": " + answerA + ", " + answerB + " | " + (answerA == answerB));
				}
				if (answerA != answerB) {
					isWorking = false;
					break;
				}
			}
		}
		
		*/
	}
}
