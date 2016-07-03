package leetcode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public class FindMaxInRotatedArray {
	public static void main(String[] args) {
		// your code goes here
		int[] nums = { 4, 5, 6, 7, 8, 2, 3 };
		int[] nums1 = { 8, 9, 10, 6, 7 };
		System.out.println("123");
		System.out.println(findMax(nums));
	}

	private static int findMax(int[] nums) {
		if (nums == null || nums.length == 0)
			return Integer.MIN_VALUE;
		int left = 0;
		int right = nums.length - 1;
		int max = Integer.MIN_VALUE;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			System.out.println("yo");
			if (nums[mid] > max) {
				max = nums[mid];
			}
			if (nums[mid] > nums[left]) {// left is sorted, branch right
				left = mid + 1;
			} else if (nums[mid] < nums[right]) {// right is sorted, branch left
				if (nums[right] > max) {
					max = nums[right];
				}
				right = mid - 1;
			} else {
				left++;
			}
		}
		return max;
	}
	
	
	
	// This is the text editor interface. 
	// Anything you type or change here will be seen by the other person in real time.

	/*
	Q: Given a set of strings, output all the strings that have a matching anagram in the set. 

	Example: Silent, Listen, And, Nad, Android, Amazon => Silent, Listen, And, Nad
	*/

	public String[] getAnagrams(String[] arr){
	    if(arr == null){
	        return new String[0];
	    }
	    if(arr.length <= 1){
	        return new String[0];
	    }
	    
	    HashMap<String, ArrayList<String>> map = new HashMap<String, ArrayList<String>>();
	    // 1. get a map of String->anagrams(list)
	    // 2. check the values of this map, rule out those whoes values has a size less than 1
	    for(String s:arr){
	        char[] charArr = s.toLowerCase().toCharArray();
	        Arrays.sort(charArr);
	        String s1 = new String(charArr);
	        if(map.containsKey(s1)){
	            map.get(s1).add(s);
	        }else{
	            ArrayList<String> list = new ArrayList<String>();
	            list.add(s);
	            map.put(s1, list);// {adn, list(Nad)} --> {adn, list(Nad, And)}{{abc, list(abc)}}
	        }
	    }
	    
	    List<List<String>> temp = new ArrayList<List<String>>();
	    Iterator iter = map.values().iterator();
	    int count = 0;
	    while(iter.hasNext()){
	        ArrayList<String> item = (ArrayList)iter.next();
	        if(item.size() > 1){
	            temp.add(item);
	            count += item.size();
	        }
	    }
	    
	    String[] ret = new String[count];
	    
	    int i = 0;
	    for(List<String> list:temp){
	        for(String s : list){
	            ret[i++] = s;
	        }
	    }
	    
	    return ret;
	}

	/*
	1. null
	2. empty or has only 1 string
	3. Nad, And, abc
	*/
	// This is the text editor interface. 
	// Anything you type or change here will be seen by the other person in real time.
/*
	Q: Given two strings how would you know they¡¯re anagrams of each other?
	String s1 = "abc"
	String s2 = "cba"
	*/
	public boolean isAnagrams(String s1, String s2){
	    if(s1 == null && s2 == null){
	        return true;
	    }
	    if(s1 == null || s2 == null){
	        return false;
	    }
	    int len1 = s1.length();
	    int len2 = s2.length();
	    if(len1 != len2){
	        return false;
	    }
	    
	    HashMap<Character, Integer> map = new HashMap<Character, Integer>();
	    for(int i=0;i<len1;i++){
	        char c = s1.charAt(i);
	        if(!map.containsKey(c)){
	            map.put(c, 1);
	        }else{
	            map.put(c, map.get(c) + 1);
	        }
	    }
	    
	    for(int i=0;i<len2;i++){
	        char c = s2.charAt(i);
	        if(!map.containsKey(c)){
	            return false;
	        }else{
	            map.put(c, map.get(c) - 1);
	        }
	    }
	    
	    for(int i:map.values()){
	        if(i != 0){
	            return false;
	        }
	    }
	    
	    return true;
	}

	/*
	Test cases:
	1. s1 null s2 null
	2. one of them is null
	3. 
	String s1 = "abcd"
	String s2 = "cba"
	4. 
	String s1 = "abc"
	String s2 = "cba"
	5.
	String s1 = "abb"
	String s2 = "cba"


	Q: Given a set of strings, output all the strings that have a matching anagram in the set. 

	Example: Silent, Listen, And, Nad, Android, Amazon => Silent, Listen, And, Nad
	*/



}
