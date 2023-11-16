package arithmetic.dynamic_plan;

/**
 * https://www.nowcoder.com/practice/6fe0302a058a4e4a834ee44af88435c7?tpId=295&tqId=2366451&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj
 */
public class MinCostClimbingStairs {
    public static void main(String[] args) {
        //int[] arr = {2, 5, 20};
        int[] arr = {1, 100, 1, 1, 1, 90, 1, 1, 80, 1};
        System.out.println(minCostClimbingStairs(arr));
    }

    public static int minCostClimbingStairs(int[] cost) {
        int[][] dp = new int[cost.length][2];
        dp[0][1] = cost[0];
        dp[1][1] = cost[1];
        for (int i = 2; i < cost.length; i++) {
            //如果跳过当前步骤，则花费为上一个步骤停下的花费
            dp[i][0] = dp[i - 1][1];
            //在在当前步骤停下，则花费为上一个和上上个取最小并加上当前的花费
            dp[i][1] = Math.min(dp[i - 2][1], dp[i - 1][1]) + cost[i];
        }
        return Math.min(dp[cost.length - 1][0], dp[cost.length - 1][1]);
    }

    /**
     * 官方答案
     *
     * @param cost
     * @return
     */
    public static int minCostClimbingStairs2(int[] cost) {
        //dp[i]表示爬到第i阶楼梯需要的最小花费
        int[] dp = new int[cost.length + 1];
        for (int i = 2; i <= cost.length; i++) {
            //每次选取最小的方案
            dp[i] = Math.min(dp[i - 1] + cost[i - 1], dp[i - 2] + cost[i - 2]);
        }
        return dp[cost.length];
    }
}
