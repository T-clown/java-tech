package util;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CacheUtil {
    private static final Cache<Integer, String> CACHE = CacheBuilder
        .newBuilder()
        .maximumSize(50)
        .expireAfterWrite(30, TimeUnit.SECONDS)
        .build();


    public static void main(String[] args) {

        try {
            System.out.println(getCache());
            System.out.println(getCache());
            System.out.println(getCache());
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
    private static String getCache() throws ExecutionException {
        return CACHE.get(1, () -> {
            log.info("添加到缓存");
            return "获取缓存数据";
        });
    }
}
