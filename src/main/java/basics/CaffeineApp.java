package basics;

import com.github.benmanes.caffeine.cache.AsyncCache;
import com.github.benmanes.caffeine.cache.Cache;
import com.github.benmanes.caffeine.cache.Caffeine;
import com.github.benmanes.caffeine.cache.LoadingCache;
import com.github.benmanes.caffeine.cache.RemovalCause;
import com.github.benmanes.caffeine.cache.Scheduler;
import lombok.extern.slf4j.Slf4j;

import java.lang.ref.Cleaner;
import java.lang.ref.WeakReference;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@Slf4j
public class CaffeineApp {
    public static void main(String[] args) {
        //1.手动加载
        Cache<Integer, Integer> manualCache = Caffeine.newBuilder()
                //写入后1分钟过期，基于时间淘汰
                .expireAfterWrite(1, TimeUnit.MINUTES)
                //访问后1分钟过期，基于时间淘汰
                .expireAfterAccess(1, TimeUnit.MINUTES)
                //写入后1分钟自动刷新过期时间
                .refreshAfterWrite(1, TimeUnit.MINUTES)
                //弱引用key，基于引用淘汰
                .weakKeys()
                //弱引用value，基于引用淘汰
                .weakValues()
                //最大容量，基于容量淘汰
                .maximumSize(1000)
                //开启记录
                .recordStats()
                .scheduler(Scheduler.systemScheduler())
                //淘汰监听
                .removalListener(((key, value, cause) -> {
                    log.info("淘汰通知，key：" + key + "，原因：" + cause);
                }))
                //过期监听
                .evictionListener((Integer key, Integer value, RemovalCause cause) ->
                        System.out.printf("Key %s was evicted (%s)%n", key, cause))
                //淘汰监听
                .removalListener((Integer key, Integer value, RemovalCause cause) ->
                        System.out.printf("Key %s was removed (%s)%n", key, cause))
                .build();
        Integer cacheValue = manualCache.get(1, x -> {
            log.info("加载缓存");
            return 2;
        });
        System.out.println(cacheValue);
        // 命中率
        System.out.println(manualCache.stats().hitRate());
        // 被剔除的数量
        System.out.println(manualCache.stats().evictionCount());
        // 加载新值所花费的平均时间[纳秒]
        System.out.println(manualCache.stats().averageLoadPenalty());

        //weakKeys(), weakKeys()
        Cleaner cleaner = Cleaner.create();
        cleaner.register(manualCache, () -> log.info("缓存已被清理"));

        //2.同步加载，在get不到数据时会加载数据
        LoadingCache<Integer, Integer> syncCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(1000)
                .build(key -> {
                    log.info("加载数据");
                    return key + 1;
                });
        log.info("     ");
        System.out.println(syncCache.get(1));


        //3.异步加载
        ExecutorService executor = Executors.newSingleThreadExecutor();
        AsyncCache<Integer, Integer> asyncCache = Caffeine.newBuilder()
                .expireAfterWrite(1, TimeUnit.MINUTES)
                .maximumSize(100)
                .executor(executor)
//                .buildAsync((key, executor1) -> {
//                    log.info("");
//                    return key + 1;
//                });
                .buildAsync();
        // get返回的是CompletableFuture
        int key = 1;
        CompletableFuture<Integer> future = asyncCache.get(key, k -> {
            // 执行所在的线程不在是main，而是ForkJoinPool线程池提供的线程
            System.out.println("当前所在线程：" + Thread.currentThread().getName());
            return k + 1;
        });

        int value = 0;
        try {
            value = future.get();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("当前所在线程：" + Thread.currentThread().getName());
        System.out.println(value);

    }


    /**
     * 充当二级缓存用，生命周期仅活到下个gc
     */
    private Map<Integer, WeakReference<Integer>> secondCacheMap = new ConcurrentHashMap<>();

    public void test() throws InterruptedException {
        // 设置最大缓存个数为1
        LoadingCache<Integer, Integer> cache = Caffeine.newBuilder()
                .maximumSize(1)
                .build(key -> {
                    WeakReference<Integer> value = secondCacheMap.get(key);
                    if (value == null) {
                        return null;
                    }
                    log.info("触发CacheLoader.load，从二级缓存读取key ={} ", key);
                    return value.get();
                });
        cache.put(1, 1);
        cache.put(2, 2);
        // 由于清除缓存是异步的，因而睡眠1秒等待清除完成
        Thread.sleep(1000);

        // 缓存超上限触发清除后
        System.out.println("从Caffeine中get数据，key为1，value为" + cache.get(1));
    }
}
