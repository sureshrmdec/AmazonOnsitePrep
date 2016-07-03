package leetcode;

public class DecodeWays {
	/*
	 * A message containing letters from A-Z is being encoded to numbers using
	 * the following mapping:
	 * 
	 * 'A' -> 1 'B' -> 2 ... 'Z' -> 26 Given an encoded message containing
	 * digits, determine the total number of ways to decode it.
	 * 
	 * For example, Given encoded message "12", it could be decoded as "AB" (1
	 * 2) or "L" (12).
	 * 
	 * The number of ways decoding "12" is 2.
	 */

	public int numDecodings(String s) {
		/*
		 * ����ʹ��DP�����������Ŀ�����ǱȽϼ򵥻�����һάDP����
		 * 
		 * 1. D[i] ��ʾǰi���ַ��ܽ�ķ�����
		 * 
		 * 2. D[i] ��2�ֽⷨ��
		 * 
		 * 1��. ���һ���ַ��������롣 ������Խ��룬��ⷨ�п��Լ���D[i - 1]
		 * 
		 * 2��. ���һ���ַ�����һ���ַ�һ����롣 ������Խ��룬��ⷨ�п��Լ���D[i - 2]
		 * 
		 * ����2�ֱַ��ж�һ��1��������2���ǲ��ǺϷ��Ľ��뼴�ɡ�
		 */
		if (s == null || s.length() == 0) {
			return 0;
		}

		int len = s.length();

		// D[i] ��ʾ����i���ַ����Ӵ���DECODE WAYS.
		int[] D = new int[len + 1];

		D[0] = 1;

		for (int i = 1; i <= len; i++) {
			// D[i] = 0;// it is already 0 by default

			// �������ڿ�����ַ�������.
			int index = i - 1;
			// ���һ���ַ���������
			if (isValidSingle(s.charAt(index))) {
				D[i] += D[i - 1];
			}

			// ���һ���ַ�����һ���ַ�һ�����
			if (i > 1 && isValidTwo(s.substring(index - 1, index + 1))) {
				D[i] += D[i - 2];
			}
		}

		return D[len];
	}

	public boolean isValidSingle(char c) {
		if (c >= '1' && c <= '9') {
			return true;
		}

		return false;
	}

	public boolean isValidTwo(String s) {
		int num = Integer.parseInt(s);

		return (num >= 10 && num <= 26);
	}
}
