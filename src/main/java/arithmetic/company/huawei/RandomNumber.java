package arithmetic.company.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 明明生成了N个1到500之间的随机整数。请你删去其中重复的数字，即相同的数字只保留一个，把其余相同的数去掉，然后再把这些数从小到大排序，按照排好的顺序输出。
 * 数据量<=1000
 */
public class RandomNumber {

    public static void main(String[] args) throws IOException {

        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        String str;
        while ((str = bf.readLine()) != null) {

            boolean[] stu = new boolean[1001];
            StringBuilder sb = new StringBuilder();
            int n = Integer.parseInt(str);
            for (int i = 0; i < n; i++) {
                stu[Integer.parseInt(bf.readLine())] = true;
            }
            for (int i = 0; i < 1001; i++) {
                if (stu[i]) {
                    sb.append(i).append("\n");
                }
            }
            sb.deleteCharAt(sb.length() - 1);
            System.out.println(sb);
        }
    }
}
