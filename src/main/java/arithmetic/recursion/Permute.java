package arithmetic.recursion;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 给出一组数字，返回该组数字的所有排列
 */
public class Permute {
    public static void main(String[] args) {
        int[] array = {1, 2, 3};
        System.out.println(JSON.toJSONString(permute(array)));
    }

    //交换位置函数
    private static void swap(ArrayList<Integer> num, int i1, int i2) {
        int temp = num.get(i1);
        num.set(i1, num.get(i2));
        num.set(i2, temp);
    }

    public static void recursion(ArrayList<ArrayList<Integer>> res, ArrayList<Integer> num, int index) {
        //分枝进入结尾，找到一种排列
        if (index == num.size() - 1) {
            res.add(num);
        } else {
            //遍历后续的元素
            for (int i = index; i < num.size(); i++) {
                //交换二者
                swap(num, i, index);
                //继续往后找
                recursion(res, num, index + 1);
                //回溯
                swap(num, i, index);
            }
        }
    }

    public static ArrayList<ArrayList<Integer>> permute(int[] num) {
        //先按字典序排序
        Arrays.sort(num);
        ArrayList<ArrayList<Integer>> res = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> nums = new ArrayList<Integer>();
        //数组转ArrayList
        for (int i = 0; i < num.length; i++) {
            nums.add(num[i]);
        }
        recursion(res, nums, 0);
        return res;
    }


    static ArrayList<ArrayList<Integer>> res;
    static ArrayList<Integer> path;
    static boolean[] used;

    public static ArrayList<ArrayList<Integer>> permute2(int[] num) {
        res = new ArrayList<>();
        path = new ArrayList<>();
        used = new boolean[num.length];
        dfs(num, 0);
        return res;
    }

    public static void dfs(int[] nums, int step) {
        if (step == nums.length) {
            //长度等于数组长度，加入集合
            res.add(new ArrayList<>(path));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (used[i]) {
                continue;
            }
            path.add(nums[i]);
            used[i] = true;
            dfs(nums, step + 1);
            used[i] = false;
            //回溯
            path.remove(path.size() - 1);
        }
    }
}
