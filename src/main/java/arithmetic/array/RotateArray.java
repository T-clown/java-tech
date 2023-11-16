package arithmetic.array;

import com.alibaba.fastjson2.JSON;

/**
 * 一个数组A中存有 n 个整数，在不允许使用另外数组的前提下，将每个整数循环向右移 M（ M >=0）个位置，
 * 即将A中的数据由（A0 A1 ……AN-1 ）变换为（AN-M …… AN-1 A0 A1 ……AN-M-1 ）
 * （最后 M 个数循环移至最前面的 M 个位置）。如果需要考虑程序移动数据的次数尽量少，要如何设计移动的方法？
 */
public class RotateArray {

    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5, 6};
        System.out.println(JSON.toJSONString(solve(6, 4, arr)));
    }

    public static int[] solve(int n, int m, int[] a) {
        if (a == null || a.length < 2) {
            return a;
        }
        //旋转次数
        int rotateNum = m % n;
        if (rotateNum == 0) {
            //旋转次数为0或者为n的倍数，直接返回
            return a;
        }
        exchange(a, 0, n - 1);
        exchange(a, 0, rotateNum - 1);
        exchange(a, rotateNum, n - 1);
        return a;
    }

    private static void exchange(int[] array, int left, int right) {
        while (left < right) {
//            int temp = array[left];
//            array[left] = array[right];
//            array[right] = temp;
            array[left] += array[right];
            array[right] = array[left] - array[right];
            array[left] = array[left] - array[right];
            left++;
            right--;
        }
    }
}
