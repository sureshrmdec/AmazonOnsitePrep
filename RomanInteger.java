package leetcode;

import java.util.HashMap;

public class RomanInteger {

	/*
	 * Given a roman numeral, convert it to an integer.
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 */

	public int romanToInt(String s) {
		if (s.length() == 0)
			return 0;
		char[] c = s.toCharArray();
		HashMap<Character, Integer> m = new HashMap<Character, Integer>();
		m.put('I', 1);
		m.put('V', 5);
		m.put('X', 10);
		m.put('L', 50);
		m.put('C', 100);
		m.put('D', 500);
		m.put('M', 1000);

		int n = s.length();
		int sum = m.get(c[n - 1]);
		for (int i = n - 2; i >= 0; i--) {
			if (m.get(c[i + 1]) <= m.get(c[i])) {// 当前数字比后面数字大，加上
				sum = sum + m.get(c[i]);
			} else {
				sum = sum - m.get(c[i]);// 当前数字比后面数字小，减去
			}
		}
		return sum;
	}

	/*
	 * Given an integer, convert it to a roman numeral.
	 * 
	 * Input is guaranteed to be within the range from 1 to 3999.
	 */

	public static String intToRoman(int num) {
		int[] nums = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };

		/*
		 * 1000, 900, 500, 400,  100,  90,   50,  40,  10, 9, 5,  4, 1 
		 * "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X" IX V, IV, I
		 */
		String[] strs = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };

		int i = 0;
		StringBuilder sb = new StringBuilder();
		// greedy.
		while (i < nums.length) {
			// bug 1: should use ">="
			if (num >= nums[i]) {
				sb.append(strs[i]);
				// bug 2: should remember to reduce the nums[i].
				num -= nums[i];
			} else {
				i++;
			}
		}

		return sb.toString();
	}
}
