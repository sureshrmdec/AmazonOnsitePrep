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
		// ע��i�ķ�Χ��(i, A.length - 1) ��Ϊi��0����A.length-1ʱ,һ��û�и߶�,�治��ˮ
		for (int i = 1; i < A.length - 1; i++) {// ��ɨ��һ�Σ��������ߵĵ�����Ϊ{Ͱ}�ĸ߶ȣ�������Ͱ����A[i]��bar
												// ,��ôA[i]���bar��ͷ���Դ洢height -
												// A[i]��ô��ˮ���������е�ˮ����������
			int height = Math.min(right[i], left[i]);
			if (height > A[i]) {
				sum += height - A[i];
			}
		}

		return sum;
	}
}
