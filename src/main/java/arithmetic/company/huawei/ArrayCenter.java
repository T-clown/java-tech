package arithmetic.company.huawei;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 给你一个整数数组nums，请计算数组的中心位置。数组的中心位置是数组的一个下标， 其左侧所有元素相乘的积等于右侧所有元素相乘的积。数组第一个元素的左侧积为1，最后一个元素的右侧积为1。 如果数组有多个中心位置，应该返回最靠近左边的那一个，如果数组不存在中心位置，返回-1。
 * <p>
 * 输入
 * 2 5 3 6 5 6
 * 输出
 * 3
 * 解释：2*5*3=5*6
 */
public class ArrayCenter {


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        System.out.println(s);

        int[] arr = {2, 5, 3, 6, 5, 6};
        int[] arr2 = {2};
        int[] arr3 = {1, 2};
        System.out.println(arrayCenter(arr));
        System.out.println(arrayCenter(arr2));
        System.out.println(arrayCenter(arr3));
    }


    private static int arrayCenter(int[] arr) {
        if (arr == null || arr.length == 0) {
            return -1;
        }
        int total = 1;
        for (int i = 0; i < arr.length; i++) {
            total *= arr[i];
        }
        int left = 1;
        int right = total;
        for (int i = 0; i < arr.length; i++) {
            int cur = arr[i];
            right /= cur;
            if (left == right) {
                return i;
            }
            left *= cur;
        }
        return -1;
    }

}
