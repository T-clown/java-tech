package arithmetic.double_pointer;

import com.alibaba.fastjson.JSON;

/**
 * 给出一个有序的整数数组 A 和有序的整数数组 B ，请将数组 B 合并到数组 A 中，变成一个有序的升序数组
 * 1.保证 A 数组有足够的空间存放 B 数组的元素， A 和 B 中初始的元素数目分别为 m 和 n，A的数组空间大小为 m+n
 * 2.不要返回合并的数组，将数组 B 的数据合并到 A 里面就好了，且后台会自动将合并后的数组 A 的内容打印出来，所以也不需要自己打印
 * 3. A 数组在[0,m-1]的范围也是有序的
 */
public class MergeArray {
    public static void main(String[] args) {
        int[] a = {1, 2, 3, 0, 0, 0};
        int[] b = {2, 5, 6};
        //merge(a, 3, b, 3);
        merge2(a, 3, b, 3);
        System.out.println(JSON.toJSONString(a));
    }

    private static void merge2(int A[], int m, int B[], int n) {
        int t1 = m - 1;
        int t2 = n - 1;
        //从A数组的尾部开始插入，依次从大到小
        for (int t = m + n - 1; t1 >= 0 && t2 >= 0; t--) {
            A[t] = A[t1] > B[t2] ? A[t1--] : B[t2--];
        }
        //当数组t2>=0,则把剩余的依次插入A数组的头部
        while (t2 >= 0) {
            A[t2] = B[t2--];
        }
    }

    public static void merge3(int A[], int m, int B[], int n) {
        int a = m - 1;
        int b = n - 1;
        //需要填m+n次
        for (int i = m + n - 1; i >= 0; i--) {
            if (b < 0 || (a >= 0 && A[a] >= B[b])) {
                //B数组中的数全部用完了就填A数组中的数 a数组中的数没有用完，并且A数组的数大
                A[i] = A[a--];
            } else {
                A[i] = B[b--];
            }
        }
    }

    public static void merge(int A[], int m, int B[], int n) {
        int idx = 0;
        int lastIdx = m;
        for (int i = 0; i < m + n; i++) {
            int v1 = A[i];
            if (i >= lastIdx && idx < n) {
                A[i] = B[idx];
                idx++;
                continue;
            }
            if (idx < n) {
                int v2 = B[idx];
                if (v2 <= v1) {
                    //查入v2
                    backValue(A, v2, i);
                    idx++;
                    lastIdx++;
                }
            }
        }
    }

    private static void backValue(int[] array, int value, int idx) {
        //把从idx开始的元素都往后移动一位
        for (int i = idx; i < array.length; i++) {
            int temp = array[i];
            array[i] = value;
            value = temp;
        }
    }
}
