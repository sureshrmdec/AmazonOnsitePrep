package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class WordBreak {
	/*
Given a string s and a dictionary of words dict, determine if s can be segmented into a space-separated sequence of one or more dictionary words.

For example, given
s = "leetcode",
dict = ["leet", "code"].

Return true because "leetcode" can be segmented as "leet code".
	 */
	public boolean wordBreak(String s, Set<String> wordDict) {
        if (s == null) {
            return false;
        }
        
        int len = s.length();
        if (len == 0) {
            return true;
        }
        
        boolean[] D = new boolean[len + 1];

        // initiate the DP. 注意，这里设置为true是不得已，因为当我们划分字串为左边为0，右边为n的时候，
        // 而右边的n是一个字典string,那么左边必然要设置为true，才能使结果为true。所以空字符串我们需要
        // 认为true
        D[0] = true;
        
        // D[i] 表示i长度的字符串能否被word break.
        for (int i = 1; i <= len; i++) {
        	// 把子串划分为2部分，分别讨论, j 表示左边的字符串的长度
        	// 成立的条件是：左边可以break, 而右边是一个字典单词
        	// 注意，划分的时候，左边是闭区间，右边是开区间.
        	D[i] = false;
        	for (int j = 0; j < i; j++) {
        		if (D[j] && wordDict.contains(s.substring(j, i))) {// [j, i)  When j is 0, D[j] is set to true, making sure it can proceed.
        			// 只要找到任意一个符合条件，我们就可以BREAK; 表示我们检查的
        			// 这一个子串符合题意
        			D[i] = true;
        			break;// only breaks the inner for loop. The outer for loop will continue.
        		}
        	}
        }

        return D[len];
	}
	
	/*
Given a string s and a dictionary of words dict, add spaces in s to construct a sentence where each word is a valid dictionary word.

Return all such possible sentences.

For example, given
s = "catsanddog",
dict = ["cat", "cats", "and", "sand", "dog"].

A solution is ["cats and dog", "cat sand dog"].
	 */
	
	//先用DP算出哪些区间是可以解的，然后在DFS的时候，先判断某区间能否word break，如果不可以，直接退出。
    public List<String> wordBreakHard(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null) {
            return null;
        }
        
        List<String> ret = new ArrayList<String>();
        // 记录切割过程中生成的字母
        List<String> path = new ArrayList<String>();
        
        int len = s.length();
        
        // i: 表示从i索引开始的字串可以word break.
        boolean[] D = new boolean[len + 1];
        D[len] = true;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j <= len - 1; j++) {
                // 左边从i 到 j
                D[i] = false;
                if (D[j + 1] && dict.contains(s.substring(i, j + 1))) {
                    D[i] = true;
                    break;
                }
            }
        }

        dfs4(s, dict, path, ret, 0, D);
        
        return ret;
    }

    public static void dfs4(String s, Set<String> dict, List<String> path, List<String> ret, int index, boolean canBreak[]) {
        int len = s.length();
        if (index == len) {
            // 结束了。index到了末尾
            StringBuilder sb = new StringBuilder();
            for (String str: path) {
                sb.append(str);
                sb.append(" ");
            }
            // remove the last " "
            sb.deleteCharAt(sb.length() - 1);
            ret.add(sb.toString());
            return;
        }
        
        // if can't break, we exit directly.
        if (!canBreak[index]) {
            return;
        }

        for (int i =  index; i < len; i++) {
            // 注意这些索引的取值。左字符串的长度从0到len
            String left = s.substring(index, i + 1);
            if (!dict.contains(left)) {
                // 如果左字符串不在字典中，不需要继续递归
                continue;
            }
            
            // if can't find any solution, return false, other set it 
            // to be true;
            path.add(left);
            dfs4(s, dict, path, ret, i + 1, canBreak);
            /* Without this statementn, input like this:
            "catsanddog"
            ["cat","cats","and","sand","dog"]
            will come out like this:
            ["cat sand dog","cat sand dog cats and dog"]
            while the expected result is:
            ["cats and dog","cat sand dog"]
            */
            path.remove(path.size() - 1);
        }
    }

}
