package leetcode;

import java.util.Collections;
import java.util.PriorityQueue;

public class FindMedian {
	/*
	 * Median is the middle value in an ordered integer list. If the size of the
	 * list is even, there is no middle value. So the median is the mean of the
	 * two middle value.
	 * 
	 * Examples: [2,3,4] , the median is 3
	 * 
	 * [2,3], the median is (2 + 3) / 2 = 2.5
	 * 
	 * Design a data structure that supports the following two operations:
	 * 
	 * void addNum(int num) - Add a integer number from the data stream to the
	 * data structure. double findMedian() - Return the median of all elements
	 * so far. For example:
	 * 
	 * add(1) add(2) findMedian() -> 1.5 add(3) findMedian() -> 2
	 */

	// ʱ�� O(NlogN) �ռ� O(N)
	PriorityQueue<Integer> max = new PriorityQueue<Integer>();// this is a min heap, but it stores the large half (the top is the minimum of the heap)
	PriorityQueue<Integer> min = new PriorityQueue<Integer>(Collections.reverseOrder());// this is a max heap, but it stores the small half (top is the maximum of the heap)
	// ������λ��ֻ�п����ǶѶ����߶Ѷ��������ľ�ֵ.��������Ѻ�Ҫ��֤�����ѵĴ�С֮�����1.
	// Adds a number into the data structure.

	public void addNum(int num) {
		max.offer(num);
		min.offer(max.poll());
		if (max.size() < min.size()) {
			max.offer(min.poll());
		}
	}

	// Returns the median of current data stream
	public double findMedian() {
		return max.size() == min.size() ? (double) (max.peek() + min.peek()) / 2.0 : max.peek();
	}
}
