package org.example.lc;

import java.util.Arrays;

/**
 * @Classname TestC
 * @Description TODO
 * @Date 2025/2/20 下午4:59
 * @Created by SunMengyuan
 */
public class TestC {
    public int[] evenOddBit(int n) {
        String binaryString = Integer.toBinaryString(n);
        int length = binaryString.length();
        int a = 0, b = 0;
        char[] charArray = binaryString.toCharArray();
        for (int i = 0; i < charArray.length / 2; i++) {
            char c = charArray[i];
            charArray[i] = charArray[charArray.length - 1 - i];
            charArray[charArray.length - 1 - i] = c;
        }
        for (int i = 0; i < length; i++) {
            char c = charArray[i];
            if (c == '1'){
                if (i % 2 == 0) {
                    a ++;
                } else {
                    b ++;
                }
            }
        }
//        System.out.println(binaryString);
        return new int[]{a, b};
    }
    public static void main(String[] args) {
        TestC testC = new TestC();
        Arrays.stream(testC.evenOddBit(50)).forEach(System.out::println);
        Arrays.stream(testC.evenOddBit(2)).forEach(System.out::println);
        Arrays.stream(testC.evenOddBit(5)).forEach(System.out::println);
    }
}
