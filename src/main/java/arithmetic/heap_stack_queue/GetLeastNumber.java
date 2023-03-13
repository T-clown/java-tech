package arithmetic.heap_stack_queue;

import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.Stack;

/**
 * 给定一个长度为 n 的可能有重复值的数组，找出其中不去重的最小的 k 个数。
 * 例如数组元素是4,5,1,6,2,7,3,8这8个数字，则最小的4个数字是1,2,3,4(任意顺序皆可)。
 */
public class GetLeastNumber {

    public ArrayList<Integer> getLeastNumber(int[] input, int k) {
        ArrayList<Integer> res = new ArrayList<Integer>();
        //排除特殊情况
        if (k == 0 || input.length == 0)
            return res;
        //大根堆
        PriorityQueue<Integer> q = new PriorityQueue<>((o1, o2) -> o2.compareTo(o1));
        //构建一个k个大小的堆
        for (int i = 0; i < k; i++)
            q.offer(input[i]);
        for (int i = k; i < input.length; i++) {
            //较小元素入堆
            if (q.peek() > input[i]) {
                q.poll();
                q.offer(input[i]);
            }
        }
        //堆中元素取出入数组
        for (int i = 0; i < k; i++) {
            res.add(q.poll());
        }
        return res;
    }
}
