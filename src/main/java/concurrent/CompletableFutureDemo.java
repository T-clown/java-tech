package concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import lombok.extern.slf4j.Slf4j;

import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.util.AssertionErrors.fail;

/**
 * 异步执行通过ForkJoinPool实现， 它使用守护线程去执行任务
 * colobu.com/2018/03/12/20-Examples-of-Using-Java%E2%80%99s-CompletableFuture/
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

    public static void main(String[] args) {
        //thenCompose();
       // thenCombine();
        thenAcceptBoth();
    }

    private static void async() {
        CompletableFuture<Void> f1 = CompletableFuture.runAsync(() -> {
            System.out.println("T1: 洗水壶");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T1: 烧开水");
            sleep(15, TimeUnit.SECONDS);
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            System.out.println("T2: 洗茶壶");
            sleep(1, TimeUnit.SECONDS);
            System.out.println("T2: 洗茶杯");
            sleep(2, TimeUnit.SECONDS);
            System.out.println("T2: 拿茶叶");
            sleep(1, TimeUnit.SECONDS);
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
            sleep(t, TimeUnit.SECONDS);
            System.out.println("f1 done");
            return "f1 takes " + t;
        });

        CompletableFuture<String> f2 = CompletableFuture.supplyAsync(() -> {
            int t = getRandom();
            System.out.println("f2 need " + t);
            sleep(t, TimeUnit.SECONDS);
            System.out.println("f2 done");
            return "f2 takes " + t;
        },executor);

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
            sleep(t, TimeUnit.SECONDS);
            System.out.println("f1 done");
        });

        CompletableFuture<Void> f2 = CompletableFuture.runAsync(() -> {
            int t = getRandom();
            System.out.println("f2 need " + t);
            sleep(t, TimeUnit.SECONDS);
            System.out.println("f2 done");
        });

       f1.complete(null);
       f2.complete(null);
    }

    private static int getRandom() {
        Random random = new Random(10);
        return random.nextInt();
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
            .thenApply(x->x*2)
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
            .map(msg -> CompletableFuture.completedFuture(msg).thenApply(x->delayedUpperCase((String) x)))
            .collect(Collectors.toList());

        CompletableFuture.anyOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((res, th) -> {
            if(th == null) {
                result.append(res);
            }
        });

        log.info("Result was empty:{}", result.length() > 0);

    }


    static void allOfExample() {

        StringBuilder result = new StringBuilder();

        List messages = Arrays.asList("a", "b", "c");

        List<CompletableFuture> futures = (List<CompletableFuture>) messages.stream()

            .map(msg -> CompletableFuture.completedFuture(msg).thenApply(s -> delayedUpperCase((String) s)))

            .collect(Collectors.toList());

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[futures.size()])).whenComplete((v, th) -> {

            futures.forEach(cf -> log.info(""+cf.getNow(null)));

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


    private static void tt(){

//        cars().thenCompose(cars -> {
//
//            List<CompletionStage> updatedCars = cars.stream()
//
//                    .map(car -> rating(car.manufacturerId).thenApply(r -> {
//
//                        car.setRating(r);
//
//                        return car;
//
//                    })).collect(Collectors.toList());
//
//
//
//            CompletableFuture done = CompletableFuture
//
//                    .allOf(updatedCars.toArray(new CompletableFuture[updatedCars.size()]));
//
//            return done.thenApply(v -> updatedCars.stream().map(CompletionStage::toCompletableFuture)
//
//                    .map(CompletableFuture::join).collect(Collectors.toList()));
//
//        }).whenComplete((cars, th) -> {
//
//            if (th == null) {
//
//                cars.forEach(System.out::println);
//
//            } else {
//
//                throw new RuntimeException(th);
//
//            }
//
//        }).toCompletableFuture().join();
    }

    private static void sleep(long value, TimeUnit tu) {
        try {
            tu.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private static String delayedUpperCase(String s){
        sleep(10,TimeUnit.SECONDS);
      return   s.toString().toUpperCase();
    }

    CompletableFuture cf = CompletableFuture.completedFuture("message").thenApply(s -> {
        log.info("当前线程是否为守护线程：" + Thread.currentThread().isDaemon());
        return s.toUpperCase();
    });

    CompletableFuture cfAsync = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
        log.info("当前线程是否为守护线程：" + Thread.currentThread().isDaemon());
        sleep(10, TimeUnit.SECONDS);
        return s.toUpperCase();
    });

    CompletableFuture cfExuctor = CompletableFuture.completedFuture("message").thenApplyAsync(s -> {
        sleep(15, TimeUnit.SECONDS);
        return s.toUpperCase();
    }, executor);

    CompletableFuture cfAccept = CompletableFuture.completedFuture("thenAccept message")
        .thenAccept(x -> log.info("suppply result:" + x));

}