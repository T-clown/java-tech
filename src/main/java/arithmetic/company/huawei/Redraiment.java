package arithmetic.company.huawei;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.Scanner;


/**
 * Redraiment是走梅花桩的高手。Redraiment可以选择任意一个起点，从前到后，但只能从低处往高处的桩子走。
 * 他希望走的步数最多，你能替Redraiment研究他最多走的步数吗？
 */
@Slf4j
public class Redraiment {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        int num = Integer.parseInt(in.nextLine());
        String[] split = in.nextLine().split(" ");
        int[] arr = new int[num];
        for (int i = 0; i < num; i++) {
            arr[i] = Integer.parseInt(split[i]);
        }
        System.out.println(lengthOfLIS2(arr));
    }

    public static int lengthOfLIS2(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }
        int n = nums.length;
        int[] dp = new int[n];
        dp[0] = 1;
        int maxLen = 1;

        for (int i = 1; i < n; i++) {
            // 默认以第i个元素结尾的子序列长度为1
            dp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (nums[i] > nums[j]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                    log.info("i:{},j:{},num[i]:{},num[j]:{},dp[i]:{},dp[j]:{}", i, j, nums[i], nums[j], dp[i], dp[j]);
                }
            }
            maxLen = Math.max(maxLen, dp[i]);
        }

        return maxLen;
    }

    public static int count(int[] nums) {
        int[] dp = new int[nums.length + 1];
        //初始化为1
        Arrays.fill(dp, 1);
        int max = 1;
        for (int i = 1; i < nums.length; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[j] < nums[i]) {
                    dp[i] = Math.max(dp[i], dp[j] + 1);
                }
                max = Math.max(dp[i], max);
            }
        }
        return max;
    }

    public static int LIS(int[] arr) {
        int n = arr.length;
        // 列表的最大子序列 下标从1开始
        int[] end = new int[n + 1];
        // 存储每个元素的最大子序列个数
        int[] dp = new int[n];
        int len = 1;
        //子序列的第一个元素默认为数组第一个元素
        end[1] = arr[0];
        //第一个元素的最大子序列个数肯定是1
        dp[0] = 1;
        for (int i = 1; i < n; i++) {
            if (end[len] < arr[i]) {
                //当arr[i] > end[len] 时 arr[i]添加到 end后面
                end[++len] = arr[i];
                dp[i] = len;
            } else {
                // 当前元素小于end中的最后一个元素 利用二分法寻找第一个大于arr[i]的元素
                // end[l] 替换为当前元素 dp[]
                int l = 0;
                int r = len;
                while (l <= r) {
                    int mid = (l + r) >> 1;
                    if (end[mid] >= arr[i]) {
                        r = mid - 1;
                    } else {
                        l = mid + 1;
                    }
                }
                end[l] = arr[i];
                dp[i] = l;
            }
        }
        int[] res = new int[len];
        for (int i = n - 1; i >= 0; i--) {
            if (dp[i] == len) {
                res[--len] = arr[i];
            }
        }
        return res.length;
    }


    public static int lengthOfLIS(int[] nums) {
        if (nums == null || nums.length == 0) {
            return 0;
        }

        int n = nums.length;
        int[] tails = new int[n];
        int len = 0;

        for (int num : nums) {
            int left = 0;
            int right = len;

            while (left < right) {
                int mid = left + (right - left) / 2;
                if (tails[mid] < num) {
                    left = mid + 1;
                } else {
                    right = mid;
                }
            }
            tails[left] = num;

            if (left == len) {
                len++;
            }
        }

        return len;
    }
}
