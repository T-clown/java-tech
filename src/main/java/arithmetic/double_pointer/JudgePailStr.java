package arithmetic.double_pointer;

/**
 * 给定一个长度为 n 的字符串，请编写一个函数判断该字符串是否回文。如果是回文请返回true，否则返回false。
 * <p>
 * 字符串回文指该字符串正序与其逆序逐字符一致。
 */
public class JudgePailStr {
    public static void main(String[] args) {
        String str = "absba";
        String str2 = "yamatomaya";
        System.out.println(judgeIsPail(str));
        System.out.println(judgeIsPail(str2));
        System.out.println(reverse("abcd"));
    }

    /**
     * 反转字符串
     * 另一种方法是倒序遍历
     *
     * @param str
     * @return
     */
    public static String reverse(String str) {
        if (str == null || str.length() == 1) {
            return str;
        }
        char[] chars = str.toCharArray();
        int start = 0;
        int end = chars.length - 1;
        while (start <= end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
        return String.valueOf(chars);
    }

    public static boolean judgeIsPail(String str) {
        if (str == null) {
            return false;
        }
        int start = 0;
        int end = str.length() - 1;
        while (start <= end) {
            if (str.charAt(start++) != str.charAt(end--)) {
                return false;
            }
        }
        return true;
    }
}
