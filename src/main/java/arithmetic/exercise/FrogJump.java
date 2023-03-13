package arithmetic.exercise;

public class FrogJump {
    public static void main(String[] args) {
        System.out.println(getN(10));
    }

    /**
     * /**
     * *一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * f(n)=f(n-2)+f(n-1)
     * <p>
     * 台阶数      跳跃方式
     * 1          1      1
     * 2          2      1+1   2+0
     * 3          3      1+1+1   1+2   2+1
     * 4          5      1+1+1+1(1种)   1+2+1  1+1+2  2+1+1  2+2
     * 5          8      全是1(1种)   两个2(3种)  3个1(4种)
     */

    int jumpFloor(int number) {
        if (number <= 0) {
            return 0;
        }
        if (number == 1) {
            return 1;
        }
        if (number == 2) {
            return 2;
        }
        int first = 1, second = 2, third = 0;
        for (int i = 3; i <= number; i++) {
            third = first + second;
            first = second;
            second = third;
        }
        return third;
    }

    /**
     * 一只青蛙一次可以跳上1级台阶，也可以跳上2级……它也可以跳上n级。求该青蛙跳上一个n级的台阶总共有多少种跳法。
     * f(1)=1
     * f(2)=3
     * f(3)=4
     * f(4)=8
     * f(n)=f(n-1)+f(n-2)+...+f(1)
     * f(n-1)=f(n-2)+...+f(1)
     * f(n)=2f(n-1)
     */
    private static int getN(int n) {
        return 1 << --n;
    }

}