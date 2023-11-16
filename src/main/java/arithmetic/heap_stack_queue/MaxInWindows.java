package arithmetic.heap_stack_queue;

import java.util.ArrayList;
import java.util.Stack;

/**
 * 给定一个长度为 n 的数组 num 和滑动窗口的大小 size ，找出所有滑动窗口里数值的最大值
 */
public class MaxInWindows {


    public ArrayList<Integer> maxInWindows(int[] num, int size) {
        if (num == null || size > num.length || size == 0) {
            return null;
        }
        ArrayList<Integer> list = new ArrayList<>();
        Stack<Integer> maxStack = new Stack<>();
        int index = 0;
        for (int i = 0; i < size; i++) {
            int value = num[i];
            if (maxStack.isEmpty() || maxStack.peek() < value) {
                maxStack.push(value);
            } else {
                maxStack.push(maxStack.peek());
            }
        }
        return list;
    }
}
