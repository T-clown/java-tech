package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * Levenshtein 距离，又称编辑距离，指的是两个字符串之间，由一个转换成另一个所需的最少编辑操作次数。许可的编辑操作包括将一个字符替换成另一个字符，插入一个字符，删除一个字符。编辑距离的算法是首先由俄国科学家 Levenshtein 提出的，故又叫 Levenshtein Distance 。
 * <p>
 * 例如：
 * <p>
 * 字符串A: abcdefg
 * <p>
 * 字符串B: abcdef
 * <p>
 * 通过增加或是删掉字符 ”g” 的方式达到目的。这两种方案都需要一次操作。把这个操作所需要的次数定义为两个字符串的距离。
 */
//todo
public class LevenshteinDistance {

    public static void main(String[] args) {
        strDistance();
    }


    private static void strDistance() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            //定义动规数组
            int[][] dp = new int[a.length() + 1][b.length() + 1];

            for (int i = 1; i <= a.length(); i++) {
                // 初始化
                dp[i][0] = i;
            }
            for (int i = 1; i <= b.length(); i++) {
                // 初始化
                dp[0][i] = i;
            }
            for (int i = 1; i <= a.length(); i++) {
                for (int j = 1; j <= b.length(); j++) {
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        //第一种情况
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        //第二种情况
                        //状态转移方程
                        dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i - 1][j - 1] + 1, dp[i][j - 1] + 1));
                    }
                }
            }
            System.out.println(dp[a.length()][b.length()]);
        }
    }


    public static void strDistance2() {
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String a = sc.nextLine();
            String b = sc.nextLine();
            //定义动规数组，两行分别表示当前和上一轮的结果，分别为i%2和(i+1)%2
            int[][] dp = new int[2][b.length() + 1];

            for (int i = 1; i <= b.length(); i++) {
                //初始化
                dp[0][i] = i;
            }
            for (int i = 1; i <= a.length(); i++) {
                for (int j = 1; j <= b.length(); j++) {
                    if (a.charAt(i - 1) == b.charAt(j - 1)) {
                        //第一种情况
                        if (j - 1 == 0) {
                            dp[i % 2][j] = i - 1;
                        } else {
                            dp[i % 2][j] = dp[(i + 1) % 2][j - 1];
                        }
                    } else {
                        //第二种情况
                        if (j - 1 == 0) {
                            dp[i % 2][j] = Math.min(dp[(i + 1) % 2][j] + 1, i);
                        } else {
                            dp[i % 2][j] = Math.min(dp[(i + 1) % 2][j] + 1, Math.min(dp[(i + 1) % 2][j - 1] + 1, dp[i % 2][j - 1] + 1));
                        }

                    }
                }
            }
            System.out.println(dp[a.length() % 2][b.length()]);
        }
    }
}
