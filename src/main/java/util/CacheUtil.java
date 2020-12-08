package util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheUtil {
    private static final Cache<Integer, String> CACHE = CacheBuilder
        .newBuilder()
        .maximumSize(50)
        .expireAfterWrite(30, TimeUnit.SECONDS)
        .build();

    private static AtomicInteger count =new AtomicInteger(1);

    public static void main(String[] args) {

        String a = null;
        try {
            System.out.println(count.get());
            System.out.println(getCache());
            System.out.println(count.get());
            System.out.println(getCache());
            System.out.println(count.get());
            System.out.println(getCache());
            System.out.println(getCache());
            System.out.println(getCache());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
    private static String getCache() throws ExecutionException {
        return CACHE.get(1, () -> {
            System.out.println("缓存失效");
            System.out.println(count.getAndIncrement());
            return "获取缓存数据";
        });
    }
}
