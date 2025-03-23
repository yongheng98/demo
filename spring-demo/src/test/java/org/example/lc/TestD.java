package org.example.lc;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @Classname Test4
 * @Description TODO
 * @Date 2025/2/22 下午12:29
 * @Created by SunMengyuan
 */
public class TestD {
    public static int similarPairs(String[] words) {
        int n = words.length;
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            Set<Character> set = new HashSet<>();
            char[] charArray = word.toCharArray();
            for (int i1 = 0; i1 < charArray.length; i1++) {
                set.add(charArray[i1]);
            }
            map.put(set.toString(), map.getOrDefault(set.toString(), 0) + 1);
        }
        AtomicInteger ans = new AtomicInteger();
        map.forEach((k, v) -> {
            ans.addAndGet(v * (v - 1) / 2);
        });
        return ans.get();
    }
    public static void main(String[] args) {
        System.out.println(similarPairs(new String[]{"aba", "aabb", "abcd", "bac", "aabc", "abc"}));
        System.out.println(similarPairs(new String[]{"aabb","ab","ba"}));
        System.out.println(similarPairs(new String[]{"nba","cba","dba"}));
    }
}
