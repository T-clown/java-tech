package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * 对输入的字符串进行加解密，并输出。
 * <p>
 * 加密方法为：
 * <p>
 * 当内容是英文字母时则用该英文字母的后一个字母替换，同时字母变换大小写,如字母a时则替换为B；字母Z时则替换为a；
 * <p>
 * 当内容是数字时则把该数字加1，如0替换1，1替换2，9替换0；
 * <p>
 * 其他字符不做变化。
 * <p>
 * 解密方法为加密的逆过程
 */
public class EncryptPassword {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String password = in.nextLine();
        System.out.println(encryption(password));
        String encodePassword = in.nextLine();
        System.out.println(decryption(encodePassword));
    }

    /**
     * 加密
     *
     * @param password
     * @return
     */
    private static String encryption(String password) {
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c < 'z' && c >= 'a') {
                //小写转大写
                chars[i] = (char) (c - 'a' + 'A' + 1);
            } else if (c == 'z') {
                chars[i] = 'A';
            } else if (c < 'Z' && c >= 'A') {
                //大写转小写
                chars[i] = (char) (c + 'a' - 'A' + 1);
            } else if (c == 'Z') {
                chars[i] = 'a';
            } else if (c == '9') {
                chars[i] = '0';
            } else if (c >= '0' && c < '9') {
                chars[i] = (char) (c + 1);
            }
        }
        return String.valueOf(chars);
    }

    /**
     * 解密
     *
     * @param password
     * @return
     */
    private static String decryption(String password) {
        char[] chars = password.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c <= 'Z' && c > 'A') {

                //大写转小写
                chars[i] = (char) (c + 'a' - 'A' - 1);
            } else if (c == 'A') {
                chars[i] = 'z';
            } else if (c <= 'z' && c > 'a') {
                //小写转大写
                chars[i] = (char) (c - 'a' + 'A' - 1);
            } else if (c == 'a') {
                chars[i] = 'Z';
            } else if (c == '0') {
                chars[i] = '9';
            } else if (c > '0' && c <= '9') {
                chars[i] = (char) (c - 1);
            }
        }
        return String.valueOf(chars);
    }


}
