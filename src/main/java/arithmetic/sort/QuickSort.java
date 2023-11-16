package arithmetic.sort;

import com.alibaba.fastjson2.JSON;

/**
 * 6.快速排序
 * 时间复杂度是 O(nlogn),极端情况下会退化成 O(n2)
 * 空间复杂度为 O(1)
 */
public class QuickSort {
    public static void main(String[] args) {
        int[] arr = {1, 4, 6, 2, 6, 8, 8, 0, 10, 9};
        int[] arr2 = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        // bubbleSortTest(arr);
        //countSortTest(arr);
        //MergeSort2.sort(arr2);
        sort(arr2);
        System.out.println(JSON.toJSONString(arr2));
    }

    public static void sort(int[] arr) {
        quickSort(arr, 0, arr.length - 1);
    }

    public static void quickSort(int[] arr, int startIndex, int endIndex) {
        if (startIndex < endIndex) {
            //找出基准
            int partition = partition(arr, startIndex, endIndex);
            //分成两边递归进行
            quickSort(arr, startIndex, partition - 1);
            quickSort(arr, partition + 1, endIndex);
        }
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
                //基准点大于当前i所在的元素，就要让mark标记自动加一，移动下一个位置，并将当前位置的元素与i除的元素进行交换即可。
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
     * 第一步只能先从右往左扫描
     */
    private static int partition(int[] arr, int startIndex, int endIndex) {
        //取第一个元素为基准值
        int pivot = arr[startIndex];
        int left = startIndex;
        int right = endIndex;
        while (left != right) {
            //从右往左扫描，必须是第一步
            while (left < right && pivot < arr[right]) {
                right--;
            }
            //从左往右扫描
            while (left < right && pivot >= arr[left]) {
                left++;
            }
            //找到left比基准大，right比基准小，进行交换
            if (left < right) {
                swap(arr, left, right);
            }
        }
        //第一轮完成，让left和right重合的位置和基准交换，返回基准的位置
        swap(arr, startIndex, left);
        return left;
    }

    private static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
