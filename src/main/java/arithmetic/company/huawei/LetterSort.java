package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * 编写一个程序，将输入字符串中的字符按如下规则排序。
 * <p>
 * 规则 1 ：英文字母从 A 到 Z 排列，不区分大小写。
 * <p>
 * 如，输入： Type 输出： epTy
 * <p>
 * 规则 2 ：同一个英文字母的大小写同时存在时，按照输入顺序排列。
 * <p>
 * 如，输入： BabA 输出： aABb
 * <p>
 * 规则 3 ：非英文字母的其它字符保持原来的位置。
 * <p>
 * <p>
 * 如，输入： By?e 输出： Be?y
 */
public class LetterSort {

    public static void main(String[] args) {

    }

    private static void sortStr() {
        Scanner in = new Scanner(System.in);
        String str = in.nextLine();
        char[] chars = str.toCharArray();
        char[] others = new char[str.length()];
        int index = 0;
        for (char c = 'A'; c <= 'Z'; c++) {
            //根据A-Z的顺序找出字母排序
            for (int i = 0; i < chars.length; i++) {
                if (chars[i] == c || chars[i] == c - 'A' + 'a') {
                    others[index++] = chars[i];
                }
            }
        }
        index = 0;
        for (int i = 0; i < chars.length; i++) {
            //把原数组的字母替换成排好的字母
            if (chars[i] >= 'A' && chars[i] <= 'Z' || chars[i] >= 'a' && chars[i] <= 'z') {
                chars[i] = others[index++];
            }
        }
        System.out.println(String.valueOf(chars));
    }


}
