package arithmetic.hash;

import java.util.HashMap;

/**
 * 给出一个整型数组 numbers 和一个目标值 target，请在数组中找出两个加起来等于目标值的数的下标，返回的下标按升序排列。
 * （注：返回的数组下标从1开始算起，保证target一定可以由数组里面2个数字相加得到）
 */
public class TwoSum {
    public static void main(String[] args) {
        System.out.println(1|2|2);
    }

    public static int[] twoSum(int[] numbers, int target) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int[] array = new int[2];
        for (int i = 0; i < numbers.length; i++) {
            int value = numbers[i];
            if (map.containsKey(value)) {
                int idx = map.get(value);
                array[0] = idx + 1;
                array[1] = i + 1;
                return array;
            } else {
                map.put(target - value, i);
            }
        }
        return array;
    }
}
