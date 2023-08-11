package arithmetic.dynamic_plan;

/**
 * 输入一个长度为n的整型数组array，数组中的一个或连续多个整数组成一个子数组，子数组最小长度为1。求所有子数组的和的最大值。
 */
public class FindGreatestSumOfSubArray {
    public static void main(String[] args) {
        int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] arr2 = {-2, -8, -1, -5, -9};
        System.out.println(findGreatestSumOfSubArray4(arr));
        System.out.println(findGreatestSumOfSubArray(arr));
    }

    private static int findGreatestSumOfSubArray4(int[] arr) {
        int[] dp = new int[arr.length];
        dp[0] = arr[0];
        int max = arr[0];
        for (int i = 1; i < dp.length; i++) {
            //状态转移
            dp[i] = Math.max(dp[i - 1] + arr[i], arr[i]);
            max = Math.max(max, dp[i]);
        }
        return max;
    }


    /**
     * @param array
     * @return
     */
    public static int findGreatestSumOfSubArray(int[] array) {
        if (array == null || array.length == 0) {
            return 0;
        }
        if (array.length == 1) {
            return array[0];
        }

        int maxSum = -101;
        int sum = 0;
        for (int i = 0, size = array.length; i < size; i++) {
            sum += array[i];
            maxSum = Math.max(maxSum, sum);
            if (sum <= 0) {
                sum = 0;
            }
        }
        return maxSum;
    }

    /**
     * 动态规划
     *
     * @param array
     * @return
     */
    public static int findGreatestSumOfSubArray2(int[] array) {
        //记录到下标i为止的最大连续子数组和
        int[] dp = new int[array.length];
        dp[0] = array[0];
        int maxsum = dp[0];
        for (int i = 1; i < array.length; i++) {
            //状态转移：连续子数组和最大值
            dp[i] = Math.max(dp[i - 1] + array[i], array[i]);
            //维护最大值
            maxsum = Math.max(maxsum, dp[i]);
        }
        return maxsum;
    }

    public static int findGreatestSumOfSubArray3(int[] array) {
        int x = array[0];
        int y = 0;
        int maxsum = x;
        for (int i = 1; i < array.length; i++) {
            //状态转移：连续子数组和最大值
            y = Math.max(x + array[i], array[i]);
            //维护最大值
            maxsum = Math.max(maxsum, y);
            //更新x的状态
            x = y;
        }
        return maxsum;
    }

}
