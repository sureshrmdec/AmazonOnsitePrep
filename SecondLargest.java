package leetcode;

public class SecondLargest {
	static int secondLargest(int... nums) {
		int high1 = Integer.MIN_VALUE;
		int high2 = Integer.MIN_VALUE;
		for (int num : nums) {
			if (num >= high1) {
				high2 = high1;
				high1 = num;
			} else if (num > high2) {
				high2 = num;
			}
		}
		return high2;
	}

	public static int secondLargest1(int[] input) {
		int largest, secondLargest;

		if (input[0] > input[1]) {
			largest = input[0];
			secondLargest = input[1];
		} else {
			largest = input[1];
			secondLargest = input[0];
		}

		for (int i = 2; i < input.length; i++) {
			if ((input[i] <= largest) && input[i] > secondLargest) {
				secondLargest = input[i];
			}

			if (input[i] > largest) {
				secondLargest = largest;
				largest = input[i];
			}
		}

		return secondLargest;
	}

	public static void main(String[] args) {
		int[] nums = { 2, 6, 9, 3, 9 };
		System.out.println(secondLargest1(nums));
	}
}
