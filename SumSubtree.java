package leetcode;

public class SumSubtree {
	// check if Every node in binary tree = sum of subtree

	/*
	 * A tree that has the property you described follows the following rule:
	 * 
	 * for each node:
	 * 
	 * if I'm a leaf, I can hold any value otherwise, my value must be f(right)
	 * + f(left) where f(node) is defined as:
	 * 
	 * If n is a leaf, f(n) = value(n) if n is not a leaf, f(n) = 2*value(n)
	 * This is because leaves only carry their own weight, while nodes bring
	 * both their value and the value of their subtrees (hence 2x)
	 */

	boolean isValidTree(Node root) {
		return isValidNodeRec(root);
	}

	boolean isValidNodeRec(Node node) {
		if (node == null)
			return true;
		else
			return isValidNode(node) && isValidNodeRec(node.left) && isValidNodeRec(node.right);
	}

	boolean isValidNode(Node node) {
		if (!isLeaf(node))
			return node.value == f(node.left) + f(node.right);
		else
			return true;
	}

	int f(Node node) {
		if (node == null)
			return 0;
		else if (isLeaf(node))
			return node.value;
		else
			return 2 * node.value;
	}

	boolean isLeaf(Node node) {
		if (node == null)
			return false;
		return node.left == null && node.right == null;
	}

	public class Node {
		Node left, right;
		int value;

		public Node(int value) {
			this.value = value;
		}
	}
}
