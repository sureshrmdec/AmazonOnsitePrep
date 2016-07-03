package leetcode;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class IteratorList {
    /*
Given two 1d vectors, implement an iterator to return their elements alternately.
For example, given two 1d vectors:
v1 = [1, 2]
v2 = [3, 4, 5, 6]
By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1, 3, 2, 4, 5, 6].
Follow up: What if you are given k 1d vectors? How well can your code be extended to such cases?
Clarification for the follow up question - Update (2015-09-18):
The "Zigzag" order is not clearly defined and is ambiguous for k > 2 cases. If "Zigzag" does not look right to you, replace "Zigzag" with "Cyclic". For example, given the following input:
[1,2,3]
[4,5,6,7]
[8,9]
It should return [1,4,8,2,5,9,3,6,7].
     */
    private class ZigzagIterator {
        LinkedList<Iterator> list;
        public ZigzagIterator(List<Integer> v1, List<Integer> v2) {
            list = new LinkedList<Iterator>();
            if(!v1.isEmpty()) list.add(v1.iterator());
            if(!v2.isEmpty()) list.add(v2.iterator());
        }

        // could use a Deque to make it faster
        public int next() {
            Iterator poll = list.remove();
            int result = (Integer)poll.next();
            if(poll.hasNext()) list.add(poll);
            return result;
        }

        public boolean hasNext() {
            return !list.isEmpty();
        }
    }
    
    private class ZigzagIteratorDeque {
        Deque<Iterator> list;
        public ZigzagIteratorDeque(List<Integer> v1, List<Integer> v2) {
            list = new ArrayDeque<Iterator>();
            if(!v1.isEmpty()) list.add(v1.iterator());
            if(!v2.isEmpty()) list.add(v2.iterator());
        }

        // could use a Deque to make it faster
        public int next() {
            Iterator poll = list.pollFirst();
            int result = (Integer)poll.next();
            if(poll.hasNext()) list.offerLast(poll);
            return result;
        }

        public boolean hasNext() {
            return !list.isEmpty();
        }
    }
    
    // =============================================
    // This is the one prepared for Google interview
    // =============================================

    public static void main(String... args) {
        Iterator<Integer> a = Arrays.asList(1, 2, 3).iterator();
        Iterator<Integer> b = Arrays.asList(4, 5).iterator();
        Iterator<Integer> c = Arrays.asList(6, 7, 8).iterator();
        Iterator<Iterator<Integer>> it = Arrays.asList(a, b, c).iterator();
        for (Integer i : iterateInOrder(it)) {
            System.out.print(i + ", ");
        }
    }

    /*
     Iterator of Iterator
     [1, 2, 3]
     [4, 5]
     [6, 7, 8]
     1, 4, 6, 2, 5, 7, 3, 8
     */
    public static List<Integer> iterateInOrder(Iterator<Iterator<Integer>> it) {
        List<Iterator<Integer>> iteratorList = new ArrayList<Iterator<Integer>>();
        List<List<Integer>> lists = new ArrayList<List<Integer>>();
        List<Integer> ret = new ArrayList<Integer>();
        int count = 0;
        while (it.hasNext()) {
            iteratorList.add(it.next());
        }
        for (int i = 0; i < iteratorList.size(); i++) {
            Iterator<Integer> iterator = iteratorList.get(i);
            List<Integer> temp = new ArrayList<Integer>();
            while (iterator.hasNext()) {
                temp.add(iterator.next());
                count++;
            }
            lists.add(temp);
        }
        iterateVertically(lists, 0, ret, count);
        return ret;
    }

    public static void iterateVertically(List<List<Integer>> in, int start, List<Integer> ret, int count) {
        if (ret.size() == count) {
            return;
        }
        for (int i = 0; i < in.size(); i++) {
            List<Integer> list = in.get(i);
            if (start < list.size()) {
                ret.add(list.get(start));
            }
        }
        iterateVertically(in, ++start, ret, count);
    }
}