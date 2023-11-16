package arithmetic.company.huawei;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * 密码要求:
 * <p>
 * 1.长度超过8位
 * <p>
 * 2.包括大小写字母.数字.其它符号,以上四种至少三种
 * <p>
 * 3.不能有长度大于2的包含公共元素的子串重复 （注：其他符号不含空格或换行）
 */
public class ValidPassword {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNextLine()) {
            String password = in.nextLine();
            if (isValid(password)) {
                System.out.println("OK");
            } else {
                System.out.println("NG");
            }
        }
    }

    private static boolean isValid(String password) {
        if (password == null || password.length() < 9) {
            return false;
        }
        //判断特殊字符
        String replace = password.replace(" ", "");
        String replace2 = replace.replace("\n", "");
        if (password.length() != replace2.length()) {
            return false;
        }
        //判断重复子串
        for (int i = 0; i < password.length(); i++) {
            if (i < password.length() - 2) {
                String sub = password.substring(i, i + 3);
                String lastSub = password.substring(i + 3);
                if (lastSub.contains(sub)) {
                    return false;
                }
            }
        }
        //判断密码包含多类字符
        Set<Type> types = new HashSet<Type>();
        for (int i = 0; i < password.length(); i++) {
            char c = password.charAt(i);
            if (c <= 'z' && c >= 'a') {
                types.add(Type.SMALL);
            } else if (c <= 'Z' && c >= 'A') {
                types.add(Type.BIG);
            } else if (c <= '9' && c >= '0') {
                types.add(Type.NUMBER);
            } else {
                types.add(Type.OTHER);
            }
        }
        return types.size() >= 3;
    }

    enum Type {
        /**
         *
         */
        BIG,
        SMALL,
        NUMBER,
        OTHER;
    }
}
