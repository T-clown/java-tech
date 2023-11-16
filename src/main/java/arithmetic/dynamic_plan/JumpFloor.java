package arithmetic.dynamic_plan;

/**
 * 一只青蛙一次可以跳上1级台阶，也可以跳上2级。求该青蛙跳上一个 n 级的台阶总共有多少种跳法（先后次序不同算不同的结果）。
 * 1---1
 * 2---2(11,2)
 * 3---3(111,12,21)
 * 4---5(1111,112,1211,211,22)
 * f(n)=f(n-1)+f(n-2) n>=3
 */
public class JumpFloor {

    public static void main(String[] args) {
        System.out.println(jumpFloor(7));
        System.out.println(recursion(7));
    }

    public static int jumpFloor(int target) {
        if (target < 3) {
            return target;
        }
        int pre = 1;
        int next = 2;
        for (int i = 3; i <= target; i++) {
            next += pre;
            pre = next - pre;
        }
        return next;
    }

    /**
     * 递归
     *
     * @param target
     * @return
     */
    public static int recursion(int target) {
        if (target < 3) {
            return target;
        }
        return recursion(target - 1) + recursion(target - 2);
    }
}
