package arithmetic.sort.common;

import com.alibaba.fastjson.JSON;

/**
 * 快排
 */
public class QuickSort {
    public static void main(String[] args) {
        //int[] arr = {9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        int[] arr = {9, 61, 7, 16, 5, 45, 3, 22, 1, 10};
        sort(arr, 0, arr.length - 1);
        System.out.println(JSON.toJSONString(arr));
    }


    private static void sort(int[] arr, int startIndex, int endIndex) {
        //System.out.println( startIndex+"\t"+endIndex);
        if (endIndex <= startIndex) {
            return;
        }
        //切分
        int pivotIndex = partition2(arr, startIndex, endIndex);
        //System.out.println("基准索引："+pivotIndex);
        sort(arr, startIndex, pivotIndex - 1);
        sort(arr, pivotIndex + 1, endIndex);
    }

    private static int partition2(int[] arr, int startIndex, int endIndex) {
        //取基准值
        int pivot = arr[startIndex];
        //Mark初始化为起始下标
        int mark = startIndex;

        for (int i = startIndex + 1; i <= endIndex; i++) {
            if (arr[i] < pivot) {
                //小于基准值 则mark+1，并交换位置。
                mark++;
                int p = arr[mark];
                arr[mark] = arr[i];
                arr[i] = p;
                System.out.println(mark+"\t"+i);
                System.out.println(JSON.toJSONString(arr));
            }
        }
        //基准值与mark对应元素调换位置
        arr[startIndex] = arr[mark];
        arr[mark] = pivot;
        return mark;
    }


    private static int[] quickSort(int[] arr, int left, int right) {
        if (left < right) {
            int partitionIndex = partition(arr, left, right);
            quickSort(arr, left, partitionIndex - 1);
            quickSort(arr, partitionIndex + 1, right);
        }
        return arr;
    }

    private static int partition(int[] arr, int left, int right) {
        // 设定基准值（pivot）
        int index = left + 1;
        for (int i = index; i <= right; i++) {
            if (arr[i] < arr[left]) {
                // swap(arr, i, index);
                index++;
            }
        }
        swap(arr, left, index - 1);
        System.out.println(JSON.toJSONString(arr));
        System.out.println(index - 1);
        return index - 1;
    }

    /**
     * 快速排序方法
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int[] quickSortb(int[] array, int start, int end) {
        int smallIndex = partitionB(array, start, end);
        if (smallIndex > start) {
            quickSortb(array, start, smallIndex - 1);
        }
        if (smallIndex < end) {
            quickSortb(array, smallIndex + 1, end);
        }
        System.out.println(JSON.toJSONString(array));
        return array;
    }

    /**
     * 快速排序算法——partition
     *
     * @param array
     * @param start
     * @param end
     * @return
     */
    public static int partitionB(int[] array, int start, int end) {
        int pivot = (int) (start + Math.random() * (end - start + 1));
        System.out.println(pivot);
        int smallIndex = start - 1;
        swap(array, pivot, end);
        for (int i = start; i <= end; i++) {
            if (array[i] <= array[end]) {
                smallIndex++;
                if (i > smallIndex) {
                    swap(array, i, smallIndex);
                }
            }
        }
        return smallIndex;
    }

    private static void swap(int[] arr, int i, int j) {
        System.out.println(i + "  " + j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }
}
