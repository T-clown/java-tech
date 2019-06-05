package concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public class CompletableFutureDemo {
    public static void main(String[] args) {
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

    private static  void sleep(long value ,TimeUnit tu){
        try {
            tu.sleep(value);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
