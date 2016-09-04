package random;

import java.util.*;

/**
 * Created by czl on 04/09/16.
 *      there is n+1 loading docks. a permutation of boxes 1->n is
 *      placed on the first n. there is a fork that can move one box
 *      to an empty location at a time. Give an algorithm to sort then
 *      boxes with minimum number of moves. Follow up: minimum distance
 */
public class LoadingBlocks {

    public static class Box implements Comparable<Integer> {
        public int size;
        public Box(int n) {
            size = n;
        }

        @Override
        public int compareTo(Integer other) {
            if (other == size) {
                return 0;
            }
            return other > size ? -1 : 1;
        }
    }

    public static class Node<T extends Comparable> {
        public T data;
        public Node left = null;
        public Node right = null;
        public int index;
        public Node(T e) {
            data = e;
        }

        public void setIndex(int n) {
            index = n;
        }
    }

    public static class BinaryTree<T extends Comparable> {

        public Node<T> root = null;

        public void addNode(T data, int index) {
            if (root == null) {
                root = new Node<T>(data);
                root.setIndex(index);
            } else {
                Node n = _addNode(data, root);
                n.setIndex(index);
            }
        }

        private Node _addNode(T data, Node curNode) {
            if (data.compareTo(curNode.data) < 0) {
                if (curNode.left == null) {
                    curNode.left = new Node(data);
                    return curNode.left;
                } else {
                    return _addNode(data, curNode.left);
                }
            } else if (data.compareTo(curNode.data) > 0) {
                if(curNode.right == null) {
                    curNode.right = new Node(data);
                    return curNode.right;
                } else {
                    return _addNode(data, curNode.right);
                }
            } else {
                // duplicates
                return null;
            }
        }

        public List<Node> getOrdered() {
            Stack<Node> stack = new Stack<>();
            List<Node> elements = new ArrayList<>();
            if (root == null) {
                return elements;
            }
            _getOrdered(root, elements);
            return elements;
        }

        public void _getOrdered(Node currNode, List<Node> elements) {
            if (currNode.left != null)  {
                _getOrdered(currNode.left, elements);
            }
            elements.add(currNode);
            if (currNode.right != null) {
                _getOrdered(currNode.right, elements);
            }
        }
    }

    public static int[] orderLoadingBlocks(int[] boxes, int[] loadingDocks) {
        BinaryTree<Integer> binTree = new BinaryTree<>();

        for (int i = 0; i < boxes.length; i++) {
            binTree.addNode(boxes[i], i);
            loadingDocks[i] = boxes[i];
        }

        List<Node> order = binTree.getOrdered();
        for (int i = 0; i < order.size(); i++) {
            Node curNode = order.get(i);
            System.out.println("Moved " + Math.abs(curNode.index - i) + " steps.");
            loadingDocks[i] = (Integer)curNode.data;
        }

        return loadingDocks;
    }

    public static void main(String[] args) {
        int[] permBox = {10,2,8,5,7,6,3,9};

        int[] loadingDocks = new int[8];

        int[] ret = orderLoadingBlocks(permBox, loadingDocks);

        for (int i = 0; i < ret.length; i++) {
            System.out.print(ret[i] + ", ");
        }

    }
}
