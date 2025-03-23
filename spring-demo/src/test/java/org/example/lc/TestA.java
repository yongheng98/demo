package org.example.lc;

import java.util.Arrays;

/**
 * @Classname TestA
 * @Description TODO
 * @Date 2025/2/17 下午5:53
 * @Created by SunMengyuan
 */
public class TestA {
    public long maxWeight(int[] pizzas) {
        int n = pizzas.length;
        Arrays.sort(pizzas);

        int cnt = n / 4; // 分奇偶
        long ans = 0;
        int index = n - 1;
        int ji = cnt % 2 == 0 ? cnt / 2 : cnt / 2 + 1;
        int ou = cnt - ji;
        while (ji > 0){
            ans += pizzas[index --];
            ji --;
        }
        index --;
        while (ou > 0){
            ans += pizzas[index];
            index -= 2;
            ou --;
        }
//        System.out.println(cnt);
//        System.out.println(ji);
        // if (cnt % 2 == 1){
        //     // cnt / 2 + 1;
        //     for(int i = 0; i < cnt / 2 + 1; i++){
        //         ans += pizzas[index];
        //         index --;
        //     }
        // }
//        for(int i = 0; i < cnt; i++){
//            if (i <= cnt / 2){
//                ans += pizzas[index];
//                if (i != cnt / 2) {
//                    index --;
//                }
//            } else {
//                ans += pizzas[index];
//                index -= 2;
//            }
//        }
        return ans;
    }
    public static void main(String[] args) {
        TestA testA = new TestA();
        System.out.println(testA.maxWeight(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}));
    }
}
