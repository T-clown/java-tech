package arithmetic.str;

/**
 * 对于一个长度为 n 字符串，我们需要对它做一些变形。
 * <p>
 * 首先这个字符串中包含着一些空格，就像"Hello World"一样，然后我们要做的是把这个字符串中由空格隔开的单词反序，同时反转每个字符的大小写。
 * <p>
 * 比如"Hello World"变形后就变成了"wORLD hELLO"。
 */
public class Trans {
    public static void main(String[] args) {
        String s1 = "h i ";
        String s2 = " This is a sample  ";
        String s3 = "A                                                              b";
        System.out.println(trans(s2, s2.length()));
        System.out.println(trans(s1, s1.length()));
        System.out.println(trans(s3, s3.length()));
    }

    public static String trans2(String s, int n) {
        // write code here
        int left = 0, right = -1;
        char[] str = s.toCharArray();
        char[] ans = new char[n];
        while (right < n) {
            right++;
            left = right;
            while (right < n && str[right] != ' ') {
                right++;
            }
            int idx = 0;
            if (right != n) {
                ans[n - right - 1] = ' ';
            }
            while (left < right) {
                ans[n - right + idx] = (char) (str[left] >= 'a' && str[left] <= 'z' ? str[left] - 32 : str[left] + 32);
                left++;
                idx++;
            }
        }
        return new String(ans);
    }

    public static String trans(String s, int n) {
        if (n == 0 || s == null || s.length() == 0) {
            return "";
        }
        char[] chars = new char[n];
        for (int i = 0; i < n; i++) {
            chars[i] = s.charAt(i);
        }
        char s1 = 'a';
        char s2 = 'z';
        StringBuilder res = new StringBuilder();
        StringBuilder temp = new StringBuilder();
        for (int i = n - 1; i >= 0; i--) {
            char c = chars[i];
            if (c == ' ') {
                res.append(temp.reverse()).append(c);
                temp = new StringBuilder();
            } else {
                if (c <= s2 && c >= s1) {
                    //小写转大写
                    temp.append(String.valueOf(c).toUpperCase());
                } else {
                    //大写转小写
                    temp.append(String.valueOf(c).toLowerCase());
                }
            }
            if (i == 0) {
                res.append(temp.reverse());
            }
        }
        return res.toString();
    }


}
