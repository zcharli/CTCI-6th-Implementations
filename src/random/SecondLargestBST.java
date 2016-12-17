package random;

import lib.AssortedMethods;
import lib.TreeNode;
import sun.reflect.generics.tree.Tree;

/**
 * Created by cli on 9/5/2016.
 */
public class SecondLargestBST {

    public static TreeNode secondLargest(TreeNode root) {

        return _secondLargest(root, false);
    }

    public static TreeNode _secondLargest(TreeNode root, boolean foundLargest) {

        if (root.right == null) {
            if (foundLargest) {

                return root;
            } else {
                foundLargest = true;
            }
            if (root.left != null) {
                TreeNode left = _secondLargest(root.left, foundLargest);
                if (left != null) {
                    return left;
                } else {
                    return root;
                }
            } else {
                // the second largest is this parent
                return null;
            }
        } else {
            TreeNode largest = _secondLargest(root.right, foundLargest);
            if (largest == null && !foundLargest) {
                return root;
            } else if(largest == null && foundLargest) {
                return largest;
            } else {
                return largest;
            }
        }
    }

    public static TreeNode findLargest(TreeNode root) {
        if (root.right == null) {
            return root;
        } else {
            return findLargest(root.right);
        }
    }

    public static void printTree(TreeNode root) {
        if (root.left != null) {
            printTree(root.left);
        }
        System.out.print(root.data + ",");
        if (root.right != null) {
            printTree(root.right);
        }
    }

    public static int findLargestCake(TreeNode rootNode) {
        TreeNode current = rootNode;
        while (current.right != null) {
            current = current.right;
        }
        return current.data;
    }

    public static int findSecondLargest(TreeNode rootNode) {
        if (rootNode.left == null && rootNode.right == null) {
            throw new IllegalArgumentException("Tree must have at least 2 nodes");
        }

        TreeNode current = rootNode;

        while (true) {
            // case: current is largest and has a left subtree
            // 2nd largest is the largest in that subtree
            if (current.left != null && current.right == null) {
                return findLargestCake(current.left);
            }

            // case: current is parent of largest, and
            // largest has no children, so
            // current is 2nd largest
            if (current.right != null &&
                    current.right.left == null &&
                    current.right.right == null) {
                return current.data;
            }

            current = current.right;
        }
    }

    public static void main(String[] args) {
        TreeNode root = AssortedMethods.randomBST(10, 0, 100);
        System.out.println();
        printTree(root);
        TreeNode second = secondLargest(root);
        System.out.println();
        System.out.println(findSecondLargest(root));
        System.out.println(second == null ? "null" : second.data);
    }
}
