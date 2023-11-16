package arithmetic.top100;

import java.util.Stack;
import java.util.stream.IntStream;

/**
 * 用两个栈实现队列
 */
public class StackQueue<T> {
    /**
     * 栈出口
     */
    private Stack<T> out = new Stack<>();
    /**
     *
     */
    private Stack<T> stack = new Stack<>();

    private int queueSize;

    public StackQueue(int queueSize) {
        this.queueSize = queueSize;
    }

    public StackQueue() {
        this.queueSize = 10;
    }

    public T pop() {
        return out.size() > 0 ? out.pop() : null;
    }
    public boolean isNotEmpty(){
        return !out.isEmpty();
    }

    public boolean put(T v) {
        if (queueSize > out.size()) {
            while (out.size() > 0) {
                stack.push(out.pop());
            }
            stack.push(v);
            while (stack.size() > 0) {
                out.push(stack.pop());
            }
            return true;
        }
        return false;
    }

    public static void main(String[] args) {
        StackQueue<Integer> stackQueue = new StackQueue<>();
        IntStream.range(1,10).forEach(stackQueue::put);
        while (stackQueue.isNotEmpty()) {
            System.out.println(stackQueue.pop());
        }
    }

}
