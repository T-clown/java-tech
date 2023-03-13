package arithmetic.dynamic_plan;

import java.util.Arrays;

/**
 * 给定一个长度为 n 的数组 arr，求它的最长严格上升子序列的长度。
 * 所谓子序列，指一个数组删掉一些数（也可以不删）之后，形成的新数组。例如 [1,5,3,7,3] 数组，其子序列有：[1,3,3]、[7] 等。但 [1,6]、[1,3,5] 则不是它的子序列。
 */
public class LIS {
    public static void main(String[] args) {
        int[] array = {6, 3, 1, 5, 2, 3, 7};
        System.out.println(lis(array));
    }

    /**
     * 哪里有问题？
     *
     * @param arr
     * @return
     */
    public static int lis(int[] arr) {
        int[] temp = new int[arr.length];
        boolean has = false;
        int hasIdx = 0;
        int start = 0;
        for (int i = 0; i < arr.length; i++) {
            int j;
            if (has) {
                //如果有比当前值大的，则需要再遍历
                i = start;
                //从遇到第一个比自己大的下一个开始
                j = hasIdx + 1;
                has = false;
            } else {
                //下一位
                j = i + 1;
            }
            //初始化，自己为最大值
            int max = arr[i];
            int length = 1;
            for (; j < arr.length; j++) {
                int value = arr[j];
                if (max <= value) {
                    if (!has) {
                        start = i;
                        has = true;
                        hasIdx = j;
                    }
                    max = value;
                    length++;
                }
            }
            temp[i] = Math.max(length, temp[i]);
        }

        int lis = 0;
        for (int i = 0; i < temp.length; i++) {
            lis = Math.max(lis, temp[i]);
        }
        return lis;
    }

    /**
     * @param arr
     * @return
     */
    public static int lcs2(int[] arr) {
        int[] dp = new int[arr.length];
        //设置数组长度大小的动态规划辅助数组
        Arrays.fill(dp, 1);
        int res = 0;
        for (int i = 1; i < arr.length; i++) {
            for (int j = 0; j < i; j++) {
                //可能j不是所需要的最大的，因此需要dp[i] < dp[j] + 1
                if (arr[i] > arr[j] && dp[i] < dp[j] + 1) {
                    //i点比j点大，理论上dp要加1
                    dp[i] = dp[j] + 1;
                    //找到最大长度
                    res = Math.max(res, dp[i]);
                }
            }
        }
        return res;
    }

    public int lcs3(int[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        int len = arr.length;
        int maxLen = 0;
        int[] dp = new int[len];
        Arrays.fill(dp, 1);
        for (int i = 1; i < len; i++) {
            for (int j = i - 1; j >= 0; j--) {
                if (arr[i] > arr[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }
        return maxLen;
    }
}
