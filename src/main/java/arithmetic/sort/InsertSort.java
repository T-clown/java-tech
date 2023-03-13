package arithmetic.sort;

import java.util.Arrays;

/**
 * 3.插入排序
 * 时间复杂度是 O(n2)
 * 空间复杂度为O(1)
 */
public class InsertSort {
    public static int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        // 从下标为1的元素开始选择合适的位置插入，因为下标为0的只有一个元素，默认是有序的
        for (int i = 1; i < arr.length; i++) {
            // 记录要插入的数据
            int tmp = arr[i];
            // 从已经排序的序列最右边的开始比较，找到比其小的数
            int j = i;
            while (j > 0 && tmp < arr[j - 1]) {
                //把数往后移动
                arr[j] = arr[j - 1];
                j--;
            }
            //插入
            arr[j] = tmp;
        }
        return arr;
    }

    public static void sort2(int[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; ++i) {
            int value = arr[i];
            //插入的位置
            int j;
            for (j = i - 1; j >= 0; j--) {
                if (arr[j] > value) {
                    //移动数据
                    arr[j + 1] = arr[j];
                } else {
                    break;
                }
            }
            //插入数据
            arr[j + 1] = value;
        }
    }
}