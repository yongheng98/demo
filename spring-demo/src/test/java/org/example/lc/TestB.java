package org.example.lc;

import java.util.HashMap;

/**
 * @Classname TestB
 * @Description TODO
 * @Date 2025/2/17 下午6:08
 * @Created by SunMengyuan
 */
public class TestB {
    /**
     *
     * @param s
     * @param k
     * @return
     */
    public boolean maxSubstringLength(String s, int k) {
        if (k == 0 || k == 1) return true;

        int n = s.length();
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < n; i++){
            map.put(s.charAt(i), map.getOrDefault(s.charAt(i), 0) + 1);
        }
        int ans = 0;
        for (Character c : map.keySet()){
            Integer i = map.get(c);
            if (i == 1) {
                ans ++;
            }
        }
        if (ans >= k){
            return true;
        }
        return false;

    }
    public static void main(String[] args) {
        TestB testB = new TestB();
        System.out.println(testB.maxSubstringLength("abcdbaefab", 2));
        System.out.println(testB.maxSubstringLength("cdefdc", 3));
        System.out.println(testB.maxSubstringLength("abeabe", 0));
    }
}
