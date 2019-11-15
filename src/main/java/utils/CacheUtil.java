package utils;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class CacheUtil {
    private static final Cache<Integer, String> CACHE = CacheBuilder
        .newBuilder()
        .maximumSize(50)
        .expireAfterWrite(30, TimeUnit.SECONDS)
        .build();

    public static void main(String[] args) {

        String a = null;
        try {
            System.out.println(null+"");
            a = CACHE.get(1, () -> {
                String str = "获取";
                return str + "缓存数据";
            });
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println(a);
    }
}
