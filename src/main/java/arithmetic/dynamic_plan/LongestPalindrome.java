package arithmetic.dynamic_plan;

/**
 * 对于长度为n的一个字符串A（仅包含数字，大小写英文字母），请设计一个高效算法，计算其中最长回文子串的长度。
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String a = "abbba";
        System.out.println(getLongestPalindrome3(a));
    }

    public static int getLongestPalindrome(String A) {
        if (A == null) {
            return 0;
        }
        if (A.length() < 2) {
            return A.length();
        }
        char[] chars = A.toCharArray();
        int max = 1;
        for (int i = 0, size = chars.length; i < size; i++) {
            for (int j = size - 1; j > i; j--) {
                if (chars[i] == chars[j] && isPalindrome(chars, i, j)) {
                    max = Math.max(max, j - i + 1);
                    break;
                }
            }
        }
        return max;
    }

    public static boolean isPalindrome(char[] chars, int left, int right) {
        while (left < right) {
            if (chars[left++] != chars[right--]) {
                return false;
            }
        }
        return true;
    }


    public static int getLongestPalindrome3(String A) {
        int max = 0;
        int len = A.length();

        for (int i = 0; i < len; ) {
            int begin = i;
            int end = i;
            //找出相同的一部分为中心
            while (end < len - 1 && A.charAt(end) == A.charAt(end + 1)) {
                end++;
            }
            i = end + 1;
            //此时begin到end这部分为相同的字符
            //中心扩散
            while (begin > 0 && end < len - 1 && A.charAt(begin - 1) == A.charAt(end + 1)) {
                begin--;
                end++;
            }
            max = Math.max(max, end - begin + 1);
        }

        return max;
    }
}
