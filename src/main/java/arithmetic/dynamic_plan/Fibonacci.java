package arithmetic.dynamic_plan;

/**
 * 大家都知道斐波那契数列，现在要求输入一个正整数 n ，请你输出斐波那契数列的第 n 项。
 * 1 1 2 3 5 8
 */
public class Fibonacci {
    public static void main(String[] args) {
        System.out.println(fibonacci(10));
        System.out.println(recursion(10));
    }

    /**
     * 非递归
     *
     * @param n
     * @return
     */
    public static int fibonacci(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        int pre = 1;
        int next = 1;
        for (int i = 3; i <= n; i++) {
            next += pre;
            pre = next - pre;
        }
        return next;
    }

    /**
     * 递归
     * @param n
     * @return
     */
    public static int recursion(int n) {
        if (n == 1 || n == 2) {
            return 1;
        }
        return recursion(n - 1) + recursion(n - 2);
    }


}
