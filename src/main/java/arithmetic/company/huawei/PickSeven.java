package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * 挑7
 * 输出 1到n之间 的与 7 有关数字的个数。
 * 一个数与7有关是指这个数是 7 的倍数，或者是包含 7 的数字（如 17 ，27 ，37 ... 70 ，71 ，72 ，73...）
 */
public class PickSeven {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());
        int a = 7;
        if (num < a) {
            System.out.println(0);
            return;
        }
        int count = 0;
        String str = String.valueOf(a);
        for (int i = 7; i <= num; i++) {
            if (i % 7 == 0 || String.valueOf(i).contains(str)) {
                count++;
            }
        }
        System.out.println(count);
    }

    private boolean conatins7(int n) {
        while (n != 0) {
            if (n % 10 == 7) {
                return true;
            }
            n /= 10;
        }
        return false;
    }
}
