package arithmetic.company.huawei;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * 现有n种砝码，重量互不相等，分别为 m1,m2,m3…mn ；
 * 每种砝码对应的数量为 x1,x2,x3...xn 。现在要用这些砝码去称物体的重量(放在同一侧)，问能称出多少种不同的重量。
 * <p>
 * 注：
 * <p>
 * 称重重量包括 0
 * <p>
 * 输入描述：
 * 对于每组测试数据：
 * 第一行：n --- 砝码的种数(范围[1,10])
 * 第二行：m1 m2 m3 ... mn --- 每种砝码的重量(范围[1,2000])
 * 第三行：x1 x2 x3 .... xn --- 每种砝码对应的数量(范围[1,10])
 */
public class Weigh {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());

        String[] arr = in.nextLine().split(" ");
        String[] numArr = in.nextLine().split(" ");

        List<Integer> weightList = new ArrayList<Integer>();
        List<Integer> numList = new ArrayList<Integer>();
        for (int i = 0; i < arr.length; i++) {
            weightList.add(Integer.parseInt(arr[i]));
            numList.add(Integer.parseInt(numArr[i]));
        }
        Set<Integer> set = new HashSet<>();
        set.add(0);
        //遍历砝码
        for (int i = 0; i < num; i++) {
            //取当前所有的结果
            ArrayList<Integer> list = new ArrayList<>(set);
            //遍历个数
            for (int j = 1; j <= numList.get(i); j++) {
                for (int k = 0; k < list.size(); k++) {
                    set.add(list.get(k) + weightList.get(i) * j);
                }
            }
        }
        System.out.println(set.size());
    }
}
