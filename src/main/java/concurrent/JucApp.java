package concurrent;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class JucApp {
    //private static Map<Object, Object> hashMap =Collections.synchronizedMap( new HashMap<>());
    private static Map<Object, Object> hashMap =new ConcurrentHashMap<>();
    Object o=new Object();


    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool = Executors.newCachedThreadPool();

        for (int i = 1; i < 101; i++) {
            int finalI = i;
            threadPool.execute(() -> add(finalI));
        }
        Thread.sleep(10*1000);
        System.out.println(hashMap.size());
    }

    private  static void add(int a) {
        hashMap.put(a, a);
    }
}
