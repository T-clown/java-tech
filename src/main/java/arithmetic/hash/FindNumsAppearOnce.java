package arithmetic.hash;

import com.alibaba.fastjson.JSON;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 一个整型数组里除了两个数字只出现一次，其他的数字都出现了两次。请写程序找出这两个只出现一次的数字。
 */
public class FindNumsAppearOnce {
    public static void main(String[] args) {
        int[] array = {1, 4, 1, 6};
        System.out.println(JSON.toJSONString(findNumsAppearOnce2(array)));
    }


    /**
     * 异或运算满足交换率，且相同的数字作异或会被抵消掉
     * 且任何数字与0异或还是原数字
     *
     * @param array
     * @return
     */
    public static int[] findNumsAppearOnce2(int[] array) {
        int res1 = 0;
        int res2 = 0;
        int temp = 0;
        //遍历数组得到a^b
        for (int i = 0; i < array.length; i++) {
            temp ^= array[i];
        }
        int k = 1;
        //找到两个数不相同的第一位
        while ((k & temp) == 0) {
            k <<= 1;
        }
        for (int i = 0; i < array.length; i++) {
            //遍历数组，对每个数分类
            if ((k & array[i]) == 0) {
                res1 ^= array[i];
            } else {
                res2 ^= array[i];
            }
        }
        //整理次序
        if (res1 < res2) {
            return new int[]{res1, res2};
        } else {
            return new int[]{res2, res1};
        }
    }

    public static int[] findNumsAppearOnce(int[] array) {
        HashMap<Integer, Integer> countMap = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            int value = array[i];
            if (countMap.containsKey(value)) {
                countMap.put(value, countMap.get(value) + 1);
            } else {
                countMap.put(value, countMap.get(value));
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            if (countMap.get(array[i]) == 1) {
                list.add(array[i]);
            }
        }
        return list.get(0) > list.get(1) ? new int[]{list.get(1), list.get(0)} : new int[]{list.get(0), list.get(1)};
    }
}
