package arithmetic.exercise;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadLocalRandom;

import org.apache.commons.lang3.StringUtils;

/**
 * 以字符串的形式给定两个非负整数 num1 和 num2，返回 num1 和 num2 的乘积。
 */

public class StringDemo {
    public static void main(String[] args) {
        String a = "abcde";
        String b = "bcda1e";
        System.out.println(rotateString(a, b));
        System.out.println(a.contains(b));
    }

    private static String multiply(String num1, String num2) {
        if (num1 == null || num2 == null) {
            return null;
        }

        int len1 = num1.length(), len2 = num2.length();
        int len3 = len1 + len2;
        int i, j, product, carry;

        int[] num3 = new int[len3];
        for (i = len1 - 1; i >= 0; i--) {
            carry = 0;
            for (j = len2 - 1; j >= 0; j--) {
                product = carry + num3[i + j + 1] +
                        Character.getNumericValue(num1.charAt(i)) *
                                Character.getNumericValue(num2.charAt(j));
                num3[i + j + 1] = product % 10;
                carry = product / 10;
            }
            num3[i + j + 1] = carry;
        }

        StringBuilder sb = new StringBuilder();
        i = 0;

        while (i < len3 - 1 && num3[i] == 0) {
            i++;
        }

        while (i < len3) {
            sb.append(num3[i++]);
        }

        return sb.toString();
    }

    private static boolean rotateString(String A, String B) {
        if (StringUtils.isEmpty(A) || StringUtils.isEmpty(B) || A.length() != B.length()) {
            return false;
        }
        char[] aChars = A.toCharArray();
        char[] bChars = B.toCharArray();
        Arrays.sort(aChars);
        Arrays.sort(bChars);
        if (!Arrays.toString(aChars).equals(Arrays.toString(bChars))) {
            return false;
        }
        for (int i = 0; i < A.length(); i++) {
            String a = A.substring(0, i + 1);
            String b = A.substring(i + 1, A.length());
            if (B.contains(a) && B.contains(b)) {
                return true;
            }
        }
        return false;
    }
}









