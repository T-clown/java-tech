package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * 假设一个球从任意高度自由落下，每次落地后反跳回原高度的一半; 再落下, 求它在第5次落地时，共经历多少米?第5次反弹多高？
 */
public class BallLanding {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        recursion(Double.parseDouble(s), 5);
        System.out.println(total);
        System.out.println(high);
    }

    private static double high = 0.0;

    private static double total = 0.0;

    private static void recursion(double height, int times) {
        if (times == 0) {
            return;
        }
        high = height / 2;
        if (times > 1) {
            total += (height + high);
        } else {
            //最后一次不算回弹的高度
            total += height;
        }
        recursion(high, --times);
    }
}
