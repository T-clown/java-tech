package concurrent;

import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.util.AssertionErrors.fail;

/**
 * 异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务
 * https://mp.weixin.qq.com/s/PfYh5x1JuU1SSKI3x0_L0Q
 * https://mp.weixin.qq.com/s/3mcg5V6hSFDefJuD_mgDqw
 */
@Slf4j
public class CompletableFutureApp {
    static ExecutorService executor = Executors.newFixedThreadPool(5, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    public static void construct() {
        /**
         * 1.有返回值的异步
         * 使用 ForkJoinPool.commonPool() 作为它的线程池执行异步代码，异步操作有返回值
         */
        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("有返回值异步操作，睡眠{}秒,线程:{}", seconds, Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
            return "有返回值异步操作";
        });
        /**
         * 使用指定的 thread pool 执行异步代码，异步操作有返回值
         */
        CompletableFuture<String> supplyAsyncExecutor = CompletableFuture.supplyAsync(() -> {
            log.info("指定线程池有返回值异步操作");
            return "指定线程池有返回值异步操作";
        }, executor);

        /**
         * 2.无返回值的异步
         * 使用 ForkJoinPool.commonPool() 作为它的线程池执行异步代码
         */
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("无返回值异步操作：睡眠{}秒，线程：{}", seconds, Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
        });
        /**
         * 使用指定的 thread pool 执行异步代码
         */
        CompletableFuture<Void> runAsyncExecutor = CompletableFuture.runAsync(() -> {
            log.info("指定线程池无返回值异步操作");
        }, executor);
        /**
         * 明确返回值的CompletableFuture
         */
        CompletableFuture<String> completedFuture = CompletableFuture.completedFuture("completedFuture");
        /**
         * 延迟执行CompletableFuture，内部还是用ScheduledThreadPoolExecutor实现的
         */
        CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS).execute(() -> log.info("延迟10秒执行"));
        CompletableFuture.delayedExecutor(10, TimeUnit.SECONDS, executor).execute(() -> log.info("延迟10秒执行"));

    }

    public static void construct2() {
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("无返回值异步操作：睡眠{}秒，线程:{}", seconds, Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
        }, executor);

        CompletableFuture<Void> runAsyncExecutor = CompletableFuture.runAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("无返回值异步操作：睡眠{}秒，线程:{}", seconds, Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
        }, executor);

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("有返回值异步操作，睡眠{}秒，线程:{}", seconds, Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
            return "有返回值异步操作";
        }, executor);

        CompletableFuture<String> supplyAsyncExecutor = CompletableFuture.supplyAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("有返回值异步操作，睡眠{}秒，线程:{}", seconds, Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
            return "有返回值异步操作";
        }, executor);

        /**
         * 多个CompletableFuture并行
         */
        List<CompletableFuture<Void>> futures1 = Arrays.asList(runAsync, runAsyncExecutor);
        List<CompletableFuture<String>> futures = Arrays.asList(supplyAsync, supplyAsyncExecutor);
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        //等待所有CompletableFuture执行完成，阻塞
        allOf.join();
        futures.forEach(x -> System.out.println(x.getNow("没有值")));

        /**
         * 返回任何一个最先执行完成的结果
         */
        CompletableFuture<Object> anyOf = CompletableFuture.anyOf(supplyAsync, supplyAsyncExecutor);
        anyOf.join();
    }


    /**
     * complete：手动完成异步执行，并返回 future 的结果
     * completeExceptionally：手动完成一个CompletableFuture并将其标记为异常完成状态
     */
    public static void complete() {
//        Map<Integer, BigDecimal> map = new HashMap<>();
//        map.merge(1, BigDecimal.ONE, BigDecimal::add);
//        map.merge(1, BigDecimal.ONE, BigDecimal::add);
        CompletableFuture<String> future = CompletableFuture.supplyAsync(() -> {
            sleepRandomSecond();
            return "完成";
        });
        future.join();
        boolean complete = future.complete("手动完成，返回默认值");

        log.info("手动完成:{}，结果:{}", complete, future.getNow(null));

        CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
                CompletableFuture.delayedExecutor(5, TimeUnit.SECONDS));
        //手动完成，并标记未异常状态
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        try {
            log.info("异常完成:{}", cf.isCompletedExceptionally());
            //log.info("结果:{}", cf.getNow(null));
            cf.join();
        } catch (CompletionException ex) {
            log.error("completed exceptionally:{}", ex.getMessage());
        }
    }


    /**
     * whenComplete 计算结果完成时的处理
     * 可以看到 Action 的类型是 BiConsumer<? super T, ? super Throwable> 它可以处理正常的计算结果，或者异常情况。
     * <p>
     * exceptionally 方法返回一个新的 CompletableFuture，当原始的 CompletableFuture 抛出异常的时候，就会触发这个 CompletableFuture 的计算，调用 function 计算值，也就是这个 exceptionally 方法用来处理异常的情况。
     * <p>
     * thenApply与 handle 方法的区别在于 handle 方法会处理正常计算值和异常，因此它可以屏蔽异常，避免异常继续抛出。而 thenApply 方法只是用来处理正常值，因此一旦有异常就会抛出。
     * <p>
     * handle 方法主要作用：运行完成时，对结果的处理
     * 一组 handle 方法也可用于处理计算结果。当原先的 CompletableFuture 的值计算完成或者抛出异常的时候，会触发这个 CompletableFuture 对象的计算，结果由 BiFunction 参数计算而得。因此这组方法兼有 whenComplete 和转换的两个功能。
     */
    private static void exception() {
        CompletableFuture<Integer> f = CompletableFuture
                .supplyAsync(() -> 1 / 0)
                //超时抛出 TimeoutException
                .orTimeout(1, TimeUnit.SECONDS)
                //超时返回默认值1
                .completeOnTimeout(1, 3, TimeUnit.SECONDS)
                //.supplyAsync(() -> 1)
                //只能处理正常结果
                .thenApply(i -> i * 10)
                // 类似于catch{}，专门处理异常情况
                .exceptionally(e -> {
                    log.info("发现异常:{}", e.getMessage());
                    return 1;
                })
                //既能处理正常结果，又能处理异常情况，有返回结果
                .handle((r, e) -> {
                    if (e != null) {
                        log.error("handle发现异常，返回0：{}", e.getMessage());
                        return 0;
                    }
                    log.info("handle执行结果:{}", r);
                    return r * 3;
                })
                //既能处理正常结果，又能处理异常情况，无返回结果
                .whenComplete((r, e) -> {
                    // 类似于finally，whenComplete不支持返回结果，handle支持返回结果
                    log.info("whenComplete执行结果:{}", r);
                    if (e != null) {
                        log.error("whenComplete异常:{}", e.getMessage());
                    }
                })
                .thenApply(x -> {
                    log.info("thenApply:{}", x);
                    return x * x;
                });
        f.join();
        log.info("执行结果:{}", f.getNow(null));
    }


    public static void main(String[] args) {
        //applyToEither();
        //acceptEither();
        //thenCompose();
        //exception();
        complete();
    }

    /**
     * thenAccept(同步)
     * thenAcceptAsync(异步)
     * 执行函数：Consumer
     * <p>
     * thenApply(同步)
     * thenApplyAsync(异步)
     * 执行函数：Function
     * <p>
     * thenRun同步)
     * thenRunAsync(异步)
     * 执行函数：Runnable
     */
    private static void thenAccept() {
        //无返回值
        CompletableFuture<Void> thenAccept = CompletableFuture.completedFuture("thenAccept message")
                .thenAccept(x -> {
                    int seconds = sleepRandomSecond();
                    log.info("thenAcceptAsync异步操作，参数[{}]，睡眠{}秒，线程:{}", x, seconds, Thread.currentThread().getName());
                });
        //有返回值
        CompletableFuture<String> thenApply = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenApply(x -> {
                    int seconds = sleepRandomSecond();
                    log.info("thenAcceptAsync异步操作，参数[{}]，睡眠{}秒，线程:{}", x, seconds, Thread.currentThread().getName());
                    return "accept-" + x;
                });
        //等上一个CompletableFuture执行完毕后执行thenRun里的action函数，没用到前一个执行结果
        CompletableFuture<Void> thenRun = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenRun(() -> {
                    int seconds = sleepRandomSecond();
                    log.info("thenRun执行，睡眠{}秒，线程:{}", seconds, Thread.currentThread().getName());
                });
        thenAccept.join();
        thenApply.join();
        thenRun.join();
        log.info("执行完毕:{}", thenApply.getNow(null));
    }

    /**
     * 将两个CompletableFuture中的任意一个完成后的结果应用给指定的函数
     * applyToEither
     * applyToEitherAsync
     * 结果处理函数为Function，即有返回值
     */
    private static void applyToEither() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    return x.toUpperCase();
                });

        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    return x.toLowerCase();
                });
        //将两个CompletableFuture中的任意一个完成后的结果应用给指定的函数
        CompletableFuture<String> cf3 = cf1.applyToEither(cf2, anyResult -> anyResult + " from applyToEither");
        cf3.join();
        log.info(cf3.getNow(null));
    }

    /**
     * 将两个CompletableFuture中的任意一个完成后的结果应用给指定的函数
     * acceptEither
     * acceptEitherAsync
     * 结果处理函数为Consumer，即无返回值
     */
    private static void acceptEither() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    return x.toUpperCase();
                });

        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    return x.toLowerCase();
                });
        //将两个CompletableFuture中的任意一个完成后的结果应用给指定的函数
        CompletableFuture<Void> cf3 = cf1.acceptEither(cf2, anyResult -> log.info(anyResult + " from applyToEither"));
        cf3.join();
    }

    /**
     * 等两个CompletableFuture都完成后执行指定的函数，没用到前面的执行结果
     * runAfterBoth
     * runAfterBothAsync
     */
    private static void runAfterBoth() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf1开始执行");
                    return x.toUpperCase();
                });

        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf2开始执行");
                    return x.toLowerCase();
                });
        //等两个CompletableFuture都完成后执行指定的函数
        CompletableFuture<Void> cf3 = cf1.runAfterBoth(cf2, () -> log.info("cf1和cf2执行完成"));
        cf3.join();
    }

    /**
     * thenAcceptBoth 方法主要作用：结合两个 CompletionStage 的结果，进行消耗，返回CompletableFuture<Void> 类型
     * thenAcceptBoth 跟 thenCombine 类似，但是返回 CompletableFuture<Void> 类型。
     * thenAcceptBoth
     * thenAcceptBothAsync
     * 执行函数：BiConsumer
     */
    private static void thenAcceptBoth() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf1开始执行");
                    return x.toUpperCase();
                });

        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf2开始执行");
                    return x.toLowerCase();
                });
        //等两个CompletableFuture都完成后的结果应用给指定的函数
        CompletableFuture<Void> cf3 = cf1.thenAcceptBoth(cf2, (r1, r2) -> log.info("cf1结果:{}，cf2结果:{}", r1, r2));
        cf3.join();

    }

    /**
     * thenCombine 方法主要作用：结合两个 CompletionStage 的结果，进行转化后返回
     * 使用 thenCombine() 之后 future1、future2 之间是并行执行的，最后再将结果汇总
     * 结果合并
     * thenCombine
     * thenCombineAsync
     * 结果执行函数：BiFunction
     */
    private static void thenCombine() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf1开始执行");
                    return x.toUpperCase();
                });

        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf2开始执行");
                    return x.toLowerCase();
                });
        //等两个CompletableFuture都完成后的结果应用给指定的函数
        CompletableFuture<String> cf3 = cf1.thenCombine(cf2, (r1, r2) -> r1 + "----" + r2);
        cf3.join();
        log.info(cf3.getNow(null));
    }

    /**
     * thenCompose 可以用于组合多个 CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序
     * 在一个CompletableFuture完成后，将其结果作为输入传递给另一个CompletableFuture的操作，形成一个操作链
     * thenCompose
     * thenComposeAsync
     * 结果执行函数：Function< T, CompletionStage<U>> fn)
     */
    static void thenCompose() {
        String original = "Message";
        CompletableFuture<String> cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf1开始执行");
                    return x.toUpperCase();
                });

        CompletableFuture<String> cf2 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(x -> {
                    sleepRandomSecond();
                    log.info("cf2开始执行");
                    return x.toLowerCase();
                });
        //将cf1的执行结果传给cf2处理，形成操作链
        CompletableFuture<String> cf3 = cf1.thenCompose(r1 -> cf2.thenApply(r2 -> r1 + "--" + r2));
        cf3.join();
        log.info(cf3.getNow(null));
    }

    private static int sleepRandomSecond() {
        int seconds = ThreadLocalRandom.current().nextInt(0, 10);
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return seconds;
    }
}