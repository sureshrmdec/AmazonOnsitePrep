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

        // initiate the DP. ע�⣬��������Ϊtrue�ǲ����ѣ���Ϊ�����ǻ����ִ�Ϊ���Ϊ0���ұ�Ϊn��ʱ��
        // ���ұߵ�n��һ���ֵ�string,��ô��߱�ȻҪ����Ϊtrue������ʹ���Ϊtrue�����Կ��ַ���������Ҫ
        // ��Ϊtrue
        D[0] = true;
        
        // D[i] ��ʾi���ȵ��ַ����ܷ�word break.
        for (int i = 1; i <= len; i++) {
        	// ���Ӵ�����Ϊ2���֣��ֱ�����, j ��ʾ��ߵ��ַ����ĳ���
        	// �����������ǣ���߿���break, ���ұ���һ���ֵ䵥��
        	// ע�⣬���ֵ�ʱ������Ǳ����䣬�ұ��ǿ�����.
        	D[i] = false;
        	for (int j = 0; j < i; j++) {
        		if (D[j] && wordDict.contains(s.substring(j, i))) {// [j, i)  When j is 0, D[j] is set to true, making sure it can proceed.
        			// ֻҪ�ҵ�����һ���������������ǾͿ���BREAK; ��ʾ���Ǽ���
        			// ��һ���Ӵ���������
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
	
	//����DP�����Щ�����ǿ��Խ�ģ�Ȼ����DFS��ʱ�����ж�ĳ�����ܷ�word break����������ԣ�ֱ���˳���
    public List<String> wordBreakHard(String s, Set<String> dict) {
        if (s == null || s.length() == 0 || dict == null) {
            return null;
        }
        
        List<String> ret = new ArrayList<String>();
        // ��¼�и���������ɵ���ĸ
        List<String> path = new ArrayList<String>();
        
        int len = s.length();
        
        // i: ��ʾ��i������ʼ���ִ�����word break.
        boolean[] D = new boolean[len + 1];
        D[len] = true;
        for (int i = len - 1; i >= 0; i--) {
            for (int j = i; j <= len - 1; j++) {
                // ��ߴ�i �� j
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
            // �����ˡ�index����ĩβ
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
            // ע����Щ������ȡֵ�����ַ����ĳ��ȴ�0��len
            String left = s.substring(index, i + 1);
            if (!dict.contains(left)) {
                // ������ַ��������ֵ��У�����Ҫ�����ݹ�
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
