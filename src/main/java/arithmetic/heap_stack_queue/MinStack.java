package arithmetic.heap_stack_queue;

import java.util.Stack;

/**
 * 定义栈的数据结构，请在该类型中实现一个能够得到栈中所含最小元素的 min 函数，
 * 输入操作时保证 pop、top 和 min 函数操作时，栈中一定有元素。
 */
public class MinStack {
    //用于栈的push 与 pop
    Stack<Integer> s1 = new Stack<>();
    //用于存储最小min
    Stack<Integer> s2 = new Stack<>();

    public void push(int node) {
        s1.push(node);
        //空或者新元素较小，则入栈
        if (s2.isEmpty() || s2.peek() > node) {
            s2.push(node);
        } else {
            //重复加入栈顶
            s2.push(s2.peek());
        }
    }

    public void pop() {
        s1.pop();
        s2.pop();
    }

    public int top() {
        return s1.peek();
    }

    public int min() {
        return s2.peek();
    }
}
