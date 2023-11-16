package arithmetic.company.huawei;

public class GreatestCommonDivisor {
    public static void main(String[] args) {
        int num1 = 12;
        int num2 = 18;

        int gcd = findGCD2(num1, num2);
        int lcm = findLCM(num1, num2, gcd);

        System.out.println("最大公约数：" + gcd);
        System.out.println("最小公倍数：" + lcm);

        System.out.println(findGCD2(5, 7));
    }

    /**
     * 求最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    private static int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    /**
     * 求最大公约数
     *
     * @param a
     * @param b
     * @return
     */
    private static int findGCD2(int a, int b) {
        System.out.println(a + "-" + b);
        if (b == 0) {
            return a;
        }
        return findGCD2(b, a % b);
    }

    /**
     * 求最小公倍数
     *
     * @param a
     * @param b
     * @param gcd
     * @return
     */
    private static int findLCM(int a, int b, int gcd) {
        return (a * b) / gcd;
    }

}
