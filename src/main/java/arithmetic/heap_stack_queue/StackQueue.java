package arithmetic.heap_stack_queue;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，使用n个元素来完成 n 次在队列尾部插入整数(push)和n次在队列头部删除整数(pop)的功能。
 * 队列中的元素为int类型。保证操作合法，即保证pop操作时队列内已有元素。
 */
public class StackQueue {
    //元素栈
    Stack<Integer> stack1 = new Stack<>();
    //辅助栈
    Stack<Integer> stack2 = new Stack<>();

    public void push(int node) {
        while (!stack1.isEmpty()) {
            stack2.push(stack1.pop());
        }
        stack2.push(node);
        while (!stack2.isEmpty()) {
            stack1.push(stack2.pop());
        }
    }

    public int pop() {
        return stack1.pop();
    }
}

