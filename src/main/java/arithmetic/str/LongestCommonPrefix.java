package arithmetic.str;

/**
 * 给你一个大小为 n 的字符串数组 strs ，其中包含n个字符串 ,
 * 编写一个函数来查找字符串数组中的最长公共前缀，返回这个公共前缀。
 */
public class LongestCommonPrefix {
    public static void main(String[] args) {
        String[] strs = {"abca", "abc", "abca", "abc", "abcc"};
        System.out.println(longestCommonPrefix(strs));
    }


    public static String longestCommonPrefix(String[] strs) {

        if (strs == null || strs.length == 0) {
            return null;
        }

        String minStr = strs[0];
        for (int i = 1; i < strs.length; i++) {
            String str = strs[i];
            if (str.length() < minStr.length()) {
                minStr = str;
            }
        }

        String commonPrefix = "";
        for (int i = 0; i <= minStr.length(); i++) {
            String str = minStr.substring(0, i);
            for (int j = 0; j < strs.length; j++) {
                if (!strs[j].startsWith(str)) {
                    return commonPrefix;
                }
            }
            commonPrefix = str;
        }
        return commonPrefix;
    }


    public static String longestCommonPrefix2(String[] strs) {
        int n = strs.length;
        //空字符串数组
        if (n == 0) {
            return "";
        }
        //遍历第一个字符串的长度
        for (int i = 0; i < strs[0].length(); i++) {
            char temp = strs[0].charAt(i);
            //遍历后续的字符串
            for (int j = 1; j < n; j++) {
                //比较每个字符串该位置是否和第一个相同
                if (i == strs[j].length() || strs[j].charAt(i) != temp) {
                    //不相同则结束
                    return strs[0].substring(0, i);
                }
            }
        }
        //后续字符串有整个字一个字符串的前缀
        return strs[0];
    }

}
