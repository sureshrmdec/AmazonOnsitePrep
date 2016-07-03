package leetcode;

import java.util.ArrayList;
import java.util.List;

public class MajorityElement {
	
	
	/*
	 *Given an array of size n, find the majority element. The majority element is the element that appears more than floor(n/2) times.

		You may assume that the array is non-empty and the majority element always exist in the array.
	 */
	// Moore voting algorithm
	public int majorityElement3(int[] nums) {
	    int count=0, ret = 0;
	    for (int num: nums) {
	        if (count==0)
	            ret = num;
	        if (num!=ret)
	            count--;
	        else
	            count++;
	    }
	    return ret;
	}
	
	/*
	 * Given an integer array of size n, find all elements that appear more than floor(n/3) times. The algorithm should run in linear time and in O(1) space.
	 */
	
	
	/*
    Based on Moore's Voting Algorithm, we need two candidates with top 2 frequency. 
    If meeting different number from the candidate, then decrease 1 from its count, or increase 1 on the opposite condition. 
    Once count equals 0, then switch the candidate to the current number. 
    The trick is that we need to count again for the two candidates after the first loop. 
    Finally, output the numbers appearing more than n/3 times.
    */
    public List<Integer> majorityElement(int[] nums) {
        List<Integer> rst = new ArrayList<Integer>();
        if(nums == null || nums.length == 0) return rst;
        int count1 = 0, count2 = 0, candidate1 = 0, candidate2 = 1;
        for(int num : nums){
            if(num == candidate1) count1++;
            else if(num == candidate2) count2++;
            else if(count1 == 0){
                candidate1 = num;
                count1 = 1;
            }
            else if(count2 == 0){
                candidate2 = num;
                count2 = 1;
            }
            else{
                count1--;
                count2--;
            }
        }
        count1 = 0; count2 = 0;
        for(int num : nums){
            if(num == candidate1) count1+=2;
            else count1--;
            if(num == candidate2) count2 += 2;
            else count2--;
        }
        if(count1 > 0) rst.add(candidate1);
        if(count2 > 0) rst.add(candidate2);
        return rst;
    }
	
	
}
