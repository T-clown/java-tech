package arithmetic.str;

/**
 * 以字符串的形式读入两个数字，编写一个函数计算它们的和，以字符串形式返回。
 */
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(solve("11", "99"));
    }

    public static String solve(String s, String t) {
        if (s == null || s.length() == 0) {
            return t;
        }
        if (t == null || t.length() == 0) {
            return s;
        }


        String longStr = s.length() > t.length() ? s : t;
        String shortStr = s.length() > t.length() ? t : s;

        StringBuilder res = new StringBuilder();
        int addVal = 0;
        int idx = shortStr.length() - 1;
        for (int i = longStr.length() - 1; i >= 0; i--) {
            char c1 = longStr.charAt(i);
            int sum = c1 - '0' + addVal;
            if (idx >= 0) {
                sum += (shortStr.charAt(idx) - '0');
                idx--;
            }
            int value = sum % 10;
            addVal = sum / 10;
            res.insert(0, value);
        }
        return addVal > 0 ? addVal + res.toString() : res.toString();
    }
}
