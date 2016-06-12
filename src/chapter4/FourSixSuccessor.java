package chapter4;

import java.util.Stack;

import lib.TreeNode;

public class FourSixSuccessor {

	public FourSixSuccessor() {
		// TODO Auto-generated constructor stub
	}

	public static TreeNode findSuccessor(TreeNode root, TreeNode findMySuccessor) {
		
		boolean preFound = false;
		Stack<TreeNode> stack = new Stack<>();
		TreeNode node = root;
        
        //first node to be visited will be the left one
        while (node != null) {
            stack.push(node);
            node = node.left;
        }
         
        // traverse the tree
        while (stack.size() > 0) {
           
            // visit the top node
            node = stack.pop();
            if(preFound) {
            	return node;
            }
            if(node == findMySuccessor) {
            	preFound = true;
            }
            if (node.right != null) {
                node = node.right;
                 
                // the next node to be visited is the leftmost
                while (node != null) {
                    stack.push(node);
                    node = node.left;
                }
            }
        }
		return null;
	}
	
	public static void main(String[] args) {
		int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
		TreeNode root = TreeNode.createMinimalBST(array);
		root.print();
		for (int i = 0; i < array.length; i++) {
			TreeNode node = root.find(array[i]);
			TreeNode next = findSuccessor(root, node);
			if (next != null) {
				System.out.println(node.data + "->" + next.data);
			} else {
				System.out.println(node.data + "->" + null);
			}
		}
	}
}
