package arithmetic.double_pointer;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 给定一个长度为n的数组arr，返回arr的最长无重复元素子数组的长度，无重复指的是所有数字都不相同。
 * 子数组是连续的，比如[1,3,5,7,9]的子数组有[1,3]，[3,5,7]等等，但是[1,3,7]不是子数组
 */
public class MaxLength {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 1, 5, 6, 7, 8, 1};
        System.out.println(maxLength(arr));
        System.out.println(maxLength2(arr));
    }

    /**
     * 官方答案
     *
     * @param arr
     * @return
     */
    public static int maxLength2(int[] arr) {
        //哈希表记录窗口内非重复的数字
        HashMap<Integer, Integer> mp = new HashMap<>();
        int res = 0;
        //设置窗口左右边界
        for (int left = 0, right = 0; right < arr.length; right++) {
            //窗口右移进入哈希表统计出现次数
            int rightValue = arr[right];
            if (mp.containsKey(rightValue)) {
                mp.put(rightValue, rightValue + 1);
            } else {
                mp.put(rightValue, 1);
            }
            //出现次数大于1，则窗口内有重复
            while (mp.get(rightValue) > 1) {
                //窗口右移，同时减去该数字的出现次数
                mp.put(arr[left], mp.get(arr[left++]) - 1);
            }
            //维护子数组长度最大值
            res = Math.max(res, right - left + 1);
        }
        return res;
    }

    /**
     * 网友答案
     *
     * @param arr
     * @return
     */
    public static int maxLength3(int[] arr) {
        int result = 0, start = 0;
        int[] end = new int[100000];
        for (int i = 0; i < arr.length; i++) {
            int num = arr[i];
            if (start < end[num]) {
                start = end[num];
            }
            if (result < i - start + 1) {
                result = i - start + 1;
            }
            end[num] = i + 1;
        }
        return result;
    }

    public static int maxLength(int[] arr) {
        if (arr == null || arr.length <= 1) {
            return arr == null ? 0 : arr.length;
        }
        HashSet<Integer> set = new HashSet<>();
        int maxLen = 0;
        for (int i = 0, size = arr.length; i < size; i++) {
            set.clear();
            set.add(arr[i]);
            for (int j = i + 1; j < size; j++) {
                int value = arr[j];
                if (set.contains(value)) {
                    break;
                } else {
                    set.add(value);
                }
                if (j == (size - 1)) {
                    return Math.max(set.size(), maxLen);
                }
            }
            maxLen = Math.max(set.size(), maxLen);
        }
        return maxLen;
    }

}
