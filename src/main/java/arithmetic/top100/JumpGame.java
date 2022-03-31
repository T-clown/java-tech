package arithmetic.top100;

/**
 * https://mp.weixin.qq.com/s/0Rs5HJgwR5RBKXkKVe_gTA
 * 1. 题目描述
 * Given an array of non-negative integers nums, you are initially positioned at the first index of the array.
 * Each element in the array represents your maximum jump length at that position.
 * Determine if you are able to reach the last index.
 * 2. Examples
 * 1th example
 * Input: nums = [2,3,1,1,4]
 * Output: true
 * Explanation: Jump 1 step from index 0 to 1, then 3 steps to the last index.
 */
public class JumpGame {

    public static void main(String[] args) {
        int[] arr = {2, 3, 0, 1, 4};
        System.out.println(jumpLast(arr));

    }

    public static boolean jumpLast(int[] nums) {
        if (nums.length == 1) { return true; }

        int max = 0;

        for (int i = 0; i < nums.length - 1; i++) {
            if (i > max) {
                return false;
            }

            max = Math.max(max, i + nums[i]);

            if (max >= nums.length - 1) { return true; }
        }
        return false;
    }
}
