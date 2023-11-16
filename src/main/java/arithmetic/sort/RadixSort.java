package arithmetic.sort;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 10. 基数排序
 * 桶排序的扩展
 * 基数排序是一种非比较型整数排序算法，其原理是将数据按位数切割成不同的数字，然后按每个位数分别比较
 * 场景：对 100 万个手机号码进行排序
 */
public class RadixSort {
    public int[] sort(int[] sourceArray) throws Exception {
        // 对 arr 进行拷贝，不改变参数内容
        int[] arr = Arrays.copyOf(sourceArray, sourceArray.length);

        int maxDigit = getMaxDigit(arr);
        return radixSort(arr, maxDigit);
    }

    /**
     * 获取最高位数
     */
    private int getMaxDigit(int[] arr) {
        int maxValue = getMaxValue(arr);
        return getNumLenght(maxValue);
    }

    private int getMaxValue(int[] arr) {
        int maxValue = arr[0];
        for (int value : arr) {
            if (maxValue < value) {
                maxValue = value;
            }
        }
        return maxValue;
    }

    protected int getNumLenght(long num) {
        if (num == 0) {
            return 1;
        }
        int lenght = 0;
        for (long temp = num; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    private int[] radixSort(int[] arr, int maxDigit) {
        int mod = 10;
        int dev = 1;

        for (int i = 0; i < maxDigit; i++, dev *= 10, mod *= 10) {
            // 考虑负数的情况，这里扩展一倍队列数，其中 [0-9]对应负数，[10-19]对应正数 (bucket + 10)
            int[][] counter = new int[mod * 2][0];

            for (int j = 0; j < arr.length; j++) {
                int bucket = ((arr[j] % mod) / dev) + mod;
                counter[bucket] = arrayAppend(counter[bucket], arr[j]);
            }

            int pos = 0;
            for (int[] bucket : counter) {
                for (int value : bucket) {
                    arr[pos++] = value;
                }
            }
        }

        return arr;
    }

    /**
     * 自动扩容，并保存数据
     *
     * @param arr
     * @param value
     */
    private int[] arrayAppend(int[] arr, int value) {
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[arr.length - 1] = value;
        return arr;
    }


    public static void sort2(int[] arr) {
        int length = arr.length;

        //最大值
        int max = arr[0];
        for (int i = 0; i < length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }


        //桶列表
        ArrayList<ArrayList<Integer>> bucketList = new ArrayList<>();

        //长度为10 装入余数0-9的数据
        for (int i = 0; i < 10; i++) {
            bucketList.add(new ArrayList());
        }
        //当前排序位置
        int location = 1;
        while (true) {
            //判断是否排完
            int dd = (int) Math.pow(10, (location - 1));
            if (max < dd) {
                break;
            }

            //数据入桶
            for (int i = 0; i < length; i++) {
                //计算余数 放入相应的桶
                int number = ((arr[i] / dd) % 10);
                bucketList.get(number).add(arr[i]);
            }

            //写回数组
            int nn = 0;
            for (int i = 0; i < 10; i++) {
                int size = bucketList.get(i).size();
                for (int ii = 0; ii < size; ii++) {
                    arr[nn++] = bucketList.get(i).get(ii);
                }
                bucketList.get(i).clear();
            }
            location++;
        }
    }
}
