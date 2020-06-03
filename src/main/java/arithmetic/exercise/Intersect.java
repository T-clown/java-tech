package arithmetic.exercise;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.alibaba.fastjson.JSON;

import arithmetic.sort.common.BubbleSort;
import com.google.common.primitives.Ints;

/**
 * 给定两个数组，编写一个函数来计算它们的交集。
 *
 * @author Administrator
 */
public class Intersect {

    public static void main(String[] args) {
        int[] A = {1, 23, 4, 56, 7, 88, 10, 123, 88, 6};
        int[] B = {11, 88, 88, 6, 2};
        int[] com = solutionThree(BubbleSort.sort(B), BubbleSort.sort(A));
        System.out.println(JSON.toJSONString(com));
    }

    private static int[] get(int[] nums1, int[] nums2) {
        List<Integer> a = IntStream.of(nums1.length >= nums2.length ? nums2 : nums1).boxed().sorted().collect(
            Collectors.toList());
        List<Integer> b = IntStream.of(nums1.length <= nums2.length ? nums2 : nums1).boxed().sorted().collect(
            Collectors.toList());
        List<Integer> indexs = new ArrayList<>();
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < a.size(); i++) {
            for (int j = 0; j < b.size(); j++) {
                if (a.get(i) == b.get(j) && !indexs.contains(j)) {
                    result.add(a.get(i));
                    indexs.add(j);
                    break;
                }
            }
        }
        int[] num = new int[result.size()];
        for (int i = 0; i < result.size(); i++) {
            num[i] = result.get(i);
        }
        return num;
    }

    /**
     * 方法二：对其中一个数组排序，采用二分查找法
     * 需要记录索引
     *
     * @param A
     * @param B
     * @return
     */
    private static int[] solutionTwo(int[] A, int[] B) {
        return null;
    }

    /**
     * 方法三：对两个数组排序，采用顺序查找法
     *
     * @param A
     * @param B
     * @return
     */
    private static int[] solutionThree(int[] A, int[] B) {
        int index = 0;
        //A<B
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            int target = A[i];
            for (int j = index; j < B.length; j++) {
                if (target == B[j]) {
                    //添加
                    result.add(target);
                    break;
                } else if (target < B[j]) {
                    index = j - 1;
                    break;
                }
            }
        }
        return Ints.toArray(result);
    }

    /**
     * 方法一：顺序查找
     *
     * @param A
     * @param B
     * @return
     */
    private static int[] solutionOne(int[] A, int[] B) {
        int[] com = new int[0];
        int index = 0;
        List<Integer> indexs = new ArrayList<>();
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < B.length; j++) {
                if (A[i] == B[j] && !indexs.contains(j)) {
                    com[index] = A[i];
                    index++;
                    indexs.add(j);
                }
            }
        }
        return com;
    }
}
