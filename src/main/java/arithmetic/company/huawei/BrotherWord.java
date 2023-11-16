package arithmetic.company.huawei;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

/**
 * 定义一个单词的“兄弟单词”为：交换该单词字母顺序（注：可以交换任意次），而不添加、删除、修改原有的字母就能生成的单词。
 * 兄弟单词要求和原来的单词不同。例如： ab 和 ba 是兄弟单词。 ab 和 ab 则不是兄弟单词。
 * 现在给定你 n 个单词，另外再给你一个单词 x ，让你寻找 x 的兄弟单词里，按字典序排列后的第 k 个单词是什么？
 * 注意：字典中可能有重复单词。
 */
public class BrotherWord {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        while (in.hasNext()) {
            String str = in.nextLine();
            String[] arr = str.split(" ");
            int idx = Integer.parseInt(arr[arr.length - 1]);
            int num = Integer.parseInt(arr[0]);
            String target = arr[arr.length - 2];
            char[] targetChars = target.toCharArray();
            Arrays.sort(targetChars);
            String sortTarget = String.valueOf(targetChars);

            List<String> brotherWords = new ArrayList<>();
            for (int i = 1; i <= num; i++) {
                if (arr[i].equals(target)) {
                    continue;
                }
                if (arr[i].length() == target.length()) {
                    char[] chars = arr[i].toCharArray();
                    Arrays.sort(chars);
                    if (String.valueOf(chars).equals(sortTarget)) {
                        brotherWords.add(arr[i]);
                    }
                }
            }
            Collections.sort(brotherWords);
            System.out.println(brotherWords.size());
            if (brotherWords.size() >= idx) {
                System.out.println(brotherWords.get(idx - 1));
            }
        }
    }
}
