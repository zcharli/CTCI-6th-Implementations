package chapter4;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import lib.Permutations;
import lib.TreeNode;

public class FourNineBSTSeq {

	public FourNineBSTSeq() {
		// TODO Finish this
	}
	
	public static ArrayList<LinkedList<Integer>> findSeq(TreeNode root) {
		ArrayList<LinkedList<Integer>> seqs = new ArrayList<LinkedList<Integer>>();
		Queue<TreeNode> q = new LinkedList<>();
		LinkedList<Integer> l = new LinkedList<>();
		l.add(root.data);
		seqs.add(l);
		int currDepth = 0;
		int elementsTillNextDepth = 1;
		int nextDepthElements = 0;
		q.add(root);
		ArrayList<Integer> collected = new ArrayList<>();
		while(!q.isEmpty()) {
			TreeNode currNode = q.poll();
			
			if(currNode.left != null) {
				q.add(currNode.left);
				nextDepthElements++;
				collected.add(currNode.left.data);
			}
			
			if(currNode.right != null) {
				q.add(currNode.right);
				nextDepthElements++;
				collected.add(currNode.right.data);
			}
			
			if(--elementsTillNextDepth == 0) {
				Permutations<Integer> perm = new Permutations(collected.toArray());
				ArrayList<Integer[]> permutations = new ArrayList<>();
				
				while(perm.hasNext()) {
					permutations.add(perm.next());
				}
				ArrayList<LinkedList<Integer>> tempList = new ArrayList<>();
				for(LinkedList<Integer> list : seqs) {
					int size = permutations.size();
					while(size-- > 0) {
						Object clone = list.clone();
						if(clone instanceof LinkedList<?>) {
							tempList.add((LinkedList<Integer>) clone);
						}
					}
				}
				seqs.addAll(tempList);
				if(currDepth == 0) {
					
				}
				
				for (Integer j : collected) {
					
				}
				
				currDepth++;
				elementsTillNextDepth = nextDepthElements;
				nextDepthElements = 0;
			}
			
		}
		return seqs;
	}
	
	public static void main(String[] args) {
		TreeNode node = new TreeNode(100);
		int[] array = {100, 50, 20, 75, 150, 120, 170};
		for (int a : array) {
			node.insertInOrder(a);
		}
		ArrayList<LinkedList<Integer>> allSeq = findSeq(node);
		for (LinkedList<Integer> list : allSeq) {
			System.out.println(list);
		}
		System.out.println(allSeq.size());
	}
}
