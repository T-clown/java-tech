package arithmetic;

import com.alibaba.fastjson.JSON;

/**
 * 输入一个整型数组，数组里有正数也有负数。数组中一个或连续的多个整数组成一个子数组。
 * 求所有子数组的和的最大值。要求时间复杂度为O(n)
 * 例如输入的数组为{1, -2, 3, 10, -4, 7, 2, -5}，和最大的子数组为3, 10, -4, 7, 2}。因此输出为该子数组的和18
 */
public class GreatestSumOfSubArray {
    public static void main(String[] args) {
        //int[] arr = {1, -2, 3, 10, -4, 7, 2, -5};
        int[] arr = {-1, -2, -3, -10, -4, 0, -2, -5};
        //System.out.println(solution1(arr));

        System.out.println(solution2(arr));

        System.out.println(solution3(arr));
    }

    private static int solution1(int[] arr) {
        int max = arr[0];
        int sum;
        int startIndex = 0;
        int endIndex = 0;
        boolean update = false;
        for (int i = 0; i < arr.length; i++) {
            sum = arr[i];
            // max = max < sum ? sum : max;
            if (max < sum) {
                max = sum;
                startIndex = i;
            }
            for (int j = i + 1; j < arr.length; j++) {
                sum += arr[j];
                // max = max < sum ? sum : max;
                if (max < sum) {
                    update = true;
                    max = sum;
                    endIndex = j;
                }
            }
            if (update) {
                startIndex = i;
                update = false;
            }
        }
        System.out.println(startIndex + "\t" + endIndex);
        return max;
    }

    public static int solution2(int[] arr) {
        if (arr == null || arr.length < 1) {
            throw new IllegalArgumentException("Array must contain an element");
        }
        int curSum = 0;
        int maxSum = arr[0];
        for (int i = 0; i < arr.length; i++) {
            curSum = (arr[i] > curSum + arr[i]) ? arr[i] : curSum + arr[i];
            maxSum = (maxSum > curSum) ? maxSum : curSum;
        }
        return maxSum;
    }

    public static int solution3(int[] array) {

        int currentsum = array[0];
        int greatsetsum = array[0];
        for (int i = 1; i < array.length; i++) {
            if (currentsum > 0) {
                currentsum += array[i];
            } else {
                currentsum = array[i];
            }
            if (currentsum > greatsetsum) {
                greatsetsum = currentsum;
            }
        }
        return greatsetsum;
    }

    public static int solution4(int[] array) {
        int len = array.length;
        if (len == 0) {
            return 0;
        }
        int[] currentsum = new int[len];
        currentsum[0] = array[0];
        int greatsetsum = array[0];
        System.out.println("第1步：累加子数组和：" + currentsum[0] + "，最大子数组和：" + greatsetsum);
        for (int i = 1; i < array.length; i++) {
            //下面是动态规划的状态转移方程
            if (currentsum[i - 1] > 0) {
                currentsum[i] = currentsum[i - 1] + array[i];
            } else {
                currentsum[i] = array[i];
            }
            //根据currentsum的值更新greatsetsum的值
            if (currentsum[i] > greatsetsum) {
                greatsetsum = currentsum[i];
            }
            System.out.println("第" + (i + 1) + "步：累加子数组和：" + currentsum[i] + "，最大子数组和：" + greatsetsum);
        }
        return greatsetsum;
    }

}
