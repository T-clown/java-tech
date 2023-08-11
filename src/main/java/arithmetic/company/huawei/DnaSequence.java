package arithmetic.company.huawei;

import java.util.LinkedHashMap;
import java.util.Scanner;

/**
 * 一个 DNA 序列由 A/C/G/T 四个字母的排列组合组成。 G 和 C 的比例（定义为 GC-Ratio ）是序列中 G 和 C 两个字母的总的出现次数除以总的字母数目（也就是序列长度）。在基因工程中，这个比例非常重要。因为高的 GC-Ratio 可能是基因的起始点。
 * <p>
 * 给定一个很长的 DNA 序列，以及限定的子串长度 N ，请帮助研究人员在给出的 DNA 序列中从左往右找出 GC-Ratio 最高且长度为 N 的第一个子串。
 * DNA序列为 ACGT 的子串有: ACG , CG , CGT 等等，但是没有 AGT ， CT 等等
 */
public class DnaSequence {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        int num = Integer.parseInt(in.nextLine());

        double maxRatio = -1.0;
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            if (i + num <= s.length()) {
                String substring = s.substring(i, i + num);
                double gcRatio = getGCRatio(substring);
                if (gcRatio > maxRatio) {
                    maxRatio = gcRatio;
                    res = substring;
                }
            }
        }
        System.out.println(res);
    }


    private static double getGCRatio(String str) {
        int count = 0;
        double len = str.length();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == 'G' || str.charAt(i) == 'C') {
                count++;
            }
        }
        return count / len;
    }
}
