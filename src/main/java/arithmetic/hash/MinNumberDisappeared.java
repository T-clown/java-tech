package arithmetic.hash;

import java.util.HashSet;
import java.util.Set;

/**
 * 给定一个无重复元素的整数数组nums，请你找出其中没有出现的最小的正整数
 */
public class MinNumberDisappeared {
    public static void main(String[] args) {
        int[] array = {2, 1, 3, 7, 6, 9, 5, 11};
        System.out.println(minNumberDisappeared(array));
    }

    public static int minNumberDisappeared2(int[] nums) {
        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }
        int res = 1;
        while (set.contains(res)) {
            res++;
        }
        return res;
    }


    public static int minNumberDisappeared(int[] nums) {
        int n = nums.length;
        //遍历数组
        for (int i = 0; i < n; i++) {
            //负数全部记为n+1
            if (nums[i] <= 0) {
                nums[i] = n + 1;
            }
        }
        for (int i = 0; i < n; i++) {
            //对于1-n中的数字
            if (Math.abs(nums[i]) <= n) {
                //这个数字的下标标记为负数
                nums[Math.abs(nums[i]) - 1] = -1 * Math.abs(nums[Math.abs(nums[i]) - 1]);
            }
        }
        for (int i = 0; i < n; i++) {
            //找到第一个元素不为负数的下标
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        return n + 1;
    }
}
