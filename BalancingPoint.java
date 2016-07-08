package leetcode;

public class BalancingPoint {
	// Given an array find the balancing point of the array.
	public static int BalanceBest(int[] a) {
		int leftSum = a[0];
		int rightSum = 0;
		for (int i = 0; i < a.length; i++) {// notice we start from 2nd as 1st
											// value is set
			rightSum += a[i];// each sum is sum of previous sum plus current
								// value
		}
		for (int i = 0; i < a.length - 1; i++) {
			if (leftSum == rightSum) {
				return i;
			}
			leftSum += a[i + 1];
			rightSum -= a[i];
		}
		return -1;// otherwise we return -1 as not found
	}

	//给一个array，找出balance point，即该点左边sum和右边sum的差值最小。O（n）
	public static int BalanceClosest(int[] a) {
		int leftSum = a[0];
		int rightSum = 0;
		int diff = Integer.MAX_VALUE;
		int tempDiff;
		int ret = 0;
		for (int i = 0; i < a.length; i++) {// notice we start from 2nd as 1st
											// value is set
			rightSum += a[i];// each sum is sum of previous sum plus current
								// value
		}
		for (int i = 0; i < a.length - 1; i++) {
			tempDiff = Math.abs(leftSum - rightSum);
			if (tempDiff < diff) {
				diff = tempDiff;
				ret = i;
			}
			leftSum += a[i + 1];
			rightSum -= a[i];
		}
		return ret;// otherwise we return -1 as not found
	}

	public static void main(String[] args) {
		int[] a = { 1, 2, 9, 3, 6, 4, -1 };
		int[] b = { 1, 2, 1, 1 };
		System.out.println("(Best) Balance point for a is index " + BalanceClosest(a));
	}
}
