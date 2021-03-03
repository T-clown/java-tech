package arithmetic.top100;

/**
 * https://mp.weixin.qq.com/s/4rcNulA8nG7vqkgrAtQRwg
 * 1. 题目描述
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest
 * sum and return its sum.
 *
 * 2. Examples
 * 1th example
 * Input: nums = [-2,1,-3,4,-1,2,1,-5,4]
 * Output: 6
 * Explanation: [4,-1,2,1] has the largest sum = 6
 *
 *
 * 3. Constraints（输入的数据约束）
 *
 * 1 <= nums.length <= 3 * 104
 *
 * -105 <= nums[i] <= 105
 */
public class MaximumSubarray {
    public static void main(String[] args) {
        int[] nums = {-2, 1, -3, 4, -1, 2, 1, 0, 2 ,- 5, 4};
        System.out.println(maxSum(nums));
    }

    public static int maxSum(int[] nums) {
        if (nums.length == 0) { return 0; }
        int max = Integer.MIN_VALUE;

        int i = 0;
        int curSum = 0;
        while (i < nums.length) {
            curSum += nums[i];
            if (curSum > max) { max = curSum; }
            if (curSum < 0) { curSum = 0; }
            i++;
        }
        return max;
    }
}

