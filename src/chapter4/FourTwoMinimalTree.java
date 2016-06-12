package chapter4;

import lib.TreeNode;

public class FourTwoMinimalTree {

	public FourTwoMinimalTree() {
		// TODO Auto-generated constructor stub
	}

	public static TreeNode createBinarySearchTree(int[] elements) {
		if (elements == null || elements.length == 0) {
			return null;
		}

		if (elements.length == 1) {
			return new TreeNode(elements[0]);
		}

		int idx = (elements.length - 1) / 2;
		TreeNode root = new TreeNode(elements[idx]);
		// Going to build the left and right subtree of the root separately.
		root.left = buildTree(elements, root, 0, idx - 1);
		root.right = buildTree(elements, root, idx + 1, elements.length - 1);
		return root;
	}
	
	public static TreeNode buildTree(int[] elements, TreeNode parent, int start, int end) {
		if(start > end) {
			return null;
		}
		int idx = (start + end) / 2;
		TreeNode curNode = new TreeNode(elements[idx]);
		curNode.parent = parent;
		curNode.left = buildTree(elements, curNode, start, idx - 1);
		curNode.right = buildTree(elements, curNode, idx + 1, end);
		return curNode;
	}

	public static void main(String[] args) {
		int[] array = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
 
		TreeNode root = createBinarySearchTree(array);
		System.out.println("Root? " + root.data);
		System.out.println("Created BST? " + root.isBST());
		System.out.println("Height: " + root.height());
	}
}
