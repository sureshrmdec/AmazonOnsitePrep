package leetcode;

import java.util.ArrayList;

public class CommonElementsSortedArray {
	public static void main(String[] args) throws java.lang.Exception {
		// your code goes here
		int[] a1 = { 2, 4, 6, 7, 10 };
		int[] a2 = { 11, 12 };
		for (int i : getCommon(a1, a2)) {
			System.out.println(i);
		}
	}

	private static ArrayList<Integer> getCommon(int[] a1, int[] a2) {
		ArrayList<Integer> ret = new ArrayList<Integer>();
		if (a1 == null || a2 == null)
			return ret;
		int len1 = a1.length;
		int len2 = a2.length;
		if (len1 < 1 || len2 < 1)
			return ret;
		if (a1[len1 - 1] < a2[0] || a2[len2 - 1] < a1[0])
			return ret;
		// assume there are duplicates in any of the arrays
		// if there are no duplicates, can use a hashset
		for (int i = 0, j = 0; i < len1 && j < len2;) {// the condition in the for loop should be connected with &&, not comma
			if (a1[i] < a2[j]) {
				i++;
			} else if (a1[i] > a2[j]) {
				j++;
			} else {
				ret.add(a1[i]);
				i++;
				j++;
			}
		}
		return ret;
	}
}
