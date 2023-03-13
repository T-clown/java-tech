package arithmetic.array;

/**
 * 请实现无重复数字的升序数组的二分查找
 * <p>
 * 给定一个 元素升序的、无重复数字的整型数组 nums 和一个目标值 target ，写一个函数搜索 nums 中的 target，如果目标值存在返回下标（下标从 0 开始），否则返回 -1
 */
public class BinarySearch {
    public static void main(String[] args) {
        int[] arr = {-1, 0, 3, 4, 6, 10, 13, 14};
        System.out.println(binarySearch(arr, 18));
    }

    private static int binarySearch(int[] arr, int val) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        if (arr[0] > val || arr[arr.length - 1] < val) {
            return -1;
        }
        int low = 0;
        int high = arr.length;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (val == arr[mid]) {
                return mid;
            } else if (val < arr[mid]) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }

    /**
     * 递归
     * @param arr
     * @param key
     * @param low
     * @param high
     * @return
     */
    private static int binarySearch(int[] arr, int key, int low, int high) {
        while (low <= high) {
            int mid = (low + high) / 2;
            if (key == arr[mid]) {
                return mid;
            } else if (key < arr[mid]) {
                return binarySearch(arr, key, low, mid - 1);
            } else {
                return binarySearch(arr, key, mid + 1, high);
            }
        }
        return -1;
    }
}
