package arithmetic.company.huawei;

import java.util.Scanner;

/**
 * 题目描述
 * 若两个正整数的和为素数，则这两个正整数称之为“素数伴侣”，如2和5、6和13，它们能应用于通信加密。现在密码学会请你设计一个程序，从已有的 N （ N 为偶数）个正整数中挑选出若干对组成“素数伴侣”，挑选方案多种多样，例如有4个正整数：2，5，6，13，如果将5和6分为一组中只能得到一组“素数伴侣”，而将2和5、6和13编组将得到两组“素数伴侣”，能组成“素数伴侣”最多的方案称为“最佳方案”，当然密码学会希望你寻找出“最佳方案”。
 * 输入:
 * 有一个正偶数 n ，表示待挑选的自然数的个数。后面给出 n 个具体的数字。
 * 输出:
 * 输出一个整数 K ，表示你求得的“最佳方案”组成“素数伴侣”的对数。
 */
public class PrimePartner {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());
        String str = in.nextLine();
        String[] array = str.split(" ");
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(array[i]);
        }
        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            for (int j = i; j < arr.length; j++) {
                if (isPrime(arr[i] + arr[j])) {
                    count++;
                }
            }
        }
        System.out.println(count);
    }

    /**
     * 判断是否为质数
     *
     * @param prime
     * @return
     */
    private static boolean isPrime(int prime) {
        for (int i = 2; i < prime; i++) {
            if (prime % i == 0) {
                return false;
            }
        }
        return true;
    }
}
