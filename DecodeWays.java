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
		 * 我们使用DP来处理这个题目。算是比较简单基础的一维DP啦。
		 * 
		 * 1. D[i] 表示前i个字符能解的方法。
		 * 
		 * 2. D[i] 有2种解法：
		 * 
		 * 1）. 最后一个字符单独解码。 如果可以解码，则解法中可以加上D[i - 1]
		 * 
		 * 2）. 最后一个字符与上一个字符一起解码。 如果可以解码，则解法中可以加上D[i - 2]
		 * 
		 * 以上2种分别判断一下1个，或是2个是不是合法的解码即可。
		 */
		if (s == null || s.length() == 0) {
			return 0;
		}

		int len = s.length();

		// D[i] 表示含有i个字符的子串的DECODE WAYS.
		int[] D = new int[len + 1];

		D[0] = 1;

		for (int i = 1; i <= len; i++) {
			// D[i] = 0;// it is already 0 by default

			// 现在正在考察的字符的索引.
			int index = i - 1;
			// 最后一个字符独立解码
			if (isValidSingle(s.charAt(index))) {
				D[i] += D[i - 1];
			}

			// 最后一个字符与上一个字符一起解码
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
