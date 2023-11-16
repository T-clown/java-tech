package arithmetic.dynamic_plan;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 最长公共子串
 * 给定两个字符串str1和str2,输出两个字符串的最长公共子串
 * 题目保证str1和str2的最长公共子串存在且唯一。
 */
public class LongestCommonSubstring {

    public static void main(String[] args) {
        System.out.println("NY92514w8AF5q1sul7MVNFZn".length());
        String str1 = "12AB2345CD";
        String str2 = "12345EF";
//        String str1 = "22222";
//        String str2 = "22222";
        //String str1 = "d8Wt20lnSgAw0HgauN2Kspyr298H6wQWMO3tMNRpWmR25NNTD4VTnq16LX80khSMEG0W5V72cIDLvy0WB1Nfnz4z51qrGNKT3xImT141NY92514w8AF5q1sul7MVNFZnGengc03vO912lFftHDkWpMwWN0SY4pXO1QLji18ujkZV4vr449Wo495WOyIXiO4C9M5L7hQ4tX9ePvV5ohnX00e4mOW28xO968cdR266Ej5M";
        // String str2 = "MV3Et2Q4x4YFlN304p5oLJzVT5zdfz8X83srj64mAx18Ai8kE82aF4so17uR3tD7Nch9CO775WHeVD166zgogKQAj4y04EjJ6Mc23Uvmt11NY92514w8AF5q1sul7MVNFZndJq1vh7qx45XOwP1k1M9jsbB3MLc9FFoy825lu0Cs9Bh3Xm84p5C36r6USQrF96W0b05RfF308001LpK89056qQ8517YFj4pM";
        System.out.println(lcs(str1, str2));
    }

    public static String lcs(String str1, String str2) {
        List<List<Character>> sub = new ArrayList<>();
        //是否已初始化集合
        boolean init = false;
        for (int i = 0; i < str1.length(); i++) {
            for (int start = 0, j = start, idx = i; j < str2.length() && idx < str1.length(); ) {
                if (init) {
                    //非第一次进来
                    j++;
                    if (j >= str2.length()) {
                        break;
                    }
                }
                //当相等的时候，加入集合
                char c1 = str1.charAt(idx);
                char c2 = str2.charAt(j);
                if (c1 == c2) {
                    if (!init) {
                        //初始化集合
                        sub.add(new ArrayList<>());
                        init = true;
                    }
                    //添加到集合
                    sub.get(sub.size() - 1).add(str1.charAt(idx));
                    idx++;
                } else {
                    start++;
                    //重置开始位置
                    j = start;
                    idx = i;
                    init = false;
                }
            }

            init = false;
        }
        List<List<Character>> collect = sub.stream().filter(x -> x.size() >= 24).collect(Collectors.toList());
        String maxStr = "";
        for (int i = 0; i < sub.size(); i++) {
            List<Character> list = sub.get(i);
            if (maxStr.length() > list.size()) {
                continue;
            }
            maxStr = "";
            for (int j = 0; j < list.size(); j++) {
                maxStr += list.get(j);
            }
        }
        return maxStr;
    }

    /**
     * 枚举法
     * 时间复杂度O(m^2n)
     * 其中m是str1的长度，n是str2的长度，分别枚举两个字符串每个字符作为起点，后续检查子串长度最坏需要花费O(n)
     * 空间复杂度O(n)  res属于返回必要空间，temps属于临时辅助空间，最坏情况下长度为n
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String lcs2(String str1, String str2) {
        int length = 0;
        String res = "";
        //遍历s1每个起始点
        for (int i = 0; i < str1.length(); i++) {
            //遍历s2每个起点
            for (int j = 0; j < str2.length(); j++) {
                int count = 0;
                int x = i, y = j;
                //比较每个起点为始的子串
                while (x < str1.length() && y < str2.length() && str1.charAt(x) == str2.charAt(y)) {
                    x++;
                    y++;
                    count++;
                }
                //更新更大的长度子串
                if (length < count) {
                    length = count;
                    res = str1.substring(i, x);
                }
            }
        }
        return res;
    }

    /**
     * 动态规划
     * 时间复杂度O(mn):其中m是str1的长度，n是str2的长度，遍历两个字符串所有字符
     * 空间复杂度:O(mn),dp数组大小为m*n
     *
     * @param str1
     * @param str2
     * @return
     */
    public static String lcs3(String str1, String str2) {
        //dp[i][j]表示到str1第i个个到str2第j个为止的公共子串长度
        int[][] dp = new int[str1.length() + 1][str2.length() + 1];
        int max = 0;
        int pos = 0;
        for (int i = 1; i <= str1.length(); i++) {
            for (int j = 1; j <= str2.length(); j++) {
                //如果该两位相同
                if (str1.charAt(i - 1) == str2.charAt(j - 1)) {
                    //则增加长度
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    //该位置为0
                    dp[i][j] = 0;
                }
                if (dp[i][j] > max) {
                    //更新最大长度
                    max = dp[i][j];
                    pos = i - 1;
                }
            }
        }
        return str1.substring(pos - max + 1, pos + 1);
    }

    public static String lcs4(String str1, String str2) {
        // write code here
        int start = 0;
        int end = 1;
        String result = "";
        while (end <= str2.length()) {
            String tmp = str2.substring(start, end);
            if (str1.contains(tmp)) {
                result = tmp;
                end++;
                continue;
            }
            start++;
            end++;
        }
        return result;
    }


}
