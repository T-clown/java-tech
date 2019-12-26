package arithmetic.exercise;

/**
 * 大家都知道斐波那契数列，现在要求输入一个整数n，请你输出斐波那契数列的第n项。
 * n<=39
 */
public class Fibonacci {

    public static void main(String[] args) {

        long b = System.currentTimeMillis();
        System.out.println(fibonacci(40));
        long c = System.currentTimeMillis();
        System.out.println(recursion(40));
        long d = System.currentTimeMillis();

        System.out.println("时间2：" + (c - b) + "\t" + "时间3：" + (d - c));
    }

    /**
     * 迭代法
     *
     * @param n
     * @return
     */
    private static int fibonacci(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        int first = 1, second = 1;
        for (int i = 3; i <= n; i++) {
            second += first;
            first = second - first;
        }
        return second;
    }

    /**
     * 递归法
     *
     * @param n
     * @return
     */
    private static int recursion(int n) {
        if (n <= 0) {
            return 0;
        }
        if (n == 1 || n == 2) {
            return 1;
        }
        return recursion(n - 2) + recursion(n - 1);
    }

}