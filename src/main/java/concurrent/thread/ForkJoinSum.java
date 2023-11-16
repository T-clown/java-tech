package concurrent.thread;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

public class ForkJoinSum extends RecursiveTask<Integer> {
    /**
     * 阈值，决定何时停止划分任务
     */
    private static final int THRESHOLD = 10;
    private int[] array;
    private int start;
    private int end;

    public ForkJoinSum(int[] array, int start, int end) {
        this.array = array;
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        if (end - start <= THRESHOLD) {
            // 如果任务足够小，直接计算结果
            int sum = 0;
            for (int i = start; i < end; i++) {
                sum += array[i];
            }
            return sum;
        } else {
            // 否则将任务划分成更小的子任务
            int mid = (start + end) / 2;
            ForkJoinSum leftSum = new ForkJoinSum(array, start, mid);
            ForkJoinSum rightSum = new ForkJoinSum(array, mid, end);
            // 异步执行左子任务
            leftSum.fork();
            // 同步执行右子任务
            int rightResult = rightSum.compute();
            // 获取左子任务的结果
            int leftResult = leftSum.join();
            return leftResult + rightResult;
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        ForkJoinSum task = new ForkJoinSum(array, 0, array.length);
        int result = forkJoinPool.invoke(task);
        System.out.println("Sum: " + result);
    }
}

