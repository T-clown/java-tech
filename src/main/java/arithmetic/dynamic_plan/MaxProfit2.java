package arithmetic.dynamic_plan;

/**
 * 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1. 你可以多次买卖该只股票，但是再次购买前必须卖出之前的股票
 * 2. 如果不能获取收益，请返回0
 * 3. 假设买入卖出均无手续费
 */
public class MaxProfit2 {
    public static void main(String[] args) {
        int[] arr = {8, 9, 2, 5, 4, 7, 1};
        int[] arr2 = {5, 4, 3, 2, 1};
        int[] arr3 = {8, 9, 3, 5, 1, 3};
        System.out.println(maxProfit(arr3));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;
        int sumProfit = 0;
        int start = prices[0];
        int end = prices[0];
        for (int i = 1; i < prices.length; i++) {
            if (start < prices[i]) {
                if (prices[i] < end) {
                    //递减
                    sumProfit += (end - start);
                    start = prices[i];
                    end = start;
                    profit = 0;
                } else {
                    //递增，则继续遍历
                    profit = prices[i] - start;
                    end = prices[i];
                }
            } else {
                //递减，更新买进的时候
                start = prices[i];
                end = prices[i];
                sumProfit += profit;
                profit = 0;
            }
        }
        return sumProfit + profit;
    }


    public static int maxProfit2(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            //只要某段在递增就有收益
            if (prices[i - 1] < prices[i]) {
                //收益累加
                res += prices[i] - prices[i - 1];
            }
        }
        return res;
    }

    /**
     * 动态规划
     *
     * @param prices
     * @return
     */
    public static int maxProfit3(int[] prices) {
        int n = prices.length;
        //dp[i][0]表示某一天不持股到该天为止的最大收益，dp[i][1]表示某天持股，到该天为止的最大收益
        int[][] dp = new int[n][2];
        //第一天不持股，总收益为0
        dp[0][0] = 0;
        //第一天持股，总收益为减去该天的股价
        dp[0][1] = -prices[0];
        //遍历后续每天，状态转移
        for (int i = 1; i < n; i++) {
            dp[i][0] = Math.max(dp[i - 1][0], dp[i - 1][1] + prices[i]);
            dp[i][1] = Math.max(dp[i - 1][1], dp[i - 1][0] - prices[i]);
        }
        //最后一天不持股，到该天为止的最大收益
        return dp[n - 1][0];
    }
}
