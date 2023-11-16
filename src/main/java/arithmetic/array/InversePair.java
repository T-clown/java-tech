package arithmetic.array;

/**
 * https://www.nowcoder.com/practice/96bd6684e04a44eb80e6a68efc0ec6c5?tpId=295&tqId=23260&ru=/exam/oj&qru=/ta/format-top101/question-ranking&sourceUrl=%2Fexam%2Foj%3FfromPut%3Dpc_kol_wsx
 * 在数组中的两个数字，如果前面一个数字大于后面的数字，则这两个数字组成一个逆序对。
 * 输入一个数组,求出这个数组中的逆序对的总数P。并将P对1000000007取模的结果输出。 即输出P mod 1000000007
 */
public class InversePair {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6, 7, 0};
        int[] arr2 = {1, 2, 3, 4, 5, 6, 7};
        System.out.println(inversePairs(arr));
    }

    /**
     * 方法一
     *
     * @param array
     * @return
     */
    private static int inversePairs(int[] array) {
        int kmod = 1000000007;
        int count = 0;
        int max = -1;
        for (int i = 0, length = array.length; i < length; i++) {
            max = Math.max(max, array[i]);
            if (array[i] >= max) {
                continue;
            }
            count += inversePairs(array, array[i], i);
        }
        return count % kmod;
    }

    private static int inversePairs(int[] array, int value, int index) {
        if (index == 0) {
            return 0;
        }
        int count = 0;
        for (int i = 0; i < index; i++) {
            if (array[i] > value) {
                count++;
            }
        }
        return count;
    }

    public int mod = 1000000007;

    public int mergeSort(int left, int right, int[] array, int[] temp) {
        //停止划分
        if (left >= right) {
            return 0;
        }
        //取中间
        int mid = (left + right) / 2;
        //左右划分合并
        int res = mergeSort(left, mid, array, temp) + mergeSort(mid + 1, right, array, temp);
        //防止溢出
        res %= mod;
        for (int k = left; k <= right; k++) {
            temp[k] = array[k];
        }
        int i = left, j = mid + 1;
        for (int k = left; k <= right; k++) {
            if (i == mid + 1) {
                array[k] = temp[j++];
            } else if (j == right + 1 || temp[i] <= temp[j]) {
                array[k] = temp[i++];
            } else {
                //左边比右边大，答案增加
                array[k] = temp[j++];
                // 统计逆序对
                res += mid - i + 1;
            }
        }
        return res % mod;
    }

    /**
     * 方法二：归并
     *
     * @param array
     * @return
     */
    public int inversePairs2(int[] array) {
        int n = array.length;
        int[] temp = new int[n];
        return mergeSort(0, n - 1, array, temp);
    }
}
