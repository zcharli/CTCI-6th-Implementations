package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import lib.AssortedMethods;
import lib.TreeNode;

public class FourTenCheckSubtree {

	public FourTenCheckSubtree() {
		// TODO Auto-generated constructor stub
	}
	
	public static boolean checkSubtree(TreeNode root, TreeNode subtree) {
		ArrayList<Integer> l = inorder(subtree);
		Stack<TreeNode> s = new Stack<>();
		
		TreeNode currNode = root;
		
		while(currNode != null) {
			s.push(currNode);
			currNode = currNode.left;
		}
		
		while(!s.isEmpty()) {
			currNode = s.pop();
			if(currNode.data == subtree.data) {
				ArrayList<Integer> l2 = inorder(currNode);
				for (int i = 0; i < l2.size(); i++) {
					if(l2.get(i) != l.get(i)) {
						return false;
					}
				}
				return true;
			}
			
			if(currNode.right != null) {
				currNode = currNode.right;
				while(currNode != null) {
					s.push(currNode);
					currNode = currNode.left;
				}
			}
		}
		return false;
	}
	
	public static ArrayList<Integer> inorder(TreeNode root) {
		ArrayList<Integer> l = new ArrayList<>();
		Stack<TreeNode> s = new Stack<>();
		
		TreeNode currNode = root;
		while(currNode != null) {
			s.add(currNode);
			currNode = currNode.left;
		}
		
		while(!s.isEmpty()) {
			currNode = s.pop();
			l.add(currNode.data);
			if(currNode.right != null) {
				currNode = currNode.right;
				while(currNode != null) {
					s.add(currNode);
					currNode = currNode.left;
				}
			}
		}
		return l;
	}
	
	
	public static void main(String[] args) {
		// t2 is a subtree of t1
		int[] array1 = {1, 2, 1, 3, 1, 1, 5};
		int[] array2 = {2, 3, 1};
		
		TreeNode t1 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t2 = AssortedMethods.createTreeFromArray(array2);

		if (checkSubtree(t1, t2)) {
			System.out.println("t2 is a subtree of t1");
		} else {
			System.out.println("t2 is not a subtree of t1");
		}

		// t4 is not a subtree of t3
		int[] array3 = {1, 2, 3};
		TreeNode t3 = AssortedMethods.createTreeFromArray(array1);
		TreeNode t4 = AssortedMethods.createTreeFromArray(array3);

		if (checkSubtree(t3, t4)) {
			System.out.println("t4 is a subtree of t3");
		} else {
			System.out.println("t4 is not a subtree of t3");
		}
	}
}
