package arithmetic.company.huawei;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

/**
 * 在字符串中找出连续最长的数字串
 * 输入一个字符串，返回其最长的数字子串，以及其长度。若有多个最长的数字子串，则将它们全部输出（按原字符串的相对位置）
 * 输入描述：
 * 输入一个字符串。1<=len(字符串)<=200
 * <p>
 * 输出描述：
 * 输出字符串中最长的数字字符串和它的长度，中间用逗号间隔。如果有相同长度的串，则要一块儿输出（中间不要输出空格）。
 */
public class LongestContinuousNumberStr {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String s = in.nextLine();
            int start = -1;
            int max = -1;
            int count = 0;
            List<String> subStr = new ArrayList<String>();
            for (int i = 0; i < s.length(); i++) {
                char c = s.charAt(i);
                if (c >= '0' && c <= '9') {
                    count++;
                    if (start < 0) {
                        start = i;
                    }
                } else {
                    if (start >= 0) {
                        subStr.add(s.substring(start, i));
                        max = Math.max(max, count);
                        start = -1;
                        count = 0;
                    }
                }
            }
            if (start > 0) {
                subStr.add(s.substring(start));
                max = Math.max(max, count);
            }
            int finalMax = max;
            String collect = subStr.stream().filter(x -> x.length() == finalMax).collect(Collectors.joining(""));
            System.out.println(collect + "," + max);
        }
    }


    public static void demo() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNextLine()) {
            String str = sc.nextLine();
            int len = str.length();
            int[] dp = new int[len + 1];
            int res = 0;
            for (int i = 1; i <= len; i++) {
                char tmp = str.charAt(i - 1);
                if (tmp >= '0' && tmp <= '9') {
                    dp[i] = dp[i - 1] + 1;
                    res = Math.max(res, dp[i]);
                }
            }
            for (int i = 1; i <= len; i++) {
                if (dp[i] == res) {
                    System.out.print(str.substring(i - res, i));
                }
            }
            System.out.println("," + res);
        }
    }
}
