package arithmetic.company.huawei;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 有一种技巧可以对数据进行加密，它使用一个单词作为它的密匙。
 * 下面是它的工作原理：首先，选择一个单词作为密匙，如TRAILBLAZERS。如果单词中包含有重复的字母，只保留第1个，将所得结果作为新字母表开头，并将新建立的字母表中未出现的字母按照正常字母表顺序加入新字母表。如下所示：
 * A B C D E F G H I J K L M N O P Q R S T U V W X Y Z
 * T R A I L B Z E S C D F G H J K M N O P Q U V W X Y (实际需建立小写字母的字母表，此字母表仅为方便演示）
 * 上面其他用字母表中剩余的字母填充完整。在对信息进行加密时，信息中的每个字母被固定于顶上那行，并用下面那行的对应字母一一取代原文的字母(字母字符的大小写状态应该保留)。因此，使用这个密匙， Attack AT DAWN (黎明时攻击)就会被加密为Tpptad TP ITVH。
 * <p>
 * 请实现下述接口，通过指定的密匙和明文得到密文。
 */
public class StrEncryption {
    /**
     * 字母表
     */
    private static final List<Character> LETTER_TABLE = new ArrayList<>();

    static {
        for (char c = 'a'; c <= 'z'; c++) {
            LETTER_TABLE.add(c);
        }
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String word = in.nextLine().toLowerCase();
        String text = in.nextLine();
        char[] chars = word.toCharArray();
        //新字母表
        List<Character> newSmallList = new ArrayList<>();
        //去重表
        Set<Character> remainSet = new HashSet<>();
        for (char c : chars) {
            if (remainSet.contains(c)) {
                continue;
            }
            remainSet.add(c);
            //newBigList.add((char) (c - 'a' + 'A'));
            newSmallList.add(c);
        }

        for (Character c : LETTER_TABLE) {
            if (newSmallList.contains(c)) {
                continue;
            }
            //填充新字母表
            newSmallList.add(c);
        }
        char[] chars1 = text.toCharArray();

        for (int i = 0; i < chars1.length; i++) {
            char c = chars1[i];
            if (c <= 'z' && c >= 'a') {
                int idx = LETTER_TABLE.indexOf(c);
                chars1[i] = newSmallList.get(idx);
            }
            if (c <= 'Z' && c >= 'A') {
                //转小写
                char c1 = (char) (c - 'A' + 'a');
                int idx = LETTER_TABLE.indexOf(c1);
                chars1[i] = (char) (newSmallList.get(idx) - 'a' + 'A');
            }
        }
        System.out.println(String.valueOf(chars1));
    }
}
