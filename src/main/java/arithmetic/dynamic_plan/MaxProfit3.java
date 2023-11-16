package arithmetic.dynamic_plan;

/**
 * 假设你有一个数组prices，长度为n，其中prices[i]是某只股票在第i天的价格，请根据这个价格数组，返回买卖股票能获得的最大收益
 * 1. 你最多可以对该股票有两笔交易操作，一笔交易代表着一次买入与一次卖出，但是再次购买前必须卖出之前的股票
 * 2. 如果不能获取收益，请返回0
 * 3. 假设买入卖出均无手续费
 */
public class MaxProfit3 {
    public static void main(String[] args) {
        int[] arr = {15, 74, 65, 7, 30, 53, 20, 78, 42};
        int[] arr2 = {1, 2, 8, 3, 8};
        int[] arr3 = {8, 9, 3, 5, 1, 3};
        System.out.println(maxProfit(arr));
    }

    public static int maxProfit(int[] prices) {
        if (prices == null || prices.length < 2) {
            return 0;
        }
        int profit = 0;
        int start = prices[0];
        int end = prices[0];
        int max1 = 0;
        int max2 = 0;
        for (int i = 1; i < prices.length; i++) {
            if (start < prices[i]) {
                if (prices[i] < end) {
                    //递减
                    profit = end - start;
                    start = prices[i];
                    end = start;
                    if (profit >= max1) {
                        max2 = max1;
                        max1 = profit;
                    } else if (profit > max2) {
                        max2 = profit;
                    }
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
                if (profit >= max1) {
                    max2 = max1;
                    max1 = profit;
                } else if (profit > max2) {
                    max2 = profit;
                }
                profit = 0;
            }
        }
        if (profit >= max1) {
            max2 = max1;
            max1 = profit;
        } else if (profit > max2) {
            max2 = profit;
        }
        return max1 + max2;
    }



}
