package arithmetic.double_pointer;

/**
 * 给定一个整形数组arr，已知其中所有的值都是非负的，将这个数组看作一个柱子高度图，
 * 计算按此排列的柱子，下雨之后能接多少雨水。(数组以外的区域高度视为0)
 */
public class MaxWater {
    public static void main(String[] args) {
        int[] arr = {3, 1, 2, 5, 2, 4, 2, 3};
        int[] arr2 = {4, 5, 1, 3, 2};
        int[] arr3 = {0, 1, 0, 2, 1, 0, 1, 3, 2, 1, 2, 1};
//        System.out.println(maxWater(arr));
//        System.out.println(maxWater(arr2));
        System.out.println(maxWater(arr));
    }

    public static long maxWater(int[] arr) {
        if (arr == null || arr.length < 3) {
            return 0;
        }
        int sumWater = 0;
        int startIdx = 0;
        int endIdx = 1;
        for (int i = 1, len = arr.length; i < len; i++) {
            int end = arr[i];
            if (arr[endIdx] <= end) {
                endIdx = i;
            }
            if (arr[endIdx] >= arr[startIdx] || i == (len - 1)) {
                //占用空间数
                int sum = sum(arr, startIdx + 1, endIdx);
                //当后面的最大值大于左边，则开始计算雨量
                sumWater += ((endIdx - startIdx - 1) * Math.min(arr[startIdx], arr[endIdx]) - sum);
                if (i == (len - 1) && i != endIdx) {
                    //回溯，防止后面有凹槽但是比maxVal小的情况
                    startIdx = endIdx;
                    i = endIdx + 1;
                    endIdx = i;
                } else {
                    if (i < (len - 1)) {
                        //防止索引越界
                        startIdx = endIdx;
                        endIdx = i + 1;
                    }
                }
            }
        }
        return sumWater;
    }

    private static int sum(int[] arr, int start, int end) {
        int sum = 0;
        while (start < end) {
            sum += arr[start++];
        }
        return sum;
    }

    /**
     * 官方答案
     * 1.准备双指针，分别指向数组首尾元素，代表最初的两个边界
     * 2.指针往中间遍历，遇到更低柱子就是底，用较短的边界减去底就是这一列的接水量，遇到更高的柱子就是新的边界，更新边界大小。
     *
     * @param arr
     * @return
     */
    public static long maxWater2(int[] arr) {
        //排除空数组
        if (arr.length == 0) {
            return 0;
        }
        long res = 0;
        //左右双指针
        int left = 0;
        int right = arr.length - 1;
        //中间区域的边界高度
        int maxL = 0;
        int maxR = 0;
        //直到左右指针相遇
        while (left < right) {
            //每次维护往中间的最大边界
            maxL = Math.max(maxL, arr[left]);
            maxR = Math.max(maxR, arr[right]);
            //较短的边界确定该格子的水量
            if (maxR > maxL) {
                res += maxL - arr[left++];
            } else {
                res += maxR - arr[right--];
            }
        }
        return res;
    }
}
