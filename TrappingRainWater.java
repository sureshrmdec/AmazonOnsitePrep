package leetcode;

public class TrappingRainWater {
	public int trap(int[] A) {
		if (A.length == 0) {
			return 0;
		}
		int[] left = new int[A.length];
		int[] right = new int[A.length];

		left[0] = A[0];
		for (int i = 1; i < A.length; i++) {// count the highest bar from the
											// left to the current. It is
											// i<A.length
			left[i] = Math.max(left[i - 1], A[i]);
		}
		right[A.length - 1] = A[A.length - 1];
		for (int i = A.length - 2; i >= 0; i--) {// count the highest bar from
													// right to current. It is
													// i>=0
			right[i] = Math.max(right[i + 1], A[i]);
		}
		int sum = 0;
		// 注意i的范围是(i, A.length - 1) 因为i是0或者A.length-1时,一边没有高度,存不了水
		for (int i = 1; i < A.length - 1; i++) {// 再扫描一次，把这两者的低者作为{桶}的高度，如果这个桶高于A[i]的bar
												// ,那么A[i]这个bar上头可以存储height -
												// A[i]这么多水。把这所有的水加起来即可
			int height = Math.min(right[i], left[i]);
			if (height > A[i]) {
				sum += height - A[i];
			}
		}

		return sum;
	}
}
