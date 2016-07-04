package leetcode;

import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;

public class BTSerieDeserie {
/*
 * Serialization is the process of converting a data structure or object into a sequence of bits so that it can be stored in a file or memory buffer, or transmitted across a network connection link to be reconstructed later in the same or another computer environment.

Design an algorithm to serialize and deserialize a binary tree. There is no restriction on how your serialization/deserialization algorithm should work. You just need to ensure that a binary tree can be serialized to a string and this string can be deserialized to the original tree structure.

For example, you may serialize the following tree

    1
   / \
  2   3
     / \
    4   5
as "[1,2,3,null,null,4,5]"You do not necessarily need to follow this format, so please be creative and come up with different approaches yourself.
Note: Do not use class member/global/static variables to store states. Your serialize and deserialize algorithms should be stateless.
 */
	
	/*
	The idea is simple: print the tree in pre-order traversal and use "X" to denote null node and split node with ",". We can use a StringBuilder for building the string on the fly. For deserializing, we use a Queue to store the pre-order traversal and since we have "X" as null node, we know exactly how to where to end building subtress.
	*/
	    private static final String spliter = ",";
	    private static final String NN = "X";

	    // Encodes a tree to a single string.
	    public String serialize(TreeNode root) {
	        StringBuilder sb = new StringBuilder();
	        buildString(root, sb);
	        return sb.toString();
	    }

	    private void buildString(TreeNode node, StringBuilder sb) {
	        if (node == null) {
	            sb.append(NN).append(spliter);
	        } else {
	            sb.append(node.val).append(spliter);
	            buildString(node.left, sb);
	            buildString(node.right,sb);
	        }
	    }
	    // Decodes your encoded data to tree.
	    public TreeNode deserialize(String data) {
	        Deque<String> nodes = new LinkedList<>();
	        nodes.addAll(Arrays.asList(data.split(spliter)));
	        return buildTree(nodes);
	    }

	    private TreeNode buildTree(Deque<String> nodes) {
	        String val = nodes.remove();//Retrieves and removes the head of this queue. This method differs from poll only in that it throws an exception if this queue is empty.
	        if (val.equals(NN)) return null;
	        else {
	            TreeNode node = new TreeNode(Integer.valueOf(val));
	            node.left = buildTree(nodes);
	            node.right = buildTree(nodes);
	            return node;
	        }
	    }
	    
	     public class TreeNode {
	    	 int val;
	    	 TreeNode left;
	    	 TreeNode right;
	    	 TreeNode(int x) { val = x; }
	     }
	    
	 // Your Codec object will be instantiated and called as such:
	 // Codec codec = new Codec();
	 // codec.deserialize(codec.serialize(root));
}