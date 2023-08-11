package concurrent.thread;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ScheduledThreadPoolExecutor的延迟原理基于一个DelayQueue和ThreadPoolExecutor的组合实现
 */
@Slf4j
public class ScheduledThreadPoolExecutorApp {
    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(5, new ThreadFactory() {

        private final AtomicInteger threadNumber = new AtomicInteger(1);
        private static final String NAME_PREFIX = "Scheduled-Thread-";

        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(Thread.currentThread().getThreadGroup(), r,
                    NAME_PREFIX + threadNumber.getAndIncrement(), 0);
            //是否守护进程
            if (t.isDaemon()) {
                t.setDaemon(false);
            }
            if (t.getPriority() != Thread.NORM_PRIORITY) {
                t.setPriority(Thread.NORM_PRIORITY);
            }
            return t;
        }
    }, new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        //scheduleAtFixedRate();
        scheduleWithFixedDelay();
    }

    private static void schedule() throws ExecutionException, InterruptedException {
        executor.execute(() -> log.info("直接执行"));

        //从当前时间开始，延迟5秒后执行，无返回值
        ScheduledFuture<?> scheduleRunnable = executor.schedule(() -> log.info("scheduleRunnable延迟执行"), 5, TimeUnit.SECONDS);
        log.info("scheduleRunnable： {}", scheduleRunnable.get());

        //从当前时间开始，延迟5秒后执行，有返回值
        ScheduledFuture<String> scheduleCallable = executor.schedule(() -> {
            log.info("scheduleCallable延迟执行");
            return "scheduleCallable执行完成";
        }, 5, TimeUnit.SECONDS);
        log.info("scheduleCallable： {}", scheduleCallable.get());

        //schedule(task, 0, NANOSECONDS)
        Future<String> submitCallable = executor.submit(() -> {
            log.info("submitCallable执行");
            return "submitCallable执行完毕";
        });
        log.info("submitCallable: {}", submitCallable.get());

        Future<?> submitRunnable = executor.submit(() -> log.info("submitRunnable执行"));
        log.info("submitRunnable: {}", submitRunnable.get());

        Future<String> submitResult = executor.submit(() -> log.info("submitResult执行"), "result");
        log.info("submitResult: {}", submitResult.get());
    }

    private static void scheduleAtFixedRate() {
        //从当前时间开始，延迟5秒后开始执行，并按照每隔3秒钟执行一次
        //scheduleAtFixedRate是按照固定的时间间隔执行任务，不受任务执行时间的影响
        //当前时间开始执行任务，如果：任务执行时间>延迟时间，则任务执行完毕后会立马执行下一次任务，因为延迟时间是从任务开始执行的那一刻开始算的
        executor.scheduleAtFixedRate(() -> {
            log.info("scheduleAtFixedRate执行:{}", LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, 3, TimeUnit.SECONDS);
    }

    private static void scheduleWithFixedDelay() {
        //从当前时间开始，延迟5秒后开始执行，每次需要等执行完后再开始下一次的延迟时间
        //scheduleWithFixedDelay是根据实际的任务执行时间来计算下一次任务的执行时间，保证任务之间的固定间隔
        //如果当前时间是0，延迟时间是3，任务执行时间是5，则下次执行时间是8，即下次执行时间=当前任务执行时间+延迟时间，因为延迟时间是从任务执行完成后开始算的
        executor.scheduleWithFixedDelay(() -> {
            log.info("scheduleWithFixedDelay执行:{}", LocalDateTime.now());
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, 5, 3, TimeUnit.SECONDS);
    }

}
