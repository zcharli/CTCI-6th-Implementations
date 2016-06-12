package chapter4;

import java.util.ArrayList;

import lib.TreeNode;

public class FourEightFirstCommonAncestor {

	public FourEightFirstCommonAncestor() {
		// TODO Auto-generated constructor stub
	}
	
	public static TreeNode firstCommonAncestor(TreeNode root, TreeNode n1, TreeNode n2) {
		if(n1 == null || n2 == null || root == null) {
			return null;
		}

		if (root == n1 || root == n2) {
			return root.parent;
		}
		
		TreeNode parent1 = firstCommonAncestor(root.left, n1, n2);
		TreeNode parent2 = firstCommonAncestor(root.right, n1, n2);
		
		if(parent1 != null && parent2 != null) {
			return root;
		}
		
		return parent1 == null ? parent2 : parent1;
	}

	public static String resultToString(String s, TreeNode x, TreeNode y, TreeNode anc) {
		s += ": ";
		s += (x == null ? "null" : x.data);
		s += " & ";
		s += (y == null ? "null" : y.data);
		s += " -> ";
		s += (anc == null ? "null" : anc.data);
		return s;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		
		ArrayList<TreeNode> nodes = new ArrayList<TreeNode>();
		for (int a : array) {
			nodes.add(root.find(a));
		}
		nodes.add(new TreeNode(11));
		root.print();
		for (TreeNode x : nodes) {
			for (TreeNode y : nodes) {
				TreeNode r5 = firstCommonAncestor(root, x, y);
				
				String s5 = resultToString("D", x, y, r5);
				
				System.out.println("SUCCESS: " + s5);
			}
		}
	}
}
