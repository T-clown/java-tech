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
 */
@Slf4j
public class CompletableFutureDemo {
    static ExecutorService executor = Executors.newFixedThreadPool(5, new ThreadFactory() {
        int count = 1;

        @Override
        public Thread newThread(Runnable runnable) {
            return new Thread(runnable, "custom-executor-" + count++);
        }
    });

    /**
     * 使用 ForkJoinPool.commonPool() 作为它的线程池执行异步代码，异步操作有返回值
     */
    CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
        log.info("有返回值异步操作");
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
     * 使用 ForkJoinPool.commonPool() 作为它的线程池执行异步代码
     */
    CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
        log.info("无返回值异步操作");
    });
    /**
     * 使用指定的 thread pool 执行异步代码
     */
    CompletableFuture<Void> runAsyncExecutor = CompletableFuture.runAsync(() -> {
        log.info("指定线程池无返回值异步操作");
    });

    /**
     *complete：完成异步执行，并返回 future 的结果
     * completeExceptionally：异步执行不正常的结束，抛出一个异常，而不是一个成功的结果
     */

    /**
     * thenApply 的功能相当于将 CompletableFuture<T> 转换成 CompletableFuture<U>。
     *
     * thenApply 函数的功能是当原来的 CompletableFuture 计算完后，将结果传递给函数 fn，将 fn 的结果作为新的 CompletableFuture 计算结果，这些转换并不是马上执行的，也不会阻塞，而是在前一个 stage 完成后继续执行。
     *
     * 它们与 handle 方法的区别在于 handle 方法会处理正常计算值和异常，因此它可以屏蔽异常，避免异常继续抛出。而 thenApply 方法只是用来处理正常值，因此一旦有异常就会抛出。
     */

    /**
     * thenCompose 可以用于组合多个 CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序
     * thenCompose 可以用于组合多个 CompletableFuture，将前一个结果作为下一个计算的参数，它们之间存在着先后顺序。
     *
     * thenApply() 是接受一个 Function<? super T,? extends U> 参数用来转换 CompletableFuture，相当于流的 map 操作，返回的是非 CompletableFuture 类型，它的功能相当于将 CompletableFuture<T> 转换成 CompletableFuture<U>。
     *
     * thenCompose() 在异步操作完成的时候对异步操作的结果进行一些操作，并且仍然返回 CompletableFuture 类型，相当于 flatMap，用来连接两个 CompletableFuture。
     */

    /**
     * thenCombine 方法主要作用：结合两个 CompletionStage 的结果，进行转化后返回
     * 现在有 CompletableFuture<T>、CompletableFuture<U> 和一个函数 (T, U) -> V，thenCompose 就是将 CompletableFuture<T> 和 CompletableFuture<U> 变为 CompletableFuture<V>。
     * 使用 thenCombine() 之后 future1、future2 之间是并行执行的，最后再将结果汇总
     */

    /**
     * thenAcceptBoth 方法主要作用：结合两个 CompletionStage 的结果，进行消耗，返回CompletableFuture<Void> 类型
     * thenAcceptBoth 跟 thenCombine 类似，但是返回 CompletableFuture<Void> 类型。
     *
     * thenAcceptBoth 以及相关方法提供了类似的功能，当两个 CompletionStage 都正常完成计算的时候，就会执行提供的 action，它用来组合另外一个异步的结果。
     */

    /**
     * whenComplete 计算结果完成时的处理
     * 可以看到 Action 的类型是 BiConsumer<? super T, ? super Throwable> 它可以处理正常的计算结果，或者异常情况。
     *
     * 方法不以 Async 结尾，意味着 Action 使用相同的线程执行，而 Async 可能会使用其他线程执行（如果是使用相同的线程池，也可能会被同一个线程选中执行）。
     *
     * exceptionally 方法返回一个新的 CompletableFuture，当原始的 CompletableFuture 抛出异常的时候，就会触发这个 CompletableFuture 的计算，调用 function 计算值，也就是这个 exceptionally 方法用来处理异常的情况。
     */

    /**
     * handle 方法主要作用：运行完成时，对结果的处理
     * 一组 handle 方法也可用于处理计算结果。当原先的 CompletableFuture 的值计算完成或者抛出异常的时候，会触发这个 CompletableFuture 对象的计算，结果由 BiFunction 参数计算而得。因此这组方法兼有 whenComplete 和转换的两个功能。
     */


    public static void main(String[] args) {

        Map<Integer, BigDecimal> map = new HashMap<>();
        map.merge(1, BigDecimal.ONE, BigDecimal::add);
        map.merge(1, BigDecimal.ONE, BigDecimal::add);
        CompletableFuture<Void> runAsync = CompletableFuture.runAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("无返回值异步操作：睡眠{}秒，线程：{}", seconds,Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
        });

        CompletableFuture<Void> runAsync1 = CompletableFuture.runAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("无返回值异步操作：睡眠{}秒，线程:{}", seconds,Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
        }, executor);

        CompletableFuture<String> supplyAsync = CompletableFuture.supplyAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("有返回值异步操作，睡眠{}秒,线程:{}", seconds,Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
            return "有返回值异步操作";
        });

        CompletableFuture<String> supplyAsync1 = CompletableFuture.supplyAsync(() -> {
            int seconds = sleepRandomSecond();
            log.info("有返回值异步操作，睡眠{}秒，线程:{}", seconds,Thread.currentThread().getName());
            System.out.println(Thread.currentThread().isDaemon());
            return "有返回值异步操作";
        }, executor);
        //List<CompletableFuture<Void>> futures = Arrays.asList(runAsync1, runAsync);
        List<CompletableFuture<String>> futures = Arrays.asList(supplyAsync1, supplyAsync);
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.join();
        //futures.forEach(x -> System.out.println(x.getNow(null)));
        //thenCompose();
        // thenCombine();
        //thenAcceptBoth();
        //completedFutureExample();
    }

    private static void completedFutureExample() {
        CompletableFuture<String> cf = CompletableFuture.completedFuture("message");
        assertTrue(cf.isDone());
        try {
            assertEquals("message", cf.get());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void async() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1: 洗水壶");
            sleepRandomSecond(1, TimeUnit.SECONDS);
            System.out.println("T1: 烧开水");
            sleepRandomSecond(15, TimeUnit.SECONDS);
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶壶");
            sleepRandomSecond(1, TimeUnit.SECONDS);
            System.out.println("T2: 洗茶杯");
            sleepRandomSecond(2, TimeUnit.SECONDS);
            System.out.println("T2: 拿茶叶");
            sleepRandomSecond(1, TimeUnit.SECONDS);
            return "龙井";
        });

        CompletableFuture<String> f3 = f1.thenCombine(f2, (__, tea) -> {
            System.out.println("T3: 拿到茶叶: " + tea);
            System.out.println("T3: 泡茶");
            return "上茶: " + tea;
        });

        System.out.println(f3.join());
    }

    /**
     * 异步有返回值
     */
    private static void supplyAsync() {
        CompletableFuture<String> f1 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom();
            System.out.println("f1 need " + t);
            sleepRandomSecond(t, TimeUnit.SECONDS);
            System.out.println("f1 done");
            return "f1 takes " + t;
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom();
            System.out.println("f2 need " + t);
            sleepRandomSecond(t, TimeUnit.SECONDS);
            System.out.println("f2 done");
            return "f2 takes " + t;
        }, executor);

        CompletableFuture<String> f3 = f1.applyToEither(f2, s -> s);
        f3.join();
    }

    /**
     * 异步无返回值
     */
    private static void runAsync() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            int t = getRandom();
            System.out.println("f1 need " + t);
            sleepRandomSecond(t, TimeUnit.SECONDS);
            System.out.println("f1 done");
        });

        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            int t = getRandom();
            System.out.println("f2 need " + t);
            sleepRandomSecond(t, TimeUnit.SECONDS);
            System.out.println("f2 done");
        });

        f1.complete(null);
        f2.complete(null);
    }


    private static void exception() {
        CompletableFuture<Integer> f = CompletableFuture
                .supplyAsync(() -> 1 / 0)
                .thenApply(i -> i * 10)
                // 类似于catch{}
                .exceptionally(e -> {
                    System.out.println(e.getMessage());
                    return 5;
                })
                .thenApply(x -> x * 2)
                .whenComplete((i, t) -> {
                    // 类似于finally，whenComplete不支持返回结果，handle支持返回结果
                    //log.info(i.toString());
                    //log.error(t.getMessage());
                    System.out.println(i);
                    System.out.println(t);

                });
        System.out.println(f.join());
    }

    private static void thenAcceptAsync() {
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture("thenAcceptAsync message")
                .thenAcceptAsync(result::append);
        cf.join();
        assertTrue("Result was empty", result.length() > 0);
    }

    private static void completeExceptionally() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture exceptionHandler = cf.handle((s, th) -> (th != null) ? "message upon cancel" : "");
        cf.completeExceptionally(new RuntimeException("completed exceptionally"));
        log.info("Was not completed exceptionally:{}", cf.isCompletedExceptionally());
        try {
            cf.join();
            fail("Should have thrown an exception");
        } catch (CompletionException ex) {
            log.error("completed exceptionally:{}", ex.getCause().getMessage());
        }
        exceptionHandler.join();
    }

    static void cancel() {
        CompletableFuture cf = CompletableFuture.completedFuture("message").thenApplyAsync(String::toUpperCase,
                CompletableFuture.delayedExecutor(1, TimeUnit.SECONDS));
        CompletableFuture cf2 = cf.exceptionally(throwable -> "canceled message");
        log.info("Was not canceled:{}", cf.cancel(true));
        log.info("Was not completed exceptionally:{}", cf.isCompletedExceptionally());
        log.info("canceled message:{}", cf2.join());

    }

    static void applyToEither() {
        String original = "Message";
        CompletableFuture cf1 = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase);
        CompletableFuture cf2 = cf1.applyToEither(
                CompletableFuture.completedFuture(original).thenApplyAsync(String::toLowerCase),
                s -> s + " from applyToEither");
        cf2.join();

    }

    static void acceptEither() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase)
                .acceptEither(CompletableFuture.completedFuture(original).thenApplyAsync(String::toLowerCase),
                        s -> result.append(s).append("acceptEither"));
        cf.join();
        log.info("Result was empty:{}", result.toString().endsWith("acceptEither"));
    }

    static void runAfterBoth() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).runAfterBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                () -> result.append("done"));
        log.info("Result was empty:{}", result.length() > 0);
    }

    static void thenAcceptBoth() {
        String original = "Message";
        StringBuilder result = new StringBuilder();
        CompletableFuture.completedFuture(original).thenApply(String::toUpperCase).thenAcceptBoth(
                CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                (s1, s2) -> result.append(s1).append(s2));
        log.info("MESSAGEmessage:{}", result.toString());

    }

    static void thenCombine() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApply(String::toLowerCase),
                        (s1, s2) -> s1 + s2);
        log.info("MESSAGEmessage:{}", cf.getNow(null));
    }

    static void thenCombineAsyncExample() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original)
                .thenApplyAsync(String::toUpperCase)
                .thenCombine(CompletableFuture.completedFuture(original).thenApplyAsync(String::toLowerCase),
                        (s1, s2) -> s1 + s2);
        log.info("MESSAGEmessage:{}", cf.join());
    }

    static void thenCompose() {
        String original = "Message";
        CompletableFuture cf = CompletableFuture.completedFuture(original).thenApply(String::toUpperCase)
                .thenCompose(upper -> CompletableFuture.completedFuture(original).thenApply(String::toLowerCase)
                        .thenApply(s -> upper + s));
        log.info("MESSAGEmessage:{}", cf.join());
    }

    static void anyOfExample() {

        StringBuilder result = new StringBuilder();

        List messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = (List<CompletableFuture>) messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(x -> delayedUpperCase((String) x)))
                .collect(Collectors.toList());

        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if (th == null) {
                result.append(res);
            }
        });

        log.info("Result was empty:{}", result.length() > 0);

    }


    static void allOfExample() {

        StringBuilder result = new StringBuilder();

        List<String> messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture<String>> futures = messages.stream()
                .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> s))
                .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {

            futures.forEach(cf -> log.info("" + cf.getNow(null)));

            result.append("done");

        });

    }


    static void allOfAsyncExample() {

        StringBuilder result = new StringBuilder();

        List messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = (List<CompletableFuture>) messages.stream()

                .map(msg -> CompletableFuture.completedFuture(msg).thenApplyAsync(s -> delayedUpperCase((String) s)))

                .collect(Collectors.toList());

        CompletableFuture allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()]))

                .whenComplete((v, th) -> {
                    System.out.println(futures.size());

                    result.append("done");

                });

        allOf.join();

        assertTrue("Result was empty", result.length() > 0);

    }


    private static String delayedUpperCase(String s) {
        sleepRandomSecond(10, TimeUnit.SECONDS);
        return s.toString().toUpperCase();
    }

    CompletableFuture<String> cf = CompletableFuture.completedFuture("message").thenApply(s -> {
        log.info("当前线程是否为守护线程：" + Thread.currentThread().isDaemon());
        return s.toUpperCase();
    });

    CompletableFuture<String> cfAsync = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
        log.info("当前线程是否为守护线程：" + Thread.currentThread().isDaemon());
        sleepRandomSecond(10, TimeUnit.SECONDS);
        return s.toUpperCase();
    });

    CompletableFuture<String> cfExuctor = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
        sleepRandomSecond(15, TimeUnit.SECONDS);
        return s.toUpperCase();
    }, executor);

    CompletableFuture<Void> cfAccept = CompletableFuture.completedFuture("thenAccept message")
            .thenAccept(x -> log.info("suppply result:" + x));


    private static int getRandom() {
        return ThreadLocalRandom.current().nextInt(0, 10);
    }

    private static void sleepRandomSecond(long time, TimeUnit timeUnit) {
        try {
            timeUnit.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static int sleepRandomSecond() {
        int seconds = getRandom();
        try {
            TimeUnit.SECONDS.sleep(seconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return seconds;
    }
}