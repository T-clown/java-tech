package arithmetic.hash;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;

/**
 * 给出一个有n个元素的数组S，S中是否有元素a,b,c满足a+b+c=0？找出数组S中所有满足条件的三元组。
 */
public class ThreeSum {
    public static void main(String[] args) {
        int[] arr = {-40, -10, -10, 0, 10, 20};
        ArrayList<ArrayList<Integer>> list = threeSum(arr);
        System.out.println(JSON.toJSONString(list));
    }


    public static ArrayList<ArrayList<Integer>> threeSum(int[] num) {
        ArrayList<ArrayList<Integer>> res = new ArrayList<>();
        if (num == null || num.length < 3) {
            return res;
        }
        int left = 0;
        int right = num.length - 1;
        if (num[left] + num[right] > 0) {
            return res;
        }
        while (left < right) {
            int min = num[left];
            int max = num[right];
            int sum = min + max;
            if (sum >= 0) {
                //从左向右遍历
                for (int i = left; i < right; i++) {
                    if (sum + num[i] > 0) {
                        right--;
                        break;
                    }
                    if (sum + num[i] == 0) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(min);
                        list.add(num[i]);
                        list.add(max);
                        res.add(list);
                        right--;
                    }
                }
            }
            if (sum < 0) {
                //从右向左遍历
                for (int i = right - 1; i > left; i--) {
                    if (sum + num[i] < 0) {
                        left++;
                        break;
                    }
                    if (sum + num[i] == 0) {
                        ArrayList<Integer> list = new ArrayList<>();
                        list.add(min);
                        list.add(num[i]);
                        list.add(max);
                        res.add(list);
                        left++;
                    }
                }
            }
        }
        return res;
    }
}
