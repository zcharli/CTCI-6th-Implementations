package chapter4;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

import lib.AssortedMethods;
import lib.TreeNode;

public class FourThreeListOfDepth {

	public FourThreeListOfDepth() {
		// TODO Auto-generated constructor stub
	}

	public static ArrayList<LinkedList<TreeNode>> levelOrderTraversal(TreeNode root) {
		ArrayList<LinkedList<TreeNode>> ret = new ArrayList<LinkedList<TreeNode>>();
		int curDepth = 0;
		int elementsToNextDepth = 1;
		int nextElementsToDepthIncrease = 0;
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);

		LinkedList<TreeNode> levelListRef = new LinkedList<>();
		while (!q.isEmpty()) {
			TreeNode currNode = q.poll();

			if (currNode.left != null) {
				nextElementsToDepthIncrease++;
				q.add(currNode.left);
			}
			
			if (currNode.right != null) {
				nextElementsToDepthIncrease++;
				q.add(currNode.right);
			}
			
			levelListRef.add(currNode);
			if (--elementsToNextDepth == 0) {
				curDepth++;
				elementsToNextDepth = nextElementsToDepthIncrease;
				nextElementsToDepthIncrease = 0;
				ret.add(levelListRef);
				levelListRef = new LinkedList<>();
			}
		}
		return ret;
	}

	public static void printResult(ArrayList<LinkedList<TreeNode>> result) {
		int depth = 0;
		for (LinkedList<TreeNode> entry : result) {
			Iterator<TreeNode> i = entry.listIterator();
			System.out.print("Link list at depth " + depth + ":");
			while (i.hasNext()) {
				System.out.print(" " + ((TreeNode) i.next()).data);
			}
			System.out.println();
			depth++;
		}
	}

	public static void main(String[] args) {
		int[] nodes_flattened = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
		TreeNode root = AssortedMethods.createTreeFromArray(nodes_flattened);
		ArrayList<LinkedList<TreeNode>> list = levelOrderTraversal(root);
		printResult(list);
	}
}
