package arithmetic.array;

/**
 * 给定一个长度为n的数组nums，请你找到峰值并返回其索引。数组可能包含多个峰值，在这种情况下，返回任何一个所在位置即可。
 * 1.峰值元素是指其值严格大于左右相邻值的元素。严格大于即不能有等于
 * 2.假设 nums[-1] = nums[n] =−∞
 * 3.对于所有有效的 i 都有 nums[i] != nums[i + 1]
 */
public class FindPeak {
    public static void main(String[] args) {
        int[] arr = {2, 4, 1, 2, 7, 8, 4};
        System.out.println(findPeak(arr));
    }

    private static int findPeak(int[] array) {
        if (array.length == 1) {
            return 0;
        }
        int left = 0;
        int right = array.length;
        int mid;
        while (left < right) {
            mid = (left + right) / 2;
            if (array[mid] > array[mid + 1]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return right;
    }
}
