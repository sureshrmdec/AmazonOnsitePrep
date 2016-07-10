package leetcode;

public class KthSmallestInBST {
	/*
	 * Given a binary search tree, write a function kthSmallest to find the kth
	 * smallest element in it.
	 * 
	 * Note: You may assume k is always valid, 1 ¡Ü k ¡Ü BST's total elements.
	 */

	// To make it O(height of BST), just add property size to BST node.
	// When insert/delete, update size too. Then we can get kth smallest element according to size.
	/*
	 * Visit from root of the BST:
	 * 
	 * if root.size == k, return root.val. 
	 * if root.size > k, search the k smallest element of root.left 
	 * if root.size < k, search the k - root.size smallest element of root.right
	 * At most we search height of BST times, so
	 * it's O(height of BST).
	 */
public int kthSmallestInorder(TreeNode root, int k) {//O(n)
    Stack<TreeNode> stack = new Stack<TreeNode>();
 
    TreeNode p = root;
    int result = 0;
 
    while(!stack.isEmpty() || p!=null){
        if(p!=null){
            stack.push(p);
            p = p.left;
        }else{
            TreeNode t = stack.pop();
            k--;
            if(k==0)
                result = t.val;
            p = t.right;
        }
    }
 
    return result;
}
	 
	public int kthSmallest(TreeNode root, int k) {
		int count = countNodes(root.left);
		if (k <= count) {
			return kthSmallest(root.left, k);
		} else if (k > count + 1) {
			return kthSmallest(root.right, k - 1 - count); // 1 is counted as current node
		}

		return root.val;
	}

	public int countNodes(TreeNode n) {
		if (n == null)
			return 0;
		return 1 + countNodes(n.left) + countNodes(n.right);
	}

	// DFS in-order recursive. better keep these two variables in a wrapper
	// class
	private static int number = 0;
	private static int count = 0;

	public int kthSmallestInOrder(TreeNode root, int k) {
		count = k;
		helper(root);
		return number;
	}

	public void helper(TreeNode n) {
		if (n.left != null)
			helper(n.left);
		count--;
		if (count == 0) {
			number = n.val;
			return;
		}
		if (n.right != null)
			helper(n.right);
	}

	public class TreeNode {
		int val;
		TreeNode left;
		TreeNode right;

		TreeNode(int x) {
			val = x;
		}
	}
}
