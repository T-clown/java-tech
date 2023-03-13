package arithmetic.sort;

import com.alibaba.fastjson.JSON;

/**
 * 6.快速排序
 * 时间复杂度是 O(nlogn),极端情况下会退化成 O(n2)
 * 空间复杂度为 O(1)
 */
public class QuickSort2 {

    public static void sort(int[] arr) {
        sort(arr, 0, arr.length - 1);
    }

    private static void sort(int[] arr, int startIndex, int endIndex) {
        if (endIndex <= startIndex) {
            return;
        }
        //切分
        int pivotIndex = partition1(arr, startIndex, endIndex);
        sort(arr, startIndex, pivotIndex - 1);
        sort(arr, pivotIndex + 1, endIndex);
    }

    /**
     * 单边扫描
     *
     * @param arr
     * @param startIndex
     * @param endIndex
     * @return
     */
    private static int partition1(int[] arr, int startIndex, int endIndex) {
        //取基准值
        int pivot = arr[startIndex];
        //Mark初始化为起始下标
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                //小于基准值 则mark+1,并交换位置。
                mark++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
            }
        }
        //基准值与mark对应元素调换位置
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }

    /**
     * 双边扫描
     */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        //取第一个元素为基准值
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (true) {
            //从左往右扫描
            while (arr[left] <= pivot) {
                left++;
                if (left == right) {
                    break;
                }
            }
            //从右往左扫描
            while (pivot < arr[right]) {
                right--;
                if (left == right) {
                    break;
                }
            }

            //左右指针相遇
            if (left >= right) {
                break;
            }

            //交换左右数据
            int temp = arr[left];
            arr[left] = arr[right];
            arr[right] = temp;
        }
        //将基准值插入序列
        int temp = arr[startIndex];
        arr[startIndex] = arr[right];
        arr[right] = temp;
        return right;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
