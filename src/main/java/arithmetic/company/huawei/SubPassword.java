package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * Catcher是MCA国的情报员，他工作时发现敌国会用一些对称的密码进行通信，
 * 比如像这些ABBA，ABA，A，123321，但是他们有时会在开始或结束时加入一些无关的字符以防止别国破解。
 * 比如进行下列变化 ABBA->12ABBA,ABA->ABAKK,123321->51233214　。因为截获的串太长了，
 * 而且存在多种可能的情况（abaaab可看作是aba,或baaab的加密形式），Cathcer的工作量实在是太大了，
 * 他只能向电脑高手求助，你能帮Catcher找出最长的有效密码串吗？
 */
public class SubPassword {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        char[] chars = s.toCharArray();
        int max = 0;
        for (int i = 0; i < chars.length; i++) {
            for (int j = chars.length - 1; j > i; j--) {
                if (chars[i] == chars[j] && isValid(i, j, chars)) {
                    max = Math.max(max, j - i + 1);
                    break;
                }
            }
        }
        System.out.println(max);
    }

    private static boolean isValid(int left, int right, char[] chars) {
        while (left < right) {
            if (chars[left++] != chars[right--]) {
                return false;
            }
        }
        return true;
    }
}
