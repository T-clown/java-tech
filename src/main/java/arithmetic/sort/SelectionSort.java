package arithmetic.sort;

import java.util.Arrays;

/**
 * 2.选择排序
 * 时间复杂度为O(n2)
 * 空间复杂度为O(1)
 * 选择排序的思路：
 * 首先，找到数组中最小的元素，拎出来，将它和数组的第一个元素交换位置，
 * 第二步，在剩下的元素中继续寻找最小的元素，拎出来，和数组的第二个元素交换位置，如此循环，直到整个数组排序完成
 */
public class SelectionSort {
    public static int[] sort(int[] sourceArray) {
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 总共要经过 N-1 轮比较
        for (int i = 0; i < arr.length - 1; i++) {
            int min = i;
            // 每轮需要比较的次数 N-i
            //循环找到最小的数，然后替换，跟冒泡有点像
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[min]) {
                    // 记录目前能找到的最小值元素的下标
                    min = j;
                }
            }
            // 将找到的最小值和i位置所在的值进行交换
            int tmp = arr[i];
            arr[i] = arr[min];
            arr[min] = tmp;
        }
        return arr;
    }
}