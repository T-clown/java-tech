package arithmetic.hash;

import java.util.HashMap;

/**
 * 给一个长度为 n 的数组，数组中有一个数字出现的次数超过数组长度的一半，请找出这个数字。
 * 例如输入一个长度为9的数组[1,2,3,2,2,2,5,4,2]。由于数字2在数组中出现了5次，超过数组长度的一半，因此输出2。
 */
public class MoreThanHalfNum {
    /**
     * 解法一：哈希计数
     * 解法二：排序后遍历一次找出现最多的数
     *
     * @param array
     * @return
     */
    public int moreThanHalfNum(int[] array) {
        if (array.length == 1) {
            return array[0];
        }
        HashMap<Integer, Integer> valueCount = new HashMap<>();
        for (int i = 0, size = array.length; i < size; i++) {
            int value = array[i];
            if (valueCount.containsKey(value)) {
                int count = valueCount.get(value) + 1;
                if (count > (size / 2)) {
                    return value;
                }
                valueCount.put(value, count);
            } else {
                valueCount.put(value, 1);
            }
        }
        return array[0];
    }

    /**
     * 候选法
     * 加入数组中存在众数，那么众数一定大于数组的长度的一半。
     * 思想就是：如果两个数不相等，就消去这两个数，最坏情况下，每次消去一个众数和一个非众数，那么如果存在众数，最后留下的数肯定是众数。
     * @param array
     * @return
     */
    public static int moreThanHalfNum2(int[] array) {
//        int count = 0;
//        int candidate = array[0];
//        for (int num : array) {
//            if (count == 0) {
//                candidate = num;
//            }
//            count += num == candidate ? 1 : -1;
//        }
//        return candidate;

        int voted = 1;
        int res = array[0];
        for (int i = 1; i < array.length; i++) {
            if (array[i] == res) {
                voted++;
            } else {
                voted--;
                if (voted == 0) {
                    voted = 1;
                    res = array[i];
                }
            }
        }
        return res;
    }
}
