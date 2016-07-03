package leetcode;

/*
 * Implement atoi to convert a string to an integer.

Hint: Carefully consider all possible input cases. If you want a challenge, please do not see below and ask yourself what are the possible input cases.

Notes: It is intended for this problem to be specified vaguely (ie, no given input specs). You are responsible to gather all the input requirements up front.
 */
public class ATOI {
	public int myAtoi(String str) {
		long ret = 0;

		// ___+1234__
		// Delete the leading and tailing spaces.
		String sNew = str.trim();

		if (sNew.length() == 0) {
			return 0;
		}

		boolean positive = true;
		for (int i = 0; i < sNew.length(); i++) {
			char c = sNew.charAt(i);
			if (i == 0 && c == '+') {
				continue;
			} else if (i == 0 && c == '-') {
				positive = false;
				continue;
			}

			if (!(c <= '9' && c >= '0')) {
				break;
			}

			int dig = positive ? c - '0' : '0' - c;

			ret = ret * 10 + dig;

			/*
			 * To deal with overflow, inspect the current number before
			 * multiplication. If the current number is greater than 214748364,
			 * we know it is going to overflow. On the other hand, if the
			 * current number is equal to 214748364, we know that it will
			 * overflow only when the current digit is greater than or equal to
			 * 8.
			 */
			if (ret > Integer.MAX_VALUE) {
				return Integer.MAX_VALUE;
			} else if (ret < Integer.MIN_VALUE) {
				return Integer.MIN_VALUE;
			}
		}

		return (int) ret;
	}
}
